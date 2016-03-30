/**
 * RecomFrag.java
 * com.librarybooksearch.fragment
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年11月8日 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.QuestionFragAdapter;
import com.xuetu.entity.Question;
import com.xuetu.ui.Answer_list;
import com.xuetu.ui.Question_ask;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.OnRefreshListener;
import com.xuetu.view.RefreshListView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:RecomFrag
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年11月8日		下午3:46:46
 *
 * @see 	 

 */
public class QuestionFrag extends Fragment implements OnClickListener,OnRefreshListener{
	
	
	// 显示所有问题的列表

	private final int REFRESH_TEMP = 1;
	private final int REFRESH_LIMIT = 2;
	int countpage = 1;
	/** 请求数据的页数 */
	private int pageIndex = 0;
	//表示是否是第一次进入改也页面
	boolean firstInto = true;
	


	
	RefreshListView lv = null;
	HttpUtils hutils = new HttpUtils();
	List<Question> list = new ArrayList<Question>();
	List<Question> oldlist=new ArrayList<Question>();
	View view = null;
	View viewPop = null;
//	ListView lv = null;
	RelativeLayout rl_top;
	RelativeLayout rl_left;
	RelativeLayout rl_right;
	RelativeLayout right_layout;
	TextView tv_title;
	TextView tv_ansNum;
	ImageView iv_back;
	ImageView iv_like;
	String url = null;
	Drawable dr_save;
	Drawable dr_saved;
	int sub_id = 0;
	int stu_id = 0;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	QuestionFragAdapter adapter = null;
//	MyBasesadapter<Question> adapter = null;
//	MyQuestionListBaseAdapter adapter = null;
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.question_frag, null);
		dr_save = getActivity().getResources().getDrawable(R.drawable.ic_save);
		dr_saved = getActivity().getResources().getDrawable(R.drawable.ic_saved);
		right_layout = (RelativeLayout) view.findViewById(R.id.right_layout);
		viewPop = inflater.inflate(R.layout.title, null);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		lv = (RefreshListView) view.findViewById(R.id.lv_question);
		InitData(1, REFRESH_TEMP);
		
		/*adapter = new MyBasesadapter<Question>(getActivity(),list,R.layout.question_listitem) {
			@Override
			public void convert(ViewHodle viewHolder,final Question item) {
				// TODO Auto-generated method stub
				viewHolder.setText(R.id.tv_answerNum, item.getAns_num()+"");
				viewHolder.setText(R.id.tv_ques_text, item.getQuesText());
				viewHolder.setText(R.id.tv_subject, item.getSubject().getName());
				viewHolder.setText(R.id.tv_time,sdf.format(new Date(item.getQuesDate().getTime())) );
				iv_like = viewHolder.getView(R.id.iv_like);
				if(item.getQuesIma()!=null){
				viewHolder.SetUrlImage(R.id.iv_ques_img, GetHttp.getHttpLC()+item.getQuesIma());
				}
				sdf.format(new Date(item.getQuesDate().getTime()));
				viewHolder.getView(R.id.rl_right).setOnClickListener(new MyOnclickListener(item));
				viewHolder.getView(R.id.rl_left).setOnClickListener(new MyOnclickListener(item));
				viewHolder.getView(R.id.rl_top).setOnClickListener(new MyOnclickListener(item));
				
			}
		};
		lv.setAdapter(adapter);*/
		stu_id = ((XueTuApplication)getActivity().getApplication()).getStudent().getStuId();
		adapter = new QuestionFragAdapter(list, getContext(),stu_id);
		lv.setAdapter(adapter);
		lv.setOnRefreshListener(this);
		return view;

		
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
				Toast.makeText(getContext(), "click", 0).show();
				Intent intentAnswer1 = new Intent(getContext(),Answer_list.class);
				Bundle bundle1 = new Bundle();
				bundle1.putSerializable("curQues",curQues);
				intentAnswer1.putExtras(bundle1);
				startActivity(intentAnswer1);
				break;
			case R.id.rl_right:
				Toast.makeText(getContext(), "zan", 0).show();
//				iv_like.setVisibility(view.INVISIBLE);
//				iv_like.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_saved));
				
			}
		}
		
	}
	//下拉刷新
	
	
	
	
	@Override
	public void onResume() {
		stu_id = ((XueTuApplication)getActivity().getApplication()).getStudent().getStuId();
		
		super.onResume();
	}
	
	public void setOnclickListener(){
//		rl_right.setOnClickListener(this);
		tv_title.setOnClickListener(this);
		right_layout.setOnClickListener(this);
	}
	/**发送网络请求，下载所有问题信息
	 * 
	 */int count=0;
	public void InitData(final int tempnum,int temp){
		url = GetHttp.getHttpLC()+"GetPageQuestion";
		RequestParams params = new RequestParams();
		if (temp == REFRESH_TEMP) {
			params.addBodyParameter("page", "1");// 查询第1页
		} else {
			countpage++;
			params.addBodyParameter("page", countpage + "");// 查询第1页

		}
		params.addBodyParameter("num", "5");// 每页显示5条
//		hutils.configCurrentHttpCacheExpiry(1000);
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new java.sql.Timestamp(System.currentTimeMillis())));*/
		hutils.send(HttpMethod.POST, url,params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "fail");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				//指定date格式的gson对象
				Log.i("hehe", "success");
				Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type listtype = new TypeToken<List<Question>>(){}.getType();
				List<Question> lists = gson.fromJson(arg0.result, listtype);
					if(tempnum==1){
					list.removeAll(oldlist);
					list.addAll(0,lists);
					adapter.notifyDataSetChanged();
					lv.hideHeaderView();
					oldlist = lists;
				}
				else{
					list.addAll(lists);
					adapter.notifyDataSetChanged();
					// 控制脚布局隐藏
					lv.hideFooterView();
				}
			}
		});
	}

	//
	@Override
	public void onDownPullRefresh() {
		// 这是下拉刷新出来的数据
		
		InitData(1, REFRESH_TEMP);

	}

	@Override
	public void onLoadingMore() {
		// 这是加载更多出来的数据1
		InitData(2, REFRESH_LIMIT);
	}

	//弹出学科列表
	public void showPopupWinow(View v){
		 // 一个自定义的布局，作为显示的内容
       View contentView = LayoutInflater.from(getContext()).inflate(
               R.layout.subject_pop, null);
       //设置4个学科选项的监听实践
       contentView.findViewById(R.id.tv_sub1).setOnClickListener(this);
       contentView.findViewById(R.id.tv_sub2).setOnClickListener(this);
       contentView.findViewById(R.id.tv_sub3).setOnClickListener(this);
       contentView.findViewById(R.id.tv_sub4).setOnClickListener(this);
       // 设置按钮的点击事件
//     
//       Button button = (Button) contentView.findViewById(R.id.button1);
//       button.setOnClickListener(new OnClickListener() {
//
//           @Override
//           public void onClick(View v) {
//               Toast.makeText(mContext, "button is pressed",
//                       Toast.LENGTH_SHORT).show();
//           }
//       });
       final PopupWindow popupWindow = new PopupWindow(contentView,
               LayoutParams.MATCH_PARENT, 180, true);
       popupWindow.setTouchable(true);
       popupWindow.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
				// 这里如果返回true的话，touch事件将被拦截
               // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
       });
       // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
       // 我觉得这里是API的一个bug
       popupWindow.setBackgroundDrawable(getResources().getDrawable(
               R.drawable.ic_launcher));
       // 设置好参数之后再show
       popupWindow.showAsDropDown(v);
    // popupWindow.showAtLocation(view.getParent(),ce, x, y)
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_title:
			showPopupWinow(v);
			lv.setFocusableInTouchMode(true);
			break;
		case R.id.right_layout:
			Toast.makeText(getContext(), stu_id+"", 0).show();
			if(stu_id>0){
			Intent intent = new Intent(getContext(),Question_ask.class);
			getContext().startActivity(intent);
			}else{
				Toast.makeText(getContext(), "请先登录哟！", 0).show();
			}
			break;
		case R.id.tv_sub1:
			sub_id = 1;
			break;
		case R.id.tv_sub2:
			sub_id = 2;
			Toast.makeText(getContext(), "这个学科还没有问题，快去当第一人吧!!", 0).show();
			break;
		case R.id.tv_sub3:
			sub_id = 3;
			Toast.makeText(getContext(), "这个学科还没有问题，快去当第一人吧!!", 0).show();
			break;
		case R.id.tv_sub4:
			sub_id = 4;
			Toast.makeText(getContext(), "这个学科还没有问题，快去当第一人吧!!", 0).show();
			break;
		}
	}
}

