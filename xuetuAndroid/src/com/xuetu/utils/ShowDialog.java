package com.xuetu.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ShowDialog {
	public static void showDialog(final Context context ,boolean temp) {
		if (temp) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			// 设置Title的图标
			// 设置Title的内容
			builder.setTitle("取消添加");
			builder.setCancelable(true);
			// 设置Content来显示一个信息
			builder.setMessage("现在退出将导致数据丢失？");
			// 设置一个PositiveButton
			builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			// 设置一个NegativeButton
			builder.setNegativeButton("确定退出", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					((Activity) context).finish();
				}
			});
			// 显示出该对话框
			builder.show();
		}else{
			((Activity)context).finish();
		}
	}

}
