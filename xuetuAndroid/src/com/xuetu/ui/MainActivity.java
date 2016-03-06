package com.xuetu.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.baseAdapter.FragmentViewPageAdapter;
import com.xuetu.fragment.CouponFrag;
import com.xuetu.fragment.FindFrag;
import com.xuetu.fragment.HomePageFrag;
import com.xuetu.fragment.PersonalFrag;
import com.xuetu.fragment.QuestionFrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 
 * ClassName:MainActivity Function: 首页
 *
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015 2015年11月6日 下午8:24:57
 *
 * @see
 */
public class MainActivity extends FragmentActivity implements OnPageChangeListener {
	private FragmentManager manager = null;
	private FragmentTransaction beginTransaction = null;
	private TextView title ;
	@ViewInject(R.id.coupon_fra)
	FrameLayout coupon_fra ;
	@ViewInject(R.id.find_fra)
	FrameLayout find_fra;
	@ViewInject(R.id.home_fra)
	FrameLayout home_fra ;
	@ViewInject(R.id.question_page)
	FrameLayout question_page ;
	@ViewInject(R.id.personal_page)
	FrameLayout personal_page ;
	@ViewInject(R.id.coupon_tv)
	
	FrameLayout[] fragmeLayout = null;
	
	@ViewInject(R.id.coupon_tv)
	TextView  coupon_tv ;
	@ViewInject(R.id.find_tv)
	TextView find_tv ;
	@ViewInject(R.id.home_tv)
	TextView home_tv;
	@ViewInject(R.id.ques_tv)
	TextView ques_tv;
	@ViewInject(R.id.person_tv)
	TextView person_tv ;
	
	TextView[] textView = null;
	
	HomePageFrag homePageFrag;
	FindFrag findFrag;
	CouponFrag couponFrag;
	QuestionFrag questionFrag;
	PersonalFrag personalFrag;
	
	Fragment[] fragments = null;

	@ViewInject(R.id.frag_page)
	ViewPager viewPage;
	
	private String[] titlename = {"券","发现","首页","问题","我"}; 
	
	int showFragment = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		initView();
	}

	/**
	 * 
	 * initView:(页面初始化方法)
	 *
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	private void initView() {
		fragments = new Fragment[5];
		fragmeLayout = new FrameLayout[5];
		textView = new TextView[5]; 
		title = (TextView) findViewById(R.id.title);
		
		manager = getSupportFragmentManager();
		beginTransaction = manager.beginTransaction();

		fragmeLayout[0] = coupon_fra;
		fragmeLayout[1] = find_fra;
		fragmeLayout[2] = home_fra;
		fragmeLayout[3] = question_page;
		fragmeLayout[4] = personal_page;
		
		
		textView[0] = coupon_tv;
		textView[1] = find_tv;
		textView[2] = home_tv;
		textView[3] = ques_tv;
		textView[4] = person_tv;

		homePageFrag = new HomePageFrag();
		findFrag = new FindFrag();
		couponFrag = new CouponFrag();
		questionFrag = new QuestionFrag();
		personalFrag = new PersonalFrag();
		fragments[0] = couponFrag;
		fragments[1] = findFrag;
		fragments[2] = homePageFrag;
		fragments[3] = questionFrag;
		fragments[4] = personalFrag;

//		beginTransaction.add(R.id.frag_page, homePageFrag).add(R.id.frag_page, findFrag).add(R.id.frag_page, couponFrag)
//				.add(R.id.frag_page, questionFrag).add(R.id.frag_page, personalFrag);
//
//		beginTransaction.hide(couponFrag).hide(findFrag).hide(personalFrag).hide(questionFrag).show(homePageFrag)
//				.commit();
		fragmeLayout[2].setSelected(true);
		textView[2].setTextColor(0xff44A6D5);
		beginTransaction = null;

		title.setText("首页");
		
		viewPage.setAdapter(new FragmentViewPageAdapter(getSupportFragmentManager(), fragments));
		viewPage.addOnPageChangeListener(this);
		viewPage.setCurrentItem(2);
		
	}

	/**
	 * 
	 * onclick:(点击事件监听器)
	 *
	 * @param v
	 *            设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.coupon_fra:// 搜索页面点击
			viewPage.setCurrentItem(0);
			break;
		case R.id.find_fra:// 发现页面
			viewPage.setCurrentItem(1);
			break;
		case R.id.home_fra:// 首页面
			viewPage.setCurrentItem(2);
			break;
		case R.id.question_page:// 问题页面
			viewPage.setCurrentItem(3);
			break;
		case R.id.personal_page:// 个人中心页面
			viewPage.setCurrentItem(4);
			break;

		default:
			break;

		}



	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		if (showFragment != arg0) {
		for (int i = 0; i < fragmeLayout.length; i++) {
			if (i != arg0) {
				fragmeLayout[i].setSelected(false);
				
				textView[i].setTextColor( 0xff323232);
			}
		}
		FragmentTransaction trx = getSupportFragmentManager().beginTransaction();

		trx.hide(fragments[showFragment]);
		fragmeLayout[arg0].setSelected(true);
		textView[arg0].setTextColor(0xff44A6D5);
		title.setText(titlename[arg0]);
		if (!fragments[arg0].isAdded()) {
			trx.add(R.id.frag_page, fragments[arg0]);
		}
		trx.show(fragments[arg0]).commit();

	}

	showFragment = arg0;
//
	}
}
