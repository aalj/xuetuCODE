package com.xuetu.constant;

import com.xuetu.XueTuApplication;
import com.xuetu.http.HttpManager;

/**
 * Created by liang on 2016/10/4.
 */

public class BaseApi {
    public HttpManager httpManager;

    public BaseApi() {
        this.httpManager = HttpManager.getInstance();
    }

    /**
     * 服务器地址
     */
    public static String urllj = "http://192.168.0.12:8080/xuetuWeb/";
//    public static String urllj = "http://www.bestsnail.com:8965/xuetuWeb/";
    public static String urlbcl = "http://www.bestsnail.com:8965/xuetuWeb/";
    public static String urllc = "http://www.bestsnail.com:8965/xuetuWeb/";
    public static String urlky = "http://www.bestsnail.com:8965/xuetuWeb/";


}
