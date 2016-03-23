package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExchangeCouponActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.ima_stone)
	ImageView ima_stone;
	@ViewInject(R.id.dianjiadejutixingxi)
	TextView dianjiadejutixingxi;
	@ViewInject(R.id.zhekoudejutixingxi)
	TextView zhekoudejutixingxi;
	@ViewInject(R.id.shiyongdidianshuoming)
	TextView shiyongdidianshuoming;
	@ViewInject(R.id.xuyaojifen)
	TextView xuyaojifen;
	@ViewInject(R.id.titleBar1)
	TitleBar titleBar1;
	Coupon coupon = null;
	Student student = null;
	HttpUtils httpUtils = new HttpUtils();
	ProgressDialog progressDialog = null;
	int jiFen = 0;
	// 检查是否登录
	boolean isLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exchange_coupon);
		ViewUtils.inject(this);
		student = ((XueTuApplication) getApplication()).getStudent();
		coupon = (Coupon) getIntent().getSerializableExtra("exchange");
		intiView();
	}

	private void intiView() {
		showDengdai();
		// 加载数据
		jiaZaiShuJu();

		if (student.getStuId() > 0) {
			isLogin = true;
		}

		titleBar1 = (TitleBar) findViewById(R.id.title_my);
		titleBar1.setLeftLayoutClickListener(this);
		BitmapUtils bitmapUtils = new BitmapUtils(this);
		bitmapUtils.display(ima_stone, GetHttp.getHttpLJ() + coupon.getStoreName().getStoImg());
		dianjiadejutixingxi.setText(coupon.getCouName());
		zhekoudejutixingxi.setText(coupon.getCouPrice() + "  折");
		xuyaojifen.setText(coupon.getCoouRedeemPoints() + " 分");
		shiyongdidianshuoming.setText(coupon.getStoreName().getStoAddress());

	}

	private void jiaZaiShuJu() {
		showDengdai();
		String url = GetHttp.getHttpLJ() + "DedaoJIFen";
		RequestParams pra = new RequestParams();
		pra.addBodyParameter("stuid", student.getStuId() + "");
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				jiFen = Integer.parseInt(arg0.result);
				if (progressDialog != null)
					progressDialog.dismiss();
			}
		});

	}

	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(ExchangeCouponActivity.this, "", "正在加载...");
			progressDialog.show();
		}
	}

	// 用于标记积分是否充足
	boolean temp = false;

	private void myShowDialog(int countjifen, int xuyaojifen) {
		AlertDialog.Builder builder = new Builder(this);
		if (countjifen >= xuyaojifen) {
			builder.setTitle("点击确定兑换");
			temp = true;
		} else {
			builder.setTitle("快去学习吧！！！");
			temp = false;

		}
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_duihuan, null);
		TextView shengyujifen = (TextView) view.findViewById(R.id.shengyujifen);
		shengyujifen.setText(countjifen + "");
		TextView xuyaojifent = (TextView) view.findViewById(R.id.xuyaojifen);
		xuyaojifent.setText(xuyaojifen + "");
		builder.setCancelable(false);
		builder.setView(view);
		builder.setPositiveButton("兑换", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (isLogin) {
					if (temp) {
						MyCoupon mycoupon = new MyCoupon();
						mycoupon.setCoupon(coupon);
						mycoupon.setMycouExchangeTime(new Date(System.currentTimeMillis()));
						mycoupon.setStudent(student);
						saveMycoupon(mycoupon);
						Intent intent= new Intent();
						intent.setClass(ExchangeCouponActivity.this, TheCollectionOfYouHuiJuanActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(), "兑换失败，积分不足", 1).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "请先登录", 1).show();
				}
				dialog.dismiss();

			}

		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.create().show();

	}

	@Override
	public void onBackPressed() {
		if (progressDialog != null)
			progressDialog.dismiss();
		super.onBackPressed();
	}
	
	
	private void saveMycoupon(MyCoupon myCoupon) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String json = gson.toJson(myCoupon);

			json = URLEncoder.encode(json, "utf-8");

			String url = GetHttp.getHttpLJ() + "SaveMycoupon";
			RequestParams pra = new RequestParams();
			pra.addBodyParameter("mycoupon", json);
			httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// myShowDialog(countjifen, coupon.getCoouRedeemPoints());

					if ("ok".equals(arg0.result)) {
						Toast.makeText(getApplicationContext(), "兑换成功", 1).show();
					} else if ("no".equals(arg0.result)) {
						Toast.makeText(getApplicationContext(), "兑换失败，请检查网络", 1).show();

					}

				}
			});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 点击兑换
	public void onclick(View v) {
		//再次加载数据
		jiaZaiShuJu();
		myShowDialog(jiFen, coupon.getCoouRedeemPoints());
	}

	@Override
	public void onClick(View v) {
		finish();

	}
}
