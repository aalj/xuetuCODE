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
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
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
import android.widget.TextView;

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
	private int btn_heigt;
	private int btn_width;
	private static final int UPDATE = 0;
	private int activity_width=0;
	private int activity_height=0;
	private int activity_top=0;
	int lm;
	int tm;
	int b ;
	Display display; 
	TextView tv;
	int fHeight ;
	int ii;
	
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
		layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT  
                 );  
		display = getActivity().getWindowManager().getDefaultDisplay();  
        System.out.println("height="+display.getHeight());  
        System.out.println("width="+display.getWidth());  
        activity_width=display.getHeight();
		activity_height=display.getWidth();
//		activity_top=display.
		
		tv=(TextView) getActivity().findViewById(R.id.person_tv);
		tv.getHeight();
		System.out.println("tv>>>>>>>>>>>>>>>>>>"+tv.getHeight());
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
		
//		Rect rect = new Rect();
//		System.out.println(ii);
//		getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		
		
		
		  layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT  
                 );  
          btn_center.setLayoutParams(layoutParams);  
          btn_center.setOnTouchListener(this);  
          
          new Thread()  
          {  
             @Override  
               public void run()  
               {  
              synchronized(this)  
              {  
               try  
               {  
                wait(1000); //1秒  
               }  
               catch (InterruptedException e)  
               {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
               }  
              }  
                 Message msg = new Message();
                 msg.what = UPDATE;
                 ii= getActivity().findViewById(R.id.main_title).getHeight();
         		 
         		 b = tv.getHeight();
                 btn_heigt=btn_center.getHeight();
         		 btn_width=btn_center.getWidth();
         		 fHeight = getFheight();
         		 String s=btn_heigt+"|"+btn_width+"|"+b+"|"+fHeight+"|"+ii;
         		 msg.obj = s;
         		 handler.sendMessage(msg);
               }  
          }.start();  
          
		return view;

	}
	
	private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO 接收消息并且去更新UI线程上的控件内容
            if (msg.what == UPDATE) {
          	btn_center.setVisibility(View.VISIBLE) ;
            	String s=(String) msg.obj;
            	System.out.println(s);
            	String []ss=s.split("\\|".toString());
            	String h = ss[0];
            	String w = ss[1];
            	String bo= ss[2];
            	String fh= ss[3];
            	String is= ss[4];
            	b=Integer.parseInt(bo);
            	fHeight=Integer.parseInt(fh);
            	ii=Integer.parseInt(is);
            	lm=(fHeight-ii-Integer.parseInt(h)+b)/2;
            	tm=(activity_height-Integer.parseInt(h))/2;
//            	layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            	
//            	layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            	layoutParams.leftMargin=tm;
            	layoutParams.topMargin =lm;
	            
            	btn_center.setLayoutParams(layoutParams);  
            	
		        PropertyValuesHolder visible = PropertyValuesHolder.ofFloat("alpha", 0F,1F);
		    	ObjectAnimator.ofPropertyValuesHolder(btn_center,visible).setDuration(1500).start();
	            
            }
            super.handleMessage(msg);
        }
    };
	
	
	/**
	 *   图片返回  动画移动效果
	 * @param vie
	 */
	public void moveBack (View vie)
	{
		PropertyValuesHolder up =    PropertyValuesHolder.ofFloat("translationY",-80F, 0);
		PropertyValuesHolder down =  PropertyValuesHolder.ofFloat("translationY",80F, 0);
		PropertyValuesHolder right = PropertyValuesHolder.ofFloat("translationX",80F, 0);
		PropertyValuesHolder left =  PropertyValuesHolder.ofFloat("translationX",-80F, 0);
		PropertyValuesHolder round = PropertyValuesHolder.ofFloat("rotation",120F, 0);
		
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
//		
		PropertyValuesHolder up =    PropertyValuesHolder.ofFloat("translationY", 0,-80F);
		PropertyValuesHolder down =  PropertyValuesHolder.ofFloat("translationY", 0,80F);
		PropertyValuesHolder right = PropertyValuesHolder.ofFloat("translationX", 0,80F);
		PropertyValuesHolder left =  PropertyValuesHolder.ofFloat("translationX", 0,-80F);
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
	public boolean onTouch( View v, MotionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("高>>>>>"+btn_center.getHeight());
		
//		layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT );   
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
        	layoutParams.leftMargin=tm;
        	layoutParams.topMargin =lm;
//        	 layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE); 
//    		 layoutParams.addRule(RelativeLayout.BELOW ,RelativeLayout.TRUE); 
    		 
	        btn_center.setLayoutParams(layoutParams);  
         	
         	 
             break;  
         case MotionEvent.ACTION_POINTER_DOWN: 
             break;  
         case MotionEvent.ACTION_POINTER_UP:  
             break;  	
         case MotionEvent.ACTION_MOVE:  
//        	 final int yy = (int) event.getRawY();  
//        	 final int xx = (int) event.getRawX();  
//        	 layoutParams.leftMargin=xx;
//        	 
//        	 System.out.println("yy"+xx);
//        	 System.out.println("yy"+yy);
//        	 System.out.println("y>>"+img5.getY());
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
	
	public int getFheight()
	{
		int aa=0;
		aa = root.getHeight();
		return aa;
	}

}
