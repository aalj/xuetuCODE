package com.xuetu.base;

import com.xuetu.R;
import com.xuetu.utils.ActivityColector;
import com.xuetu.widget.SystemBarTintManager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

public class Baseactivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActivityColector.addActivity(this);

		setSystemBarTint();
		
	}

	public void setSystemBarTint(){
		//当前版本大于19
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			setTranslucentStatus(true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.light_blue);
	}
	@TargetApi(19)
	private void setTranslucentStatus(boolean bo){
		Window win = getWindow();
		WindowManager.LayoutParams layoutParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if(bo){
			layoutParams.flags |= bits;
		}else{
			layoutParams.flags &= ~bits;
		}
		win.setAttributes(layoutParams);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityColector.removeActivity(this);
	}

}
