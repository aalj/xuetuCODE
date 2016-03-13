
package com.xuetu.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 
 * @author Mystery
 * @date 2015-2-1
 */
public class CommonUtil {

	/**
	 * 生成一个随机数
	 * 
	 * @param max
	 * @return int
	 */
	public static int getRandom(int max) {
		return (int) (Math.random() * max);
	}

}
