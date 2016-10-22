package com.newxuetu.base;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.newxuetu.R;
import com.newxuetu.weight.SystemBarTintManager;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTint();
    }


    public void setSystemBarTint() {
        //判断当前版本，然后对不同的版本进行对大于版本号19的进行特殊处理
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus( );
        }
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setStatusBarTintResource(R.color.colorPrimary);

    }
    @TargetApi(19)
    private void setTranslucentStatus(){
        Window win = getWindow();
        WindowManager.LayoutParams layoutParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            layoutParams.flags |= bits;

        win.setAttributes(layoutParams);
    }


}
