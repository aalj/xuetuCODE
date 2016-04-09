package com.xuetu.ui;

import java.util.ArrayList;
import java.util.List;

import com.xuetu.R;
import com.xuetu.adapter.MyAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class YinDaoyeActivity extends Activity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_yin_daoye);
//	}
	
	// viewpager
		public ViewPager pager;
		// 数据源
		List<View> mylist;
		int currentIndex = 0;
		ImageView[] dots;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_yin_daoye);
			viewInit();
			initDots();
		}


		// 控件初始化
		@SuppressWarnings("deprecation")
		public void viewInit() {
			mylist = new ArrayList<View>();
			pager = (ViewPager) findViewById(R.id.viewpager);
			LayoutInflater inflater = getLayoutInflater();
			mylist.add(inflater.inflate(R.layout.layout0, null));
			mylist.add(inflater.inflate(R.layout.layout1, null));
			mylist.add(inflater.inflate(R.layout.layout2, null));
			mylist.add(inflater.inflate(R.layout.layout3, null));
			// 初始化适配器
			pager.setAdapter(new MyAdapter(YinDaoyeActivity.this, mylist));

			pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {

					setCurrentDot(arg0);

				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					
					// TODO Auto-generated method stub
					
				}
			});
		}
		//白点的初始化
		//初始化 小点
			private void initDots() {
				LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

				dots = new ImageView[mylist.size()];

				for (int i = 0; i < mylist.size(); i++) {
					dots[i] = (ImageView) ll.getChildAt(i);
					//白点
					dots[i].setEnabled(true);
				}

				currentIndex = 0;
				//黑点
				dots[currentIndex].setEnabled(false);
			}
			
			//设置当前小点
			private void setCurrentDot(int position) {
				if (position < 0 || position > mylist.size() - 1
						|| currentIndex == position) {
					return;
				}

				dots[position].setEnabled(false);
				dots[currentIndex].setEnabled(true);

				currentIndex = position;
			}

		

	
}
