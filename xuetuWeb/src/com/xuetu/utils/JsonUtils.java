package com.xuetu.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	
	
	
	

	
	public static String formToJson(int code,String msg,Object obResult){
		
		String msg2;
		
		if(200 == code){
			msg2="success";
		}else if(404 == code){
			msg2=msg;
		}else{
			msg2="fail";
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
		String result = gson.toJson(obResult);
		
		
		String json = "{"
				+ "'id':'"+code
				+"','msg':'"+msg2
				+"','result':"+result
				+"}";
		
		
		
		

		return json;
	}
	

}
