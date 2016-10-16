package com.xuetu.http;


import android.content.Context;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpManager {
	private OkHttpClient okHttpClient=null;
	private volatile static HttpManager httpManager = null;
	//提交json数据
	private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
	//提交字符串
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");


	private HttpManager() {
		okHttpClient = new OkHttpClient();

	}

	//使用单例模式得到请求的对象
	public static HttpManager getInstance(){
		if(httpManager==null){
			synchronized (MyOkhttp.class){
				httpManager = new HttpManager();
			}
		}
		return httpManager;
	}


	public void doPostRequest(String url, Map<String,String> params, final HttpListener httpListener){
		RequestBody requestBody  = params2RequestBody(params);

		Request request = new Request.Builder().post(requestBody).url(url).build();
		okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				if(httpListener != null){
					httpListener.onFailure(e.getMessage());
				}else{
					//non thing
				}
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if(httpListener != null){
					httpListener.onSuccess(response.body().string());
				}else{
					//no thing
				}
			}
		});


	}



	public RequestBody params2RequestBody(Map<String,String> params){
		FormBody.Builder from_builder = new FormBody.Builder();
		if(params!=null&&!params.isEmpty()){
			for (Map.Entry<String ,String> entry:params.entrySet()
					) {from_builder.add(entry.getKey(),entry.getValue());

			}
		}
		RequestBody requestBody = from_builder.build();
		return  requestBody;
	}





}
