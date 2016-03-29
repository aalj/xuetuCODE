package com.xuetu;

import java.text.SimpleDateFormat;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.utils.Log;
import com.xuetu.entity.JiFenMingXi;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class JiFenXiangQingActivity extends Activity {

	@ViewInject(R.id.jifen)
	TextView jifen;
	@ViewInject(R.id.circleImageView1)
	CircleImageView circleImageView1;
	@ViewInject(R.id.leixing)
	TextView leixing;
	@ViewInject(R.id.time)
	TextView time;
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ji_fen_xiang_qing);
		JiFenMingXi jiFenMingxi = (JiFenMingXi) getIntent().getSerializableExtra("jifenIten");
		ViewUtils.inject(this);
		jifen.setText(jiFenMingxi.getUnmpuint() + "");
		

		Log.i("TAG", "jiFenMingxi.getText()------->>>>" + jiFenMingxi.getText());
		switch (jiFenMingxi.getImgUrl()) {
		case "1":// 加载问题图片
			circleImageView1.setImageResource(R.drawable.ic_home_widget_qa);

			break;
		case "2":// 加载答案图片

			circleImageView1.setImageResource(R.drawable.ic_item_tishiyu);
			break;
		case "3":// 加载学习时间图片
			circleImageView1.setImageResource(R.drawable.ic_home_widget_study);

			break;
		default:// 加载网络图片
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

	
}
