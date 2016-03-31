package com.xuetu.adapter;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.entity.Question;
import com.xuetu.ui.Answer_list;
import com.xuetu.utils.GetHttp;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionFragAdapter extends BaseAdapter{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  private List<Question> questions;
		private Context context;
		LayoutInflater inflater;
		List<Integer> listtag = new ArrayList<Integer>();
		BitmapUtils bitmapUtils = null;
		int stu_id;
		String url = null;
		HttpUtils hutils = new HttpUtils(10000);
		RequestParams params ;
		private RequestParams paramIsSave;
		private String urlIsSave;
		public QuestionFragAdapter(List<Question> questions,Context context,int stu_id,List<Integer> listtag){
			this.stu_id = stu_id;
			bitmapUtils = new BitmapUtils(context);
			this.questions=questions;
			this.context=context;
			this.listtag = listtag;
			inflater=LayoutInflater.from(context);
		}
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return questions.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position ;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder v;
			hutils.configCurrentHttpCacheExpiry(5000);
			if(convertView==null){
				System.out.println("convertView为空"+position);
				v=new ViewHolder();
				convertView=inflater.inflate(R.layout.question_listitem, null);
				v.tv_answerNum=(TextView) convertView.findViewById(R.id.tv_answerNum);
				v.tv_ques_text=(TextView) convertView.findViewById(R.id.tv_ques_text);
				v.tv_subject=(TextView) convertView.findViewById(R.id.tv_subject);
				v.tv_time=(TextView) convertView.findViewById(R.id.tv_time);
				v.iv_ques_img = (ImageView) convertView.findViewById(R.id.iv_ques_img);
				v.rl_left = (RelativeLayout) convertView.findViewById(R.id.rl_left);
				v.rl_right = (RelativeLayout) convertView.findViewById(R.id.rl_right);
				v.rl_top = (RelativeLayout) convertView.findViewById(R.id.rl_top);
				v.iv_like = (ImageView) convertView.findViewById(R.id.iv_save);
				
				convertView.setTag(v);
			}else{
				System.out.println("convertView不为空"+position);
				 v=(ViewHolder) convertView.getTag();
			}
			v.tv_answerNum.setText(questions.get(position).getAns_num()+"");
			v.tv_ques_text.setText(questions.get(position).getQuesText());
			v.tv_subject.setText(questions.get(position).getSubject().getName());
			v.tv_time.setText(sdf.format(new Date(questions.get(position).getQuesDate().getTime())));
			bitmapUtils.display(v.iv_ques_img,GetHttp.getHttpLC()+questions.get(position).getQuesIma());
			//通过数据库查询是否被点赞
			for(int l:listtag){
				Log.i("hehe", "size------->"+l);
			}
			
			v.iv_like.setTag(questions.get(position).getQuesID());
			//判断复用
			if(listtag.contains(questions.get(position).getQuesID())){
				v.iv_like.setImageResource(R.drawable.ic_saved);
			}else
				v.iv_like.setImageResource(R.drawable.ic_save);
			//imageview加tag:
			
			//v.zan.setTag(key, tag); //key:控件id
			v.rl_left.setOnClickListener(new MyOnclickListener(questions.get(position)));
			v.rl_top.setOnClickListener(new MyOnclickListener(questions.get(position)));
			v.iv_like.setOnClickListener(new MyOnclickListener(questions.get(position)));
			
			
			
			//设置点击事件
			
			v.iv_like.setOnClickListener(new OnClickListener(){

				/*1、 imageview加tag:position
				 * 2、所有点过赞的imageview，加到集合中;
				 *   3、进入一个view(getview)，判断如果position在集合中（点过赞图片源）；没有点过赞的图片源；*/
				@Override
				public void onClick(View v1) {
					params = new RequestParams();
					ImageView v=(ImageView) v1;
					if(listtag.contains(v.getTag())){
					listtag.remove((Integer) v.getTag());
					v.setImageResource(R.drawable.ic_save);
					
					//删除点赞记录写入数据库
					url=GetHttp.getHttpLC()+"CollectCancelQuestion" ;
					params.addBodyParameter("stu_id",stu_id+"");
					params.addBodyParameter("ques_id",(Integer)v1.getTag()+"");
					hutils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(context, "取消收藏失败", 0).show();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(context, "取消收藏成功", 0).show();
						}
					});
					}
					else{
						
						v.setImageResource(R.drawable.ic_saved);
						
						//点赞记录写入数据库
						url=GetHttp.getHttpLC()+"CollectQuestion" ;
//						Log.i("hehe", questions.get((Integer)v1.getTag()).getQuesID()+"");
						params.addBodyParameter("stu_id",stu_id+"");
						params.addBodyParameter("ques_id",(Integer)v1.getTag()+"");
						params.addBodyParameter("ques_time_collect",System.currentTimeMillis()+"");
						hutils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0, String arg1) {
								// TODO Auto-generated method stub
								Toast.makeText(context, "收藏失败", 0).show();
							}

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								Toast.makeText(context, "收藏成功", 0).show();
							}
						});
					}
				}});
			return convertView;
		}
		public class MyOnclickListener implements OnClickListener{
			Question curQues = null;
			public MyOnclickListener(Question curQues){
				this.curQues = curQues;
			}
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId()){
				case R.id.rl_left:
				case R.id.rl_top:
					// TODO Auto-generated method stub
					Toast.makeText(context, "click", 0).show();
					Intent intentAnswer1 = new Intent(context,Answer_list.class);
					Bundle bundle1 = new Bundle();
					bundle1.putSerializable("curQues",curQues);
					intentAnswer1.putExtras(bundle1);
					context.startActivity(intentAnswer1);
					break;
				}
			}
			
		}
		
		//封装view的所有控件
				static class ViewHolder{
					TextView tv_answerNum;
					TextView tv_ques_text;
					TextView tv_subject;
					TextView tv_time;
					ImageView iv_ques_img;
					RelativeLayout rl_right;
					RelativeLayout rl_left;
					RelativeLayout rl_top;
					ImageView iv_like;
					
					
				}

				
}
