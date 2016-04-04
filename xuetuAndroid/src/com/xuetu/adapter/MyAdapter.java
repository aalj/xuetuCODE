/**
 * MyAdapter.java
 * Adapter
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015-8-7 		����
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.adapter;
/**
 * ������
 */
import java.util.List;

import com.xuetu.R;
import com.xuetu.ui.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;


/**
 * ClassName:MyAdapter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   ����
 * @version  
 * @since    Ver 1.1
 * @Date	 2015-8-7		����11:25:59
 *
 * @see 	 
 */
public class MyAdapter extends PagerAdapter {
	 //����Դ
		List<View>  mylist;
		//ҳ�����
		Activity myactivity;
		
		
		public MyAdapter(Activity outactivity,List<View>  outlist) {
		mylist = outlist;
		myactivity =outactivity;
		}

        //��ȡ����Դ������ ����ķ���
		@Override
		public int getCount() {
			
			// TODO Auto-generated method stub
			return mylist.size();
			
		}
		//�Ƴ�View  ����ķ����������Ƴ� ֮ǰ��View
		@Override
		public void destroyItem(View container, int position, Object object) {
			//�Ƴ���ǰλ�õ�View����
			 ((ViewPager) container).removeView(mylist.get(position));
			
		}
//��ʼ��View    
      @Override
    public Object instantiateItem(View container, int position) {
    	  ((ViewPager) container).addView(mylist.get(position), 0);
          if (position==mylist.size()-1) {
			
        	  Button bt=(Button) container.findViewById(R.id.button1);
        	  bt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					goHome();
					
				}
			});
        	  
        	  
		}
    	return mylist.get(position);
    	
    }
      //����ķ���    
      //�жϴ������Ķ����Ƿ���view���� ViewPagerֻ���л�View����
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			// TODO Auto-generated method stub
			return (arg0==arg1);
			
		}
		
	
		
	public void goHome(){
		
		myactivity.startActivity(new Intent(myactivity,LoginActivity.class));
		myactivity.finish();
	}	

	}