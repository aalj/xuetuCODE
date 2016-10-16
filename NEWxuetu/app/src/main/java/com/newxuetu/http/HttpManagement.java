package com.newxuetu.http;

import com.newxuetu.Bean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liang on 2016/10/16.
 * 这个类里面主要是要写出能用到的所有网络请求的方式
 * post请求
 * |-带参数的请求
 * |-不带参数的请求
 * get请求
 * |-带参数的请求
 * |-不打参数的请求
 * 图片上传
 * 文件下载
 */

public class HttpManagement {

    private static HttpManagement mInstance = null;


    public static HttpManagement getInstance()
    {
        if (mInstance == null)
        {
            synchronized (HttpManagement.class)
            {
                if (mInstance == null)
                {
                    mInstance = new HttpManagement();
                }
            }
        }
        return mInstance;
    }



    //同步get请求

    //异步异步请求

    //同步post请求
    //异步post请求

    /**
     * 异步请求网络
     * @param url
     * @param param
     * @param httpListener
     */
    public void _postAsyn(String url, String param, final HttpListener httpListener){
        OkHttpClient okHttpClient = new OkHttpClient();
        //声明传给服务器的是json格式的数据
        RequestBody body = RequestBody.create(JSON,param);
//        Log.e("Stone", "_postAsyn: ");

        final Request request = new Request.Builder()
                .url(url)
//                .get(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.e("Stone", "onResponse: ---" );
//                httpListener.onFail(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Bean event = new Bean();
                event.name=response.body().string();
                EventBus.getDefault().post(event);
                httpListener.onSeccess(response.body().toString());
            }
        });


    }




    public void GET() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("")
                .get()//请求方式，默认不写就是get请求方式
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

        } else {
            throw new IOException(response.toString() + "");
        }


    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String POST() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("platform", "android")
                .add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX")
                .build();


        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder()
                .url("")
                .post(body)//设置请求体
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }




}



