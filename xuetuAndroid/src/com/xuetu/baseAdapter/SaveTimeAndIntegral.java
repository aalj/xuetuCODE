package com.xuetu.baseAdapter;

import android.widget.EditText;
import android.widget.TextView;
import com.xuetu.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SaveTimeAndIntegral {
	
	
	
	
	
	private String url = null;
	private HttpUtils httpUtils;
	
	private String integral="5";
	
	
	public void saveStudyTime()
	{
		httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("time", "20");
		requestParams.addBodyParameter("integral", "5");
		httpUtils.send(HttpMethod.POST, url,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				
			}
		} );
		
	}
	
	
}
