package com.xuetu.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;

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
import okhttp3.internal.framed.FrameReader;

/**
 * 封装Okhttp，方便调用
 * Created by Stone on 2016/7/17.
 */
public class MyOkhttp {

    private OkHttpClient client;
    private volatile static MyOkhttp myOkhttp = null;
    private final String TAG = MyOkhttp.class.getSimpleName();
    private Handler handler = null;
    //提交json数据
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    //提交字符串
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");

    //需要使用单利模式
    private MyOkhttp() {
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 采用单利模式获取对象
     *
     * @author Stone
     * created at 2016/7/17  17:51
     */
    public static MyOkhttp getInstance() {
        MyOkhttp instance
                = null;
        if (myOkhttp == null) {
            synchronized (MyOkhttp.class) {
                if (instance == null) {
                    instance = new MyOkhttp();
                }

            }
        }
        return instance;
    }


    /**
     * 同步请求，
     *
     * @param url
     * @return
     */
    public String syncGetByURL(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过请求返回字符串
     * @param url
     * @param callBack
     */
    public void asyncStringByURL(String url, final Func1 callBack) {
        Request request = new Request.Builder().url(url).build();

        //enqueue请求队列
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    onSuccessJsonStringMethod(response.body().string(), callBack);
                }
            }
        });
    }

    /**
     * 通过气请求返回一个对象
     * @param url
     * @param callBack
     */
    public void asyncObjectByURL(String url, final Func4 callBack) {
        final Request request = new Request.Builder().url("").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonObjectMethod(response.body().string(), callBack);
                }
            }
        });


    }

    /**
     * 通过请求返回一个byte数组
     * @param url
     * @param callBack
     */
    public void asyncByteByURL(String url, final Func2 callBack) {
        final Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    OnSuccessByteMethod(response.body().bytes(),callBack);
                }
            }
        });


    }

    /**
     * 通过请求返回一张图片
     * @param url
     * @param callBack
     */
    public void asyncDownLoadImageByURL(String url, final Func3 callBack){
        Request request= new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            if(response!=null&&response.isSuccessful()){
                byte[] bytes = response.body().bytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                callBack.onResponse(bitmap);
            }
            }
        });
    }


    /**
     * 提交表单数据
     * @param url
     * @param params
     * @param callBack
     */
    public void  sendComplesForm(String url, Map<String,String> params, final Func4 callBack){
        FormBody.Builder from_builder = new FormBody.Builder();//表单对象，包含以input开始的对象，以html表单为主
        if(params!=null&&params.isEmpty()){
            for (Map.Entry<String ,String> entry:params.entrySet()
                 ) {from_builder.add(entry.getKey(),entry.getValue());

            }
        }
        RequestBody requestBody = from_builder.build();
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
onSuccessJsonObjectMethod(response.body().string(),callBack);
            }
        });
    }




    /**
     * 请求返回的结果是json字符串
     *
     * @param jsonValue
     * @param callback
     */
    private void onSuccessJsonStringMethod(final String jsonValue, final Func1 callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(jsonValue);
                }
            }
        });
    }

    /**
     * 返回的结果是一个JSON 对象
     *
     * @param jsonValue
     * @param callback
     */
    private void onSuccessJsonObjectMethod(final String jsonValue, final Func4 callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    try {
                        callback.onResponse(new JSONObject(jsonValue));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 请求返回的是byte数组
     *
     * @param data
     * @param callBack
     */
    private void OnSuccessByteMethod(final byte[] data, final Func2 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onResponse(data);
                }
            }
        });

    }


    interface Func1 {
        void onResponse(String resilt);
    }

    interface Func2 {
        void onResponse(byte[] result);
    }

    interface Func3 {
        void onResponse(Bitmap bitmap);
    }

    interface Func4 {
        void onResponse(JSONObject jsonObject);
    }

}
