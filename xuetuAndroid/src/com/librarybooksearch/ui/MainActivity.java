package com.librarybooksearch.ui;

import com.librarybooksearch.fragment.CouponFrag;
import com.librarybooksearch.fragment.FindFrag;
import com.librarybooksearch.fragment.HomePageFrag;
import com.librarybooksearch.fragment.PersonalFrag;
import com.librarybooksearch.fragment.QuestionFrag;
import com.xuetu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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
public class MainActivity extends FragmentActivity {
	private FragmentManager manager = null;
	private FragmentTransaction beginTransaction = null;
	private TextView title = null;
	LinearLayout search_page = null;
	LinearLayout search_page1 = null;
	LinearLayout search_page2 = null;
	LinearLayout recommend_page = null;
	LinearLayout personal_page = null;

	HomePageFrag homePageFrag;
	FindFrag findFrag;
	CouponFrag couponFrag;
	QuestionFrag questionFrag;
	PersonalFrag personalFrag;
	Fragment[] fragments = null;
	
	
	
	int showFragment = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		title = (TextView) findViewById(R.id.title);
		search_page = (LinearLayout) findViewById(R.id.search_page);
		search_page1 = (LinearLayout) findViewById(R.id.search_page1);
		search_page2 = (LinearLayout) findViewById(R.id.search_page2);
		recommend_page = (LinearLayout) findViewById(R.id.recommend_page);
		personal_page = (LinearLayout) findViewById(R.id.personal_page);
		manager = getSupportFragmentManager();
		beginTransaction = manager.beginTransaction();

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

		beginTransaction.add(R.id.frag_page, homePageFrag)
				.add(R.id.frag_page, findFrag)
				.add(R.id.frag_page, couponFrag)
				.add(R.id.frag_page, questionFrag)
				.add(R.id.frag_page, personalFrag);

		beginTransaction.hide(couponFrag)
		.hide(findFrag)
		.hide(personalFrag)
		.hide(questionFrag)
		.show(homePageFrag)
				.commit();

		
		beginTransaction = null;

		search_page.setBackgroundResource(R.drawable.bg_content_card);
		title.setText("首页");
		search_page1.setBackgroundResource(R.drawable.bg_content_card);
		search_page2.setBackgroundResource(R.drawable.btn_app_download_d);
		recommend_page.setBackgroundResource(R.drawable.bg_content_card);
		personal_page.setBackgroundResource(R.drawable.bg_content_card);
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
		beginTransaction = manager.beginTransaction();
		int index = 2;
		switch (v.getId()) {
		case R.id.search_page:// 搜索页面点击
			index =0;
			// CouponFrag couponFrag = new CouponFrag();
			// beginTransaction.replace(R.id.frag_page, couponFrag);
//			beginTransaction.commit();
//			beginTransaction = null;

			title.setText("券");
			search_page.setBackgroundResource(R.drawable.btn_app_download_d);

			search_page1.setBackgroundResource(R.drawable.bg_content_card);
			search_page2.setBackgroundResource(R.drawable.bg_content_card);
			recommend_page.setBackgroundResource(R.drawable.bg_content_card);
			personal_page.setBackgroundResource(R.drawable.bg_content_card);
			break;
		case R.id.search_page1:// 发现页面
			index=1;
			// FindFrag findFrag = new FindFrag();
			// beginTransaction.replace(R.id.frag_page, findFrag);
//			beginTransaction.commit();
//			beginTransaction = null;

			search_page.setBackgroundResource(R.drawable.bg_content_card);
			title.setText("发现");
			search_page1.setBackgroundResource(R.drawable.btn_app_download_d);
			search_page2.setBackgroundResource(R.drawable.bg_content_card);
			recommend_page.setBackgroundResource(R.drawable.bg_content_card);
			personal_page.setBackgroundResource(R.drawable.bg_content_card);
			break;
		case R.id.search_page2:// 首页面
			index=2;
			// HomePageFrag homePageFrag = new HomePageFrag();
			// beginTransaction.replace(R.id.frag_page, homePageFrag);
//			beginTransaction.commit();
//			beginTransaction = null;

			search_page.setBackgroundResource(R.drawable.bg_content_card);
			title.setText("首页");
			search_page1.setBackgroundResource(R.drawable.bg_content_card);
			search_page2.setBackgroundResource(R.drawable.btn_app_download_d);
			recommend_page.setBackgroundResource(R.drawable.bg_content_card);
			personal_page.setBackgroundResource(R.drawable.bg_content_card);
			break;
		case R.id.recommend_page:// 问题页面
			index =3;
			// QuestionFrag questionFrag = new QuestionFrag();
			// beginTransaction.replace(R.id.frag_page, questionFrag);
			title.setText("问题");
//			beginTransaction.commit();
//			beginTransaction = null;
			search_page.setBackgroundResource(R.drawable.bg_content_card);
			search_page1.setBackgroundResource(R.drawable.bg_content_card);
			search_page2.setBackgroundResource(R.drawable.bg_content_card);
			recommend_page.setBackgroundResource(R.drawable.btn_app_download_d);
			personal_page.setBackgroundResource(R.drawable.bg_content_card);
			break;
		case R.id.personal_page:// 个人中心页面
			index = 4;
			// PersonalFrag personalFrag = new PersonalFrag();
			// beginTransaction.replace(R.id.frag_page, personalFrag);
			title.setText("个人中心");
//			beginTransaction.commit();
//			beginTransaction = null;
			search_page.setBackgroundResource(R.drawable.bg_content_card);
			search_page1.setBackgroundResource(R.drawable.bg_content_card);
			search_page2.setBackgroundResource(R.drawable.bg_content_card);
			recommend_page.setBackgroundResource(R.drawable.bg_content_card);
			personal_page.setBackgroundResource(R.drawable.btn_app_download_d);
			break;

		default:
			break;

		}
		
		if(showFragment!= index){
			FragmentTransaction trx = getSupportFragmentManager()
					.beginTransaction();
			
			trx.hide(fragments[showFragment]);
			if (!fragments[index].isAdded()) {
				trx.add(R.id.frag_page, fragments[index]);
			}
			trx.show(fragments[index]).commit();
			
		}
		
		showFragment = index;
		
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
}
