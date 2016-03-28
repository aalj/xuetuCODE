package com.xuetu.ui;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.PersonalStudyTimeAll;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class PaiHangBangActivity extends Baseactivity implements OnClickListener {
	ListView listView_paihangbang;
	TitleBar titlebar;
	MyBasesadapter<PersonalStudyTimeAll> myadapter;
	List<PersonalStudyTimeAll> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pai_hang_bang);
		listView_paihangbang = (ListView) findViewById(R.id.listView_paihangbang);
		titlebar = (TitleBar) findViewById(R.id.backtoperson_paihangbang);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
		getDate();
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
	 * 数据的获取
	 * 
	 */

	public void getDate() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "PaiHangbangServlet";
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<PersonalStudyTimeAll>>() {
				}.getType();
				datas = gson.fromJson(arg0.result, type);
				loadView();

			}
		});
	}

	/**
	 * 加载listv
	 * 
	 */
	int i = 1;

	public void loadView() {
		boolean temp = true;
		listView_paihangbang.setAdapter(
				myadapter = new MyBasesadapter<PersonalStudyTimeAll>(this, datas, R.layout.listitem_of_paihangbang) {

					@Override
					public void convert(ViewHodle viewHolder, PersonalStudyTimeAll item) {

						Log.i("TAG", "66666666666" + datas.size());
						Log.i("TAG", "```````````" + i);
						viewHolder.setText(R.id.paihangbangxuhao, item.getTimePosition() + "");
						i++;
						viewHolder.SetUrlImage(R.id.head_paihangbang,
								GetHttp.getHttpBCL() + item.getStudent().getStuIma());
						viewHolder.setText(R.id.nicheng_paihangbang, item.getStudent().getStuName());
						viewHolder.setText(R.id.number_paihangbang, item.getTimeAll());

					}
				});

	}

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
		oks.setText("梁军给您丢螺母辣");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

}
