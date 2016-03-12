/**
 * CloseDb.java
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:CloseDb
 * Function: 关闭数据库连接
 * Reason:	 TODO ADD REASON
 *
 * @author   liang
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月12日		下午3:20:26
 *
 * @see 	 

 */
public class CloseDb {
	
	/**
	 * 
	 * close:(关闭数据库连接)
	 * <br/>
	 * <br/>
	 * @param  @param connection
	 * @param  @param query
	 * @param  @param statement    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public static void close(Connection connection,ResultSet query,PreparedStatement statement){

		try {
			if (statement != null && query != null && connection != null) {

				query.close();
				statement.close();
				connection.close();
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	
	}
	/**
	 * 
	 * close:(关闭数据库连接)
	 * <br/>
	 * <br/>
	 * @param  @param connection 
	 * @param  @param statement    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public static void close(Connection connection,PreparedStatement statement){

		try {
			if (statement != null && connection != null) {

				statement.close();
				connection.close();
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	
	}
	
	
	

}

