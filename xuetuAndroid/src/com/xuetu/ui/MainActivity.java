package com.xuetu.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.adapter.FragmentViewPageAdapter;
import com.xuetu.entity.Student;
import com.xuetu.fragment.CouponFrag;
import com.xuetu.fragment.FindFrag;
import com.xuetu.fragment.HomePageFrag;
import com.xuetu.fragment.PersonalFrag;
import com.xuetu.fragment.QuestionFrag;
import com.xuetu.view.TitleBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {
	private FragmentManager manager = null;
	private FragmentTransaction beginTransaction = null;
	private TitleBar title;
	@ViewInject(R.id.coupon_fra)
	FrameLayout coupon_fra;
	@ViewInject(R.id.find_fra)
	FrameLayout find_fra;
	@ViewInject(R.id.home_fra)
	FrameLayout home_fra;
	@ViewInject(R.id.question_page)
	FrameLayout question_page;
	@ViewInject(R.id.personal_page)
	FrameLayout personal_page;

	FrameLayout[] fragmeLayout = null;

	@ViewInject(R.id.coupon_tv)
	TextView coupon_tv;
	@ViewInject(R.id.find_tv)
	TextView find_tv;
	@ViewInject(R.id.home_tv)
	TextView home_tv;
	@ViewInject(R.id.ques_tv)
	TextView ques_tv;
	@ViewInject(R.id.person_tv)
	TextView person_tv;

	TextView[] textView = null;

	HomePageFrag homePageFrag;
	FindFrag findFrag;
	CouponFrag couponFrag;
	QuestionFrag questionFrag;
	PersonalFrag personalFrag;

	Fragment[] fragments = null;

	@ViewInject(R.id.frag_page)
	ViewPager viewPage;

	private String[] titlename = { "券", "发现", "首页", "问题", "我" };

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
		// TODO Auto-generated method stub
		super.onResume();
		// 登录成功传值出来
		Intent intent = this.getIntent();
		Student student = (Student) intent.getSerializableExtra("KEY");

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
		int intExtra = getIntent().getIntExtra("page", -1);

		fragments = new Fragment[5];
		fragmeLayout = new FrameLayout[5];
		textView = new TextView[5];
		title = (TitleBar) findViewById(R.id.main_title);
		//
		title.setRightLayoutClickListener(this);
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

		// beginTransaction.add(R.id.frag_page,
		// homePageFrag).add(R.id.frag_page, findFrag).add(R.id.frag_page,
		// couponFrag)
		// .add(R.id.frag_page, questionFrag).add(R.id.frag_page, personalFrag);
		//
		// beginTransaction.hide(couponFrag).hide(findFrag).hide(personalFrag).hide(questionFrag).show(homePageFrag)
		// .commit();
		fragmeLayout[2].setSelected(true);
		textView[2].setTextColor(0xff44A6D5);
		beginTransaction = null;

		title.setTitle("首页");

		viewPage.setAdapter(new FragmentViewPageAdapter(getSupportFragmentManager(), fragments));
		viewPage.addOnPageChangeListener(this);
		if (intExtra == 0) {
			//当跳转到当前页面的时候显示优惠券页面
			viewPage.setCurrentItem(0);
			Log.i("TAG", "这里是否有进入这里的东西");
		} else {
			//显示主页
			viewPage.setCurrentItem(2);
		}
		viewPage.setOffscreenPageLimit(5);
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
		case R.id.coupon_fra:// 搜索页面点击
			title.shoerightLayout(View.INVISIBLE);
			viewPage.setCurrentItem(0);
//			title.shoelayout(View.VISIBLE);
			break;
		case R.id.find_fra:// 发现页面
			title.shoerightLayout(View.INVISIBLE);
			viewPage.setCurrentItem(1);
//			title.shoelayout(View.VISIBLE);
			break;
		case R.id.home_fra:// 首页面
			title.shoerightLayout(View.INVISIBLE);
			viewPage.setCurrentItem(2);
//			title.shoelayout(View.VISIBLE);
			break;
		case R.id.question_page:// 问题页面
			title.shoerightLayout(View.INVISIBLE);
			viewPage.setCurrentItem(3);
//			title.shoelayout(View.GONE);
//			title.shoelayout(View.INVISIBLE);
//			title.shoelayout(View.GONE);
			// title.shoelayout(View.INVISIBLE);
			break;
		case R.id.personal_page:// 个人中心页面
			title.shoerightLayout(View.VISIBLE);
			title.setRightImageResource(R.drawable.more_setting);
			viewPage.setCurrentItem(4);
//			title.shoelayout(View.VISIBLE);
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
			if (arg0 == 4) {
				title.shoerightLayout(View.VISIBLE);
				title.setRightImageResource(R.drawable.more_setting);
			} else {
				title.shoerightLayout(View.INVISIBLE);
			}
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
			title.setTitle(titlename[arg0]);
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
		Toast.makeText(getApplicationContext(), "sesdfsdf", 0).show();

	}
}
