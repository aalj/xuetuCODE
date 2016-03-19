package com.xuetu.ui;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Question_ask extends Activity implements OnClickListener{
	//声明变量
	int stuId = 0;
	String quesText = null;
//	String quesImg = null;
	int acpoNum = 0;
	int subId = 0;
	long quesTime;
	TitleBar title;
	Context mContext;
	//声明页面控件
	EditText et_question = null;
	Button btn_ask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_ask);
		title = (TitleBar) findViewById(R.id.title_questionAsk);
		title.setLeftLayoutClickListener(this);
		findViewById(R.id.btn_ask).setOnClickListener(this);
		findViewById(R.id.tv_sub).setOnClickListener(this);
		mContext = this;
		init();
		
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.btn_ask:
					ask();
					break;
				case R.id.tv_sub:
					Toast.makeText(Question_ask.this, "xueke", 1).show();
					showPopupWinow(v);
					break;
				default:
					finish();
					break;
				}
	}
	public void ask(){
		Log.i("hehe", "subquestion");
		//学生id
		stuId = 1;
		//问题信息
		quesText = et_question.getText().toString();
		//提问时间
		quesTime = System.currentTimeMillis();
		//图片
//		quesImg = ;
		//积分
		acpoNum =10;
		//学科
		subId = 1;
		HttpUtils hutils = new HttpUtils(5000);
		String url = GetHttp.getHttpLC()+"SubmitQuestion";
		hutils.configCurrentHttpCacheExpiry(1000);
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuId",String.valueOf(stuId));
		params.addBodyParameter("quesText",et_question.getText().toString());
		params.addBodyParameter("quesTime",quesTime+"");
		params.addBodyParameter("acpoNum",String.valueOf(acpoNum));
		params.addBodyParameter("subId",String.valueOf(subId));
		hutils.send(HttpMethod.POST,url,params,new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "failed");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 存入数据成功，跳回问题列表页面
				Toast.makeText(Question_ask.this, "submit success"+arg0.result, 1).show();
				Question_ask.this.finish();
			}} );
	}
	
	//初始化控件
	public void init(){
		et_question = (EditText) findViewById(R.id.et_question);
		btn_ask = (Button) findViewById(R.id.btn_ask);
	}
	
	
	
	
	//显示showPopupWindow
	
	public void showPopupWinow(View v){
		 // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.subject_pop, null);
        // 设置按钮的点击事件
//      
//        Button button = (Button) contentView.findViewById(R.id.button1);
//        button.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "button is pressed",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
				// 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}

           
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.ic_launcher));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
     // popupWindow.showAtLocation(view.getParent(),ce, x, y)

	}
	
}
