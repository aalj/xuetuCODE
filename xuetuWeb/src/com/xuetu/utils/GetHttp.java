/**
 * GetHttp.java
 * com.xuetu.utils
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月12日 		liang
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName:GetHttp Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年3月12日 下午2:48:26
 *
 * @see
 * 
 */
public class GetHttp {
	

	public static String getHttpLJ() {
		try {

			Properties p = new Properties();
			p.load(DBconnection.class.getResourceAsStream("db.properties"));
			String property = p.getProperty("urllj");
			return property;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	public static String getHttpBCL() {
		try {
			
			Properties p = new Properties();
			p.load(DBconnection.class.getResourceAsStream("db.properties"));
			String property = p.getProperty("urlbcl");
			return property;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static String getHttpLC() {
		try {
			
			Properties p = new Properties();
			p.load(DBconnection.class.getResourceAsStream("db.properties"));
			String property = p.getProperty("urllc");
			return property;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static String getHttpKY() {
		try {
			
			Properties p = new Properties();
			p.load(DBconnection.class.getResourceAsStream("db.properties"));
			String property = p.getProperty("urlky");
			return property;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
