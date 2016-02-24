/**
 * DBconnection.java
 * com.xuetu.util
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月6日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;

/**
 * ClassName:DBconnection Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年1月6日 上午10:46:30
 *
 * @see
 * 
 */
public class DBconnection {
	public static Connection getConnection() {
		try {

			Properties p = new Properties();
			p.load(DBconnection.class.getResourceAsStream("db.properties"));
			Class.forName(p.getProperty("driver"));
			return DriverManager.getConnection(p.getProperty("url"), p.getProperty("name"), p.getProperty("pwd"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 

	}

}
