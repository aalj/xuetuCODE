package com.newxuetu.http;

/**
 * Created by liang on 2016/10/16.
 */

public interface HttpListener {
    void onSeccess(String response);
    void onFail(String msg);
}
