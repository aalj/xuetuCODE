/**
 * MyAdapter.java
 * Adapter
 *
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015-8-7 		疯子
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.adapter;
/**
 * 适配器
 */
import java.util.List;

import com.xuetu.R;
import com.xuetu.ui.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * ClassName:MyAdapter
 *
 * @author   疯子
 * @version  
 * @since    Ver 1.1
 * @Date	 2015-8-7		上午11:25:59
 *
 * @see 	 
 */
public class MyAdapter extends PagerAdapter {
	 //数据源
		List<View>  mylist;
		//页面对象
		Activity myactivity;
		SharedPreferences sp =null;
		
		public MyAdapter(Activity outactivity,List<View>  outlist) {
		mylist = outlist;
		myactivity =outactivity;
		sp = myactivity.getSharedPreferences("config",Activity.MODE_PRIVATE);
		}

        //获取数据源的数量 必须的方法
		@Override
		public int getCount() {
			
			return mylist.size();
			
		}
		//移除View  必须的方法，销毁移除 之前的View
		@Override
		public void destroyItem(View container, int position, Object object) {
			//移除当前位置的View对象
			 ((ViewPager) container).removeView(mylist.get(position));
			
		}
//初始化View    
      @Override
    public Object instantiateItem(View container, int position) {
    	  ((ViewPager) container).addView(mylist.get(position), 0);
          if (position==mylist.size()-1) {
			
        	  ImageView bt=(ImageView) container.findViewById(R.id.imageView1);
        	  bt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					sp.edit().putBoolean("isFirst", false).commit();
					goHome();
					
				}
			});
        	  
        	  
		}
    	return mylist.get(position);
    	
    }
      //必须的方法    
      //判断传进来的对象是否是view对象 ViewPager只能切换View对象
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return (arg0==arg1);
			
		}
		
	
		
	public void goHome(){
		
		myactivity.startActivity(new Intent(myactivity,LoginActivity.class));
		myactivity.finish();
	}	

	}