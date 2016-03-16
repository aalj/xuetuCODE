package com.xuetu.adapter;

import java.text.SimpleDateFormat;
import java.util.List;


import com.xuetu.R;
import com.xuetu.entity.Question;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
		Log.i("hehe", "getview");
		viewHolder vh = null;
		String date = null;
		if(convertView==null){
			vh = new viewHolder();
			convertView	= LayoutInflater.from(context).inflate(com.xuetu.R.layout.question_listitem, null);
			vh.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
			vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			vh.tv_ques_text = (TextView) convertView.findViewById(R.id.tv_ques_text);
//			vh.iv_ques_img = (ImageView) convertView.findViewById(R.id.iv_ques_img);
			convertView.setTag(vh);
		}else{
			vh = (viewHolder) convertView.getTag();
		}
		Log.i("hehe", "Adapter----->getView");
		vh.tv_ques_text.setText(list.get(position).getQuesText());
		vh.tv_subject.setText(list.get(position).getSubject().getName()+"");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sdf.format(new java.sql.Timestamp(System.currentTimeMillis())));
		date = sdf.format(list.get(position).getQuesDate());
		Log.i("hehe", date+"");
		vh.tv_time.setText(date);
//		vh.iv_ques_img.setImageResource(list.get(position).getQues_img());
		return convertView;
	}
	public static class viewHolder{
		TextView tv_subject;
		TextView tv_time;
		TextView tv_ques_text;
//		ImageView iv_ques_img;
		
	}
}
