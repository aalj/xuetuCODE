package com.xuetu.ui.activity.user.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.constant.BaseApi;

import com.xuetu.entity.Student;
import com.xuetu.http.HttpListener;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.StringUtlis;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by liang on 2016/10/5.
 * 访问网路的类
 */

public class UserLoginApi extends BaseApi {

    /**
     * 用户登录
     * @param tel
     * @param pwd
     */
    public void  userLogin(String tel,String pwd){
        String uri = urllj+"LoginAndroid";

        Map<String ,String > param =  new HashMap<>();
        param.put("telephone",tel);
        param.put("pwd",pwd);



    httpManager.doPostRequest(uri, param, new HttpListener() {
        @Override
        public void onSuccess(String response) {
            Student student = null;
            if(!StringUtlis.isEmpty(response)){
                Log.e("Stone", "onSuccess: ---->>>"+response.toString() );
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                student=gson.fromJson(response,Student.class);
            }
            EventBus.getDefault().post(student);

        }

        @Override
        public void onFailure(String msg) {
            Student student = new Student();
            EventBus.getDefault().post(student);
        }
    });

    }





}
