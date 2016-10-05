package com.xuetu.utils;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by liang on 2016/10/5.
 * 工具类用于处理字符串
 */

public class StringUtlis {
    /**
     * 判断字符串是否为空或者是空串
     *
     * @param a
     * @return
     */
    public static boolean isEmpty(String a) {
        if (TextUtils.isEmpty(a)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 把特定的字符串拼接成json格式数据
     * @param jsonObject
     * @param key
     * @param value
     */
    public static void jsonObjectAddPram(JSONObject jsonObject ,String key,String value){
        try {
            jsonObject.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
