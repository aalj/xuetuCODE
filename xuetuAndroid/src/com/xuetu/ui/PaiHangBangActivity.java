package com.xuetu.ui;

import java.util.ArrayList;
import java.util.List;

import com.xuetu.R;
import com.xuetu.fragment.QuestionAnswerBillBoardFragment;
import com.xuetu.fragment.StudyTimeBillBoardFragment;
import com.xuetu.view.TitleBar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class PaiHangBangActivity extends Baseactivity implements OnClickListener, OnPageChangeListener {
//	ListView listView_paihangbang;
	TitleBar titlebar;
//	MyBasesadapter<PersonalStudyTimeAll> myadapter;
//	List<PersonalStudyTimeAll> datas;
	TextView studytimefragment;
	TextView answerfragment;
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
	StudyTimeBillBoardFragment studytimebillboardFragment;
	QuestionAnswerBillBoardFragment questionsnswerbillBoardFragment;
	/**
	 * //当前选中的项
	 */
	int currenttab = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pai_hang_bang);
		// listView_paihangbang = (ListView)
		// findViewById(R.id.listView_paihangbang);
		titlebar = (TitleBar) findViewById(R.id.backtoperson_paihangbang);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
		studytimefragment = (TextView) findViewById(R.id.studytimefragment);
		answerfragment = (TextView) findViewById(R.id.answerfragment);
		studytimefragment.setOnClickListener(this);
		answerfragment.setOnClickListener(this);
		mViewPager = (ViewPager) findViewById(R.id.viewpager_paihangbang);

		fragmentList = new ArrayList<>();
		studytimebillboardFragment = new StudyTimeBillBoardFragment();
		questionsnswerbillBoardFragment = new QuestionAnswerBillBoardFragment();

		fragmentList.add(studytimebillboardFragment);
		fragmentList.add(questionsnswerbillBoardFragment);
		studytimefragment.setBackgroundResource(R.drawable.kb5);
		mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
		mViewPager.addOnPageChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			finish();
			break;
		case R.id.right_layout:
			showShare();
		default:
			break;
		}

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
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		Log.i("TAG", "xixxiii----" + arg0);
		if (arg0 == 0) {
			studytimefragment.setBackgroundResource(R.drawable.kb7);
			answerfragment.setBackgroundDrawable(null);
		} else if (arg0 == 1) {
			studytimefragment.setBackgroundDrawable(null);
			answerfragment.setBackgroundResource(R.drawable.kb1);
		}

	}
	// /**
	// * 数据的获取
	// *
	// */
	//
	// public void getDate() {
	// HttpUtils httpUtils = new HttpUtils();
	// String url = GetHttp.getHttpBCL() + "PaiHangbangServlet";
	// httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
	//
	// @Override
	// public void onFailure(HttpException arg0, String arg1) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0) {
	// Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd
	// HH:mm:ss").create();
	// Type type = new TypeToken<List<PersonalStudyTimeAll>>() {
	// }.getType();
	// datas = gson.fromJson(arg0.result, type);
	// loadView();
	//
	// }
	// });
	// }
	//
	// /**
	// * 加载listv
	// *
	// */
	// int i = 1;
	//
	// public void loadView() {
	// boolean temp = true;
	// listView_paihangbang.setAdapter(
	// myadapter = new MyBasesadapter<PersonalStudyTimeAll>(this, datas,
	// R.layout.listitem_of_paihangbang) {
	//
	// @Override
	// public void convert(ViewHodle viewHolder, PersonalStudyTimeAll item) {
	//
	// Log.i("TAG", "66666666666" + datas.size());
	// Log.i("TAG", "```````````" + i);
	// viewHolder.setText(R.id.paihangbangxuhao, item.getTimePosition() + "");
	// i++;
	// viewHolder.SetUrlImage(R.id.head_paihangbang,
	// GetHttp.getHttpBCL() + item.getStudent().getStuIma());
	// viewHolder.setText(R.id.nicheng_paihangbang,
	// item.getStudent().getStuName());
	// viewHolder.setText(R.id.number_paihangbang, item.getTimeAll());
	//
	// }
	// });
	//
	// }

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://www.bestsnail.com/");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("螺母给您丢梁军辣");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("？？？？？是不是是傻");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

}
