package com.newxuetu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newxuetu.http.HttpListener;
import com.newxuetu.http.HttpManagement;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.Button buttonPanel;
    private android.widget.LinearLayout activitymain;
    private android.widget.TextView te;
    private static String TAG   = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activitymain = (LinearLayout) findViewById(R.id.activity_main);
        this.te = (TextView) findViewById(R.id.te);
        this.buttonPanel = (Button) findViewById(R.id.buttonPanel);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }



        buttonPanel.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onEventMainThread (Bean bean) {
        Log.e(TAG, "onEventMainThread: =-=-=-=-"+bean.name);
        te.setText(bean.name);
        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        Log.e("Stone", "onClick: ");
        HttpManagement instance = HttpManagement.getInstance();
        instance._postAsyn("http://gank.io/api/data/Android/10/1", "", new HttpListener() {
            @Override
            public void onSeccess(String response) {
                Log.e("Stone", "onSeccess: "+response);

//                Toast.makeText(MainActivity.this, "cehnggong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
