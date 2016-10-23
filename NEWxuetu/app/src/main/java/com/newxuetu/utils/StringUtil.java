package com.newxuetu.utils;

import android.text.TextUtils;

/**
 * Created by liang on 2016/10/23.
 */

public class StringUtil {

    public static boolean stringIsEntry (String text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }else {
            return false;
        }
    }


}
