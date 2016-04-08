package com.xuetu;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xuetu.entity.Coupon;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;

public class ShowerweiActivity extends Activity {
	 ImageView ima;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_showerwei);
		Coupon coupon = (Coupon) getIntent().getSerializableExtra("name");
		ima = (ImageView) findViewById(R.id.ima);
		createImage(coupon.getCouID()+"",ima);
		
		
	}

	
	// 生成QR图
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
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			finish();
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
}
