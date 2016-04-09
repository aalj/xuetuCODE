package com.xuetu.ui;

import java.text.SimpleDateFormat;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lidroid.xutils.BitmapUtils;
import com.xuetu.R;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.MyCoupon;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YouHuiJuanInfomationActivity extends Baseactivity implements OnClickListener {
	CircleImageView head;
	CircleImageView head_CircleImageView;
	TextView youhuijuanxingxi;
	TextView youxiaoqixian;
	TextView dianjiadejutixingxi;
	TextView zhekoudejutixingxi;
	TextView shiyongdidianshuoming;
	TextView button_lijishiyong;
	TextView xuyaodejifen;
	TextView shiyongjieshao;
	ImageView backToDecember;
	MyCoupon mycoupon;
	Coupon coupon;

	/**
	 * 我的优惠劵具体信息
	 * 
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_hui_juan_infomation);
		head = (CircleImageView) findViewById(R.id.head);
		youhuijuanxingxi = (TextView) findViewById(R.id.youhuijuanxingxi);
		youxiaoqixian = (TextView) findViewById(R.id.youxiaoqixian);
		dianjiadejutixingxi = (TextView) findViewById(R.id.dianjiadejutixingxi);
		zhekoudejutixingxi = (TextView) findViewById(R.id.zhekoudejutixingxi);
		shiyongdidianshuoming = (TextView) findViewById(R.id.shiyongdidianshuoming);
		head_CircleImageView = (CircleImageView) findViewById(R.id.head_CircleImageView);
		button_lijishiyong = (TextView) findViewById(R.id.button_lijishiyong);
		xuyaodejifen = (TextView) findViewById(R.id.xuyaodejifen);
		shiyongjieshao = (TextView) findViewById(R.id.shiyongjieshao);
		backToDecember = (ImageView) findViewById(R.id.imageView_backToDecember);
		getDatasAndsetDates();
	}
	
	
	// 生成二维码图
    private void createImage(String str,ImageView view) {
    	Bitmap bitmap = null;
        try {
            // 需要引入core包
            QRCodeWriter writer = new QRCodeWriter();
            Log.i("TAG", "生成的文本：" + str);
            // 把输入的文本转为二维码
            BitMatrix martix = writer.encode(str, BarcodeFormat.QR_CODE,200, 200);
            System.out.println("w:" + martix.getWidth() + "h:" + martix.getHeight());
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(str,BarcodeFormat.QR_CODE, 200, 200, hints);
            int[] pixels = new int[200 * 200];
            for (int y = 0; y < 200; y++) {
                for (int x = 0; x < 200; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * 200 + x] = 0xff000000;
                    } else {
                        pixels[y * 200 + x] = 0xffffffff;
                    }

                }
            }

            bitmap = Bitmap.createBitmap(200, 200,
                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, 200, 0, 0, 200,200);
            view.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
	
	
	
	public void getDatasAndsetDates() {
		Intent intent = this.getIntent();
		Bundle extras = intent.getExtras();

		mycoupon = (MyCoupon) extras.getSerializable("MyCoupon");
		coupon = mycoupon.getCoupon();
		BitmapUtils bitmapUtils = new BitmapUtils(this);
		bitmapUtils.display(head, GetHttp.getHttpBCL() + coupon.getCouIma());
		BitmapUtils bitmapUtils1 = new BitmapUtils(this);
		bitmapUtils1.display(head_CircleImageView, GetHttp.getHttpBCL() + coupon.getStoreName().getStoImg());
		xuyaodejifen.setText(mycoupon.getCoupon().getCoouRedeemPoints()+"");
		youhuijuanxingxi.setText(coupon.getCouName());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		String time_format = dateFormat.format(coupon.getConValidity());
		youxiaoqixian.setText(time_format);
		dianjiadejutixingxi.setText(coupon.getStoreName().getStoName());
		shiyongjieshao.setText(coupon.getCouInfo());
		if(!(mycoupon.getUserState().getUstaID() == 2)){
			button_lijishiyong.setClickable(false);
			button_lijishiyong.setFocusable(false);
		}
		button_lijishiyong.setOnClickListener(this);
		zhekoudejutixingxi.setText(coupon.getCouPrice() + "");
		shiyongdidianshuoming.setText("要在" + coupon.getStoreName().getStoAddress()
				+ "吃，如果有来生，要做一棵树， 站成永恒， 没有悲欢的姿势。一半在土里安详， 一半在风里飞扬， 一半洒落阴凉， 一半沐浴阳光。 非常沉默，非常骄傲， 从不依靠，从不寻找。");

	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.imageView_backToDecember:
			
			finish();
			break;
		/** 跳转到店家 **/
		case R.id.head_CircleImageView:
			Intent intent1 = new Intent();
			intent1.setClass(this, StoneNameActivity.class);
			intent1.putExtra("coupon", coupon);
			startActivity(intent1);
			finish();
			break;
		default:
			break;
		}

	}


	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), ShowerweiActivity.class);
		intent.putExtra("name", coupon);
		startActivity(intent);
		
	}

 
}
