package com.xuetu.http;

public interface HttpListener {
    void onSuccess(String response);

    void onFailure(String msg);
}
