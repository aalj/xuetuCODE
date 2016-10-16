package com.xuetu.ui;

import java.text.SimpleDateFormat;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.utils.Log;
import com.xuetu.R;
import com.xuetu.entity.JiFenMingXi;
import com.xuetu.utils.GetHttp;
import com.xuetu.widget.CircleImageView;
import com.xuetu.widget.TitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class JiFenXiangQingActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.jifen)
	TextView jifen;
	@ViewInject(R.id.circleImageView1)
	CircleImageView circleImageView1;
	@ViewInject(R.id.leixing)
	TextView leixing;
	@ViewInject(R.id.time)
	TextView time;
	@ViewInject(R.id.textView1)
	TextView textView1;

	TitleBar titleBar1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ji_fen_xiang_qing);
		JiFenMingXi jiFenMingxi = (JiFenMingXi) getIntent().getSerializableExtra("jifenIten");
		ViewUtils.inject(this);
//		jifen.setText(jiFenMingxi.getUnmpuint() + "");
		titleBar1 = (TitleBar) findViewById(R.id.titleBar1);
		titleBar1.setLeftLayoutClickListener(this);
//		Log.i("TAG", "jiFenMingxi.getText()------->>>>" + jiFenMingxi.getText());
		switch (jiFenMingxi.getImgUrl()) {
		case "1":// 加载问题图片
			textView1.setText("提问题");
			jifen.setText("- "+jiFenMingxi.getUnmpuint() + "");
			circleImageView1.setImageResource(R.drawable.ic_home_widget_qa);

			break;
		case "2":// 加载答案图片
			textView1.setText("回答问题");
			jifen.setText("+ "+jiFenMingxi.getUnmpuint() + "");
			circleImageView1.setImageResource(R.drawable.ic_item_tishiyu);
			break;
		case "3":// 加载学习时间图片
			textView1.setText("学习");
			jifen.setText("+ "+jiFenMingxi.getUnmpuint() + "");
			circleImageView1.setImageResource(R.drawable.ic_home_widget_study);

			break;
		default:// 加载网络图片
			textView1.setText("兑换优惠券");
			jifen.setText("- "+jiFenMingxi.getUnmpuint() + "");
			BitmapUtils item = new BitmapUtils(this);
			item.display(circleImageView1, GetHttp.getHttpLJ() + jiFenMingxi.getImgUrl());

			break;
		}
		leixing.setText(jiFenMingxi.getText());
		Log.i("TAG", "jiFenMingxi.getText()--------><><><><<<><><><>" + jiFenMingxi.getText());
		// 显示创建时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time.setText(dateFormat.format(jiFenMingxi.getTime()));

	}

	@Override
	public void onClick(View v) {
		finish();
	}

}
