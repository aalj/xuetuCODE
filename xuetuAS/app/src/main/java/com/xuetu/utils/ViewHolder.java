package com.xuetu.utils;



import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TextView;


/*
 * 1、一个convertview对应一个viewholder；
 * 2、如果convertview为null,则解析布局文件，赋值给convertview，convertview和viewholder绑定；
 * 3、如果convertview不为null,则直接根据convertview获取viewholder;
 */

public class ViewHolder {

	//私有构造方法，不能随便创建对象
	/*
	 * 资源文件，上下文
	 */
	Context context;
	
	
	View mConvertView;
	
	//存放布局中所有控件(Integer-View)
	SparseArray<View> mViews;//创建集合，用于存放id-view
	
	
	private ViewHolder(Context context,ViewGroup parent,int resoureId){
		
		mViews=new SparseArray<View>();
		//不能在listview中添加子布局，必须要加上false;
		mConvertView=	LayoutInflater.from(context).inflate(resoureId, parent,false);
		mConvertView.setTag(this);
	}
	/*
	 * 一个viewholder对象，一个convertview，对应一个sparsearray(存放viewholder中的控件)
	 */
	
	//获取viewholder对象-convertview-sparsearray
	
	/*结论：
	 * convertview和mconvertview可以不是同一个对象，如果界面只能显示5条记录，那么第6条的时候，convertview就自动是第一条的view；
	 * getConvertview来决定；
	 */
	public static ViewHolder get(Context context,ViewGroup parent,int resoureId,View convertView){
		if(convertView==null){
			//解析布局文件，赋值给convertview；并将convertview和viewholder绑定；
			return new ViewHolder(context,parent,resoureId);
		}

			return (ViewHolder) convertView.getTag();
		
	}
	
	
	/*
	 * 获取某个view：根据id，获取某个view对象
	 * 将界面上用到的所有控件对象放在集合中；如果在集合中，则直接取；如果不在集合中，重新创建view对象，放入集合；
	 * T是view的子类：只要赋值个一个view的子类，都可以
	 */
	public <T extends View> T getView(int viewId){
		View v=mViews.get(viewId);
		//如果集合中没有v
		if(v==null){
			//找到控件:赋值给v
			v=mConvertView.findViewById(viewId);
			//将v加入集合
			mViews.put(viewId, v);
		}
		return (T)v;
	}
	
	
	//获取convertview对象
	public View getConvertView(){
		return mConvertView;
	}
	
	/*
	 * 所有的viewholder，通过id找到控件，再通过对象属性取出值，可以将两步封装成一步；
	 */
	
	//将text内容赋值给viewId的控件上；
	public void setText(int viewId,String text){
		//转换成textview
		TextView tv=getView(viewId);
		//将内容赋值给textview
		tv.setText(text);
	}
	
	//将图片内容显示在imageview上
	public void setImageResource(int viewId,int drawableId){
	ImageView iv=getView(viewId);
	iv.setImageResource(drawableId);
	}
	
	

}
