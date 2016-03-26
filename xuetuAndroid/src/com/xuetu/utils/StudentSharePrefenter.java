package com.xuetu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class StudentSharePrefenter {
	private static SharedPreferences sharedPreferences=null;
	public StudentSharePrefenter(Context context) {
		sharedPreferences=context.getSharedPreferences("config", Activity.MODE_PRIVATE);
	}
	
	
	public static boolean editStudent(String phone,String pwd){
		Editor edit = sharedPreferences.edit();
		
		edit.putString("uasename", phone);
		edit.putString("pwd", pwd);
		boolean commit = edit.commit();
		return commit;
		
	}
	
	public static String getStudentPhne(){
		return sharedPreferences.getString("uasename", "-1");
		
	}
	public static String getStudentPwd(){
		return sharedPreferences.getString("pwd", "-1");
		
	}
	
	
	
	
	
}
