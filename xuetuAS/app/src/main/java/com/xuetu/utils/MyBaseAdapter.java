package com.xuetu.utils;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/*
 * 所有的adapter都要重写getcount等方法，而且都是一样的代码；所以封装成一个;
 * 所有的adapter都需要有一个集合
 * 泛型类：在构造方法中指定具体类型
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	public List<T> list;
	Context context;
	int resoureId;
	
	/*
	 * 构造方法中传入泛型类型
	 */
	
	public MyBaseAdapter(List<T> list,Context context,int resoureId){
		this.list=list;
		this.context=context;
		this.resoureId=resoureId;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int arg0) {
		return list.get(arg0);//获取某个item的对象
	}

	
	//???
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//找到布局的view，找到控件，设置控件值；
		/*
		 * 原来逻辑：
		 * 判断convertview是否为null；为null:重新解析布局，存入convertview；
		 * convertview和viewholder绑定，只查找一次控件；
		 */
		
		ViewHolder viewHolder=ViewHolder.get(context, parent, resoureId, convertView);
		
		/*抽象出一个方法：针对某一个控件，给该控件赋值即可；
		 * convert：找出一个控件，给该控件赋值
		 * viewholder中是所有控件对象的集合；
		 * getItem(position)是所有数据的集合；
		 * 
		 */
		
		convert(viewHolder,getItem(position));
		return viewHolder.getConvertView();
		
	}

	/*
	 * convert：找出一个控件，给该控件赋值
		 * viewholder中是所有控件对象的集合；
		 * getItem(position)是所有数据的集合；
	 */
	public abstract void convert(ViewHolder viewHolder,T item);
}
