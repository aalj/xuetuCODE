package com.xuetu.adapter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


import com.xuetu.R;
import com.xuetu.entity.Question;
import com.xuetu.ui.Question_ask;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyQuestionListBaseAdapter extends BaseAdapter {
	Context context;
	List<Question> list = null;
	public MyQuestionListBaseAdapter(Context context,List<Question> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewHolder vh = null;
		String date = null;
		if(convertView==null){
			vh = new viewHolder();
			convertView	= LayoutInflater.from(context).inflate(com.xuetu.R.layout.question_listitem, null);
			vh.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
			vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			vh.tv_ques_text = (TextView) convertView.findViewById(R.id.tv_ques_text);
			vh.rl_top = (RelativeLayout) convertView.findViewById(R.id.rl_top);
			vh.rl_left = (RelativeLayout) convertView.findViewById(R.id.rl_left);
			vh.rl_right = (RelativeLayout) convertView.findViewById(R.id.rl_right);
//			vh.iv_ques_img = (ImageView) convertView.findViewById(R.id.iv_ques_img);
			convertView.setTag(vh);
		}else{
			vh = (viewHolder) convertView.getTag();
		}
		vh.tv_ques_text.setText(list.get(position).getQuesText());
		vh.tv_subject.setText(list.get(position).getSubject().getName()+"");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sdf.format(new java.sql.Timestamp(System.currentTimeMillis())));
		
		date = sdf.format(list.get(position).getQuesDate());
		Log.i("hehe", sdf.format(new Date(list.get(position).getQuesDate().getTime())));
		vh.tv_time.setText(sdf.format(new Date(list.get(position).getQuesDate().getTime())));
//		vh.iv_ques_img.setImageResource(list.get(position).getQues_img());
		
		//设置监听器
		vh.rl_top.setOnClickListener(new MyClickListener(context));
		vh.rl_left.setOnClickListener(new MyClickListener(context));
		vh.rl_right.setOnClickListener(new MyClickListener(context));
		return convertView;
	}
	public class MyClickListener implements OnClickListener{
		Context context = null;
		public MyClickListener(Context context){
			this.context = context;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.rl_top:
				Toast.makeText(context, "top", Toast.LENGTH_LONG).show();
				break;
			case R.id.rl_left:
				Toast.makeText(context, "left", Toast.LENGTH_LONG).show();
				break;
			case R.id.rl_right:
				Intent intent = new Intent(context,Question_ask.class);
				context.startActivity(intent);
				Toast.makeText(context, "right", Toast.LENGTH_LONG).show();
				break;
			}
		}
		
	}
	public static class viewHolder{
		TextView tv_subject;
		TextView tv_time;
		TextView tv_ques_text;
		RelativeLayout rl_top;
		RelativeLayout rl_left;
		RelativeLayout rl_right;
//		ImageView iv_ques_img;
		
	}
}
