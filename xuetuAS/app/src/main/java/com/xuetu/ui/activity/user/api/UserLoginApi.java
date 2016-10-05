package com.xuetu.ui.activity.user.api;

import com.loopj.android.http.AsyncHttpClient;
import com.xuetu.constant.BaseApi;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.StringUtlis;

import org.json.JSONObject;

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
        //1. 拼接访问参数
        JSONObject jsonObject = new JSONObject();
        //2. 调用请求网路的方法请求网络
        StringUtlis.jsonObjectAddPram(jsonObject,"telephone",tel);
        StringUtlis.jsonObjectAddPram(jsonObject,"pwd",pwd);



    }





}
