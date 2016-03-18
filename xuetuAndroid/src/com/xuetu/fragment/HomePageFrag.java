/**
 * HomePageFrag.java
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

import com.xuetu.R;
import com.xuetu.ui.TimerActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

/**
 * ClassName:HomePageFrag Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015年11月8日 下午3:46:04
 *
 * @see
 * 
 */
public class HomePageFrag extends Fragment implements OnTouchListener{
	
	Button btn_up,btn_down,btn_center ;
	ImageView img1,img2,img3,img4,img5,img6,imgup,imgdown;
	View view;
	RelativeLayout.LayoutParams layoutParams;
	ViewGroup root;
	private int _x;
	private int _y;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.home_page_frag, null);
		
		view.findViewById(R.id.home_btn_up).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),TimerActivity.class);
				startActivity(intent);
			}
		});
		
		root=(ViewGroup) view.findViewById(R.id._root);
		btn_center = (Button) view.findViewById(R.id.imageButton1);
		img1 = (ImageView) view.findViewById(R.id.imageView7);
		img2 = (ImageView) view.findViewById(R.id.imageView3);
		img3 = (ImageView) view.findViewById(R.id.imageView5);
		img4 = (ImageView) view.findViewById(R.id.imageView4);
		img5 = (ImageView) view.findViewById(R.id.imageView6);
		img6 = (ImageView) view.findViewById(R.id.imageView8);
		
		imgup= (ImageView) view.findViewById(R.id.imageView1);
		imgdown= (ImageView) view.findViewById(R.id.imageView2);
		
//		btn_center.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				move(v);
//			}
//		});
		
		 RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT  
                 );  
          layoutParams.leftMargin=230;
          layoutParams.topMargin =  445;
          btn_center.setLayoutParams(layoutParams);  
          btn_center.setOnTouchListener(this);  
		
		return view;

	}
	private ViewGroup findViewById(int root2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *   图片返回  动画移动效果
	 * @param vie
	 */
	public void moveBack (View vie)
	{
		PropertyValuesHolder up =    PropertyValuesHolder.ofFloat("translationY", -100F,0);
		PropertyValuesHolder down =  PropertyValuesHolder.ofFloat("translationY", 100F,0);
		PropertyValuesHolder right = PropertyValuesHolder.ofFloat("translationX", 100F,0);
		PropertyValuesHolder left =  PropertyValuesHolder.ofFloat("translationX", -100F,0);
		PropertyValuesHolder round = PropertyValuesHolder.ofFloat("rotation", 120F,0);
		
		ObjectAnimator.ofPropertyValuesHolder(img1, up,left,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img2, left,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img3, left,down,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img4, up,right,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img5, right,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img6, down,right,round).setDuration(1000).start();
		
	}
	
	
	
	/**
	 * 动画移动效果
	 * @param vie
	 */
	public void move(View vie)
	{
//		img1 = (ImageView) view.findViewById(R.id.imageView7);
//		img2 = (ImageView) view.findViewById(R.id.imageView3);
//		img3 = (ImageView) view.findViewById(R.id.imageView5);
//		img4 = (ImageView) view.findViewById(R.id.imageView4);
//		img5 = (ImageView) view.findViewById(R.id.imageView6);
//		img6 = (ImageView) view.findViewById(R.id.imageView8);
//		
//		imgup= (ImageView) view.findViewById(R.id.imageView1);
//		imgdown= (ImageView) view.findViewById(R.id.imageView2);
		
//		PropertyValuesHolder right = PropertyValuesHolder.ofFloat("rotation", 0,360F);
		PropertyValuesHolder up =    PropertyValuesHolder.ofFloat("translationY", 0,-100F);
		PropertyValuesHolder down =  PropertyValuesHolder.ofFloat("translationY", 0,100F);
		PropertyValuesHolder right = PropertyValuesHolder.ofFloat("translationX", 0,100F);
		PropertyValuesHolder left =  PropertyValuesHolder.ofFloat("translationX", 0,-100F);
		PropertyValuesHolder round = PropertyValuesHolder.ofFloat("rotation", 0,120F);
		
		
		ObjectAnimator.ofPropertyValuesHolder(img1, up,left,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img2, left,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img3, left,down,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img4, up,right,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img5, right,round).setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img6, down,right,round).setDuration(1000).start();
		
	}

	
	/**
	 * 触摸效果,我也不懂,能用就行了
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT );   
		final int Y = (int) event.getRawY();  
		 switch (event.getAction() & MotionEvent.ACTION_MASK) {  
         case MotionEvent.ACTION_DOWN:  
        	 
        	 move(v);
        	 
             RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();  
                       
//             _x = X - lParams.leftMargin;  
             _y = Y - lParams.topMargin; 
             System.out.println("y>>"+_y);
             break;  
         case MotionEvent.ACTION_UP:  
         	
        	moveBack(v);
         	layoutParams.leftMargin=230;
	        layoutParams.topMargin =  445;
	        btn_center.setLayoutParams(layoutParams);  
         	
         	 
             break;  
         case MotionEvent.ACTION_POINTER_DOWN: 
             break;  
         case MotionEvent.ACTION_POINTER_UP:  
             break;  
         case MotionEvent.ACTION_MOVE:  
             RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams(); 
                      
//             layoutParams.addRule(RelativeLayout.CENTER_VERTICAL,0);
//	            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,0);
             
//             layoutParams.leftMargin = X - _x;     //控制按钮在X轴移动
             layoutParams.topMargin = Y - _y;  		//控制按钮在Y轴移动
//             layoutParams.rightMargin = -250;  
//             layoutParams.bottomMargin = -250;  
             v.setLayoutParams(layoutParams);  
             break;  
         }  
         root.invalidate();  
         return true;  
		
		
	}

}
