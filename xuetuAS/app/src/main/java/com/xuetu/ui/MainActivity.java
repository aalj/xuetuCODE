package com.xuetu.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.adapter.FragmentViewPageAdapter;
import com.xuetu.base.Baseactivity;
import com.xuetu.entity.Student;
import com.xuetu.fragment.CouponFrag;
import com.xuetu.fragment.FindFrag;
import com.xuetu.fragment.PersonalFrag;
import com.xuetu.fragment.QuestionFrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
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
public class MainActivity extends Baseactivity implements OnPageChangeListener, OnClickListener {
	private FragmentManager manager = null;
	private FragmentTransaction beginTransaction = null;
	@ViewInject(R.id.coupon_fra)
	ImageView coupon_fra;
	@ViewInject(R.id.find_fra)
	ImageView find_fra;
	@ViewInject(R.id.question_page)
	ImageView question_page;
	@ViewInject(R.id.personal_page)
	ImageView personal_page;
	/**底部导航栏的图片的数组*/
	ImageView[] fragmeLayout = null;

	@ViewInject(R.id.coupon_tv)
	TextView coupon_tv;
	@ViewInject(R.id.find_tv)
	TextView home_tv;
	@ViewInject(R.id.ques_tv)
	TextView ques_tv;
	@ViewInject(R.id.person_tv)
	TextView person_tv;

	TextView[] textView = null;

	FindFrag findFrag;
	CouponFrag couponFrag;
	QuestionFrag questionFrag;
	PersonalFrag personalFrag;

	Fragment[] fragments = null;

	@ViewInject(R.id.frag_page)
	ViewPager viewPage;

	private String[] titlename = { "首页","券",   "问题", "我" };

	int showFragment = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		Log.i("TAG", "MainActivity");
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 登录成功传值出来
		Intent intent = this.getIntent();
		Student student = (Student) intent.getSerializableExtra("KEY");
		int flag = getIntent().getIntExtra("flag", 0);
		if (flag == -1) {
			Log.i("TAG", "mainactivity页面就哈哈哈哈   ");
			viewPage.setCurrentItem(0);
		}

	}

	/**
	 * 
	 * initView:(页面初始化方法)
	 *

	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	private void initView() {
		int intExtra = getIntent().getIntExtra("page", -1);

		fragments = new Fragment[4];
		fragmeLayout = new ImageView[4];
		textView = new TextView[4];
		
		manager = getSupportFragmentManager();
		beginTransaction = manager.beginTransaction();

		fragmeLayout[0] = find_fra;
		fragmeLayout[1] = coupon_fra;
		fragmeLayout[2] = question_page;
		fragmeLayout[3] = personal_page;

		textView[0] = home_tv;
		textView[1] = coupon_tv;
		textView[2] = ques_tv;
		textView[3] = person_tv;

		findFrag = new FindFrag();
		couponFrag = new CouponFrag();
		questionFrag = new QuestionFrag();
		personalFrag = new PersonalFrag();
		
		
		
		fragments[0] = findFrag;
		fragments[1] = couponFrag;
		fragments[2] = questionFrag;
		fragments[3] = personalFrag;

		fragmeLayout[0].setSelected(true);
		textView[0].setTextColor(0xff44A6D5);
		beginTransaction = null;

//		title.setTitle("首页");

		viewPage.setAdapter(new FragmentViewPageAdapter(getSupportFragmentManager(), fragments));
		viewPage.setOnPageChangeListener(this);
		
		if (intExtra == 0) {
			// 当跳转到当前页面的时候显示优惠券页面
			viewPage.setCurrentItem(1);
		} else {
			// 显示主页
			viewPage.setCurrentItem(0);
		}
		// 显示缓存内容
		viewPage.setOffscreenPageLimit(4);
	}

	public void setViepage(int i) {
		viewPage.setCurrentItem(i);
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
		case R.id.coupon_fra_li:// 券页面
			viewPage.setCurrentItem(1);
			break;
		case R.id.find_fra_li:// 首页页面
			viewPage.setCurrentItem(0);
			break;
		case R.id.question_page_li:// 问题页面
			viewPage.setCurrentItem(2);
			break;
		case R.id.personal_page_li:// 个人中心页面
			viewPage.setCurrentItem(3);
			break;

		default:
			break;

		}

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

					textView[i].setTextColor(0xff323232);
				}
			}
			FragmentTransaction trx = getSupportFragmentManager().beginTransaction();

			trx.hide(fragments[showFragment]);
			fragmeLayout[arg0].setSelected(true);
			textView[arg0].setTextColor(0xff44A6D5);
			if (!fragments[arg0].isAdded()) {
				trx.add(R.id.frag_page, fragments[arg0]);
			}
			trx.show(fragments[arg0]).commit();

		}

		showFragment = arg0;
		//
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, SettingActivity.class);
		startActivity(intent);
		finish();

	}
}
