package com.newxuetu.weight.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newxuetu.R;
import com.newxuetu.utils.StringUtil;

/**
 * Created by liang on 2016/10/23.
 */

public class TitleBar extends LinearLayout implements View.OnClickListener {


    private TextView titlebarback;
    private TextView titlebartitle;
    private TextView titlebarshare;
    private TextView titlebarsetting;
    private View view;

    private Context context;

    private boolean isFinish = true;

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.weight_titlbar, null);
        this.titlebarsetting = (TextView) view.findViewById(R.id.titlebar_setting);
        this.titlebarshare = (TextView) view.findViewById(R.id.titlebar_share);
        this.titlebartitle = (TextView) view.findViewById(R.id.titlebar_title);
        this.titlebarback = (TextView) view.findViewById(R.id.titlebar_back);

        titlebarback.setOnClickListener(this);

    }

    //设置每个图标的点击事件
    public void showSettingBut(){
        titlebarsetting.setVisibility(VISIBLE);
    }

    public void hintSettingBut(){
        titlebarsetting.setVisibility(GONE);
    }

    public void showShareBut () {
        titlebarshare.setVisibility(VISIBLE);
    }

    public void hintShareBut () {
        titlebarshare.setVisibility(GONE);
    }


    //设置图标显示方法

    //设置返回按钮的点击事件
    public void leftFinishLisenaer(final Activity activity){
        titlebarback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
//                activity.overridePendingTransition();
            }
        });
    }


    public void setBackImgResoure (int resId) {
        titlebarback.setBackgroundResource(resId);
    }

    public void setText(String text,int resId,boolean isLeftORReght){
        if (!StringUtil.stringIsEntry(text)) {
            titlebartitle.setText(text);
        }
        Drawable drawable= getResources().getDrawable(resId);
        if (isLeftORReght){
            titlebarback.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
        } else {
            titlebarback.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);

        }
    }


    public View  getView(){
        return view;
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.titlebar_back) {
            if(isFinish){
                ((Activity) context).finish();
            }
        }
    }
}
