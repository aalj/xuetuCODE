package com.xuetu.ui;

import java.util.ArrayList;
import java.util.List;

import com.xuetu.R;
import com.xuetu.fragment.PersonQuestionCollectionFrag;
import com.xuetu.fragment.YouHuiJuanFrag;
import com.xuetu.view.TitleBar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WoDeShouCangActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	protected static final String TAG = "TAG";
	// ListView listview;
	// List<FavoritesCoupons> datas = new ArrayList<FavoritesCoupons>();
	// Student student;
	// MyBasesadapter<FavoritesCoupons> myadapter;
	TitleBar titlebar;
	TextView youhuijuanfragment;
	TextView questionfragment;
	/**
	 * 作为页面容器的ViewPager
	 */
	ViewPager mViewPager;
	/**
	 * 页面集合
	 */
	List<Fragment> fragmentList;
	/**
	 * 两个Fragment页面
	 */
	YouHuiJuanFrag youhuijuanfrag;
	PersonQuestionCollectionFrag personquestioncollectionfrag;

	/**
	 * //当前选中的项
	 */
	int currenttab = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wo_de_shou_cang);
		// listview = (ListView) findViewById(R.id.listView1);
		titlebar = (TitleBar) findViewById(R.id.backtoperson);
		titlebar.setLeftLayoutClickListener(this);
		youhuijuanfragment = (TextView) findViewById(R.id.youhuijuanfragment);
		questionfragment = (TextView) findViewById(R.id.questionfragment);
		youhuijuanfragment.setOnClickListener(this);
		questionfragment.setOnClickListener(this);
		mViewPager = (ViewPager) findViewById(R.id.viewpager_wodeshoucang);

		fragmentList = new ArrayList<>();
		youhuijuanfrag = new YouHuiJuanFrag();
		personquestioncollectionfrag = new PersonQuestionCollectionFrag();
		fragmentList.add(youhuijuanfrag);
		fragmentList.add(personquestioncollectionfrag);
		youhuijuanfragment.setBackgroundResource(R.drawable.kb1);
		mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(this);
		// getSupportFragmentManager().beginTransaction().add(R.id.framelayout,
		// youHuiJuanFrag).commit();
		// listview.setOnItemClickListener(this);
		// getCoupon();
	}

	/**
	 * 定义自己的ViewPager适配器。 也可以使用FragmentPagerAdapter。关于这两者之间的区别，可以自己去搜一下。
	 */
	class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

		public MyFrageStatePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		/**
		 * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
		 */
		@Override
		public void finishUpdate(ViewGroup container) {
			super.finishUpdate(container);// 这句话要放在最前面，否则会报错
			// 获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
			int currentItem = mViewPager.getCurrentItem();
			if (currentItem == currenttab) {
				return;
			}

			currenttab = mViewPager.getCurrentItem();
		}

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			finish();
			break;
		case R.id.youhuijuanfragment:
			changeView(0);
			break;
		case R.id.questionfragment:
			changeView(1);
			break;

		default:
			break;
		}
	}

	// 手动设置ViewPager要显示的视图
	private void changeView(int desTab) {
		mViewPager.setCurrentItem(desTab, true);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		Log.i("TAG", "xixxiii----" + arg0);
		if (arg0 == 0) {
			youhuijuanfragment.setBackgroundResource(R.drawable.kb1);
			questionfragment.setBackgroundDrawable(null);
		} else if (arg0 == 1) {
			youhuijuanfragment.setBackgroundDrawable(null);
			questionfragment.setBackgroundResource(R.drawable.kb1);
		}

	}

}
