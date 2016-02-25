/**
 * StoreNameDao.java
 * com.xuetu.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月20日 		Administrator
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xuetu.entity.StoreName;
import com.xuetu.utils.DBconnection;

/**
 * ClassName:StoreNameDao
 * Function: TODO 店家的添加和修改方法
 * Reason:	 TODO 由于没有管理员的设定，这里只有商家注册的方法，并没有对商家删除和查询的方法
 *
 * @author   Administrator
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		下午8:28:50
 *
 * @see 	 

 */
public class StoreNameDao2 {
	public StoreName getStoreNameById(int storeNameId) {
		ResultSet query = null;
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBconnection.getConnection();
			statement = conn.createStatement();
			String sql = "select * from storename where sto_id= "+ storeNameId +";";
			query = statement.executeQuery(sql);
			StoreName storeName = null;
			if (query.next()) {
				storeName = new StoreName();
				//ID
				storeName.setStoID(query.getInt(1));
				//stoUserName
				storeName.setStoUserName(query.getString(2));
				//stoTel
				storeName.setStoTel(query.getString(3));
				//stoAddress
				storeName.setStoAddress(query.getString(4));
				//stoName
				storeName.setStoName(query.getString(5));
				//stoIntroduction
				storeName.setStoIntroduction(query.getString(6));
				//stoIma
				storeName.setStoImg(query.getString(7));
				//stoPwd
				storeName.setStoPwd(query.getString(8));
				
			}
			return storeName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null && statement != null && query != null) {
					conn.close();
					statement.close();
					query.close();
				}
			} catch (SQLException e) {
				
				//
				e.printStackTrace();
				
			}
		}
		
	}

	public StoreName getStoreNameByName(String username) {
		ResultSet query = null;
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBconnection.getConnection();
			statement = conn.createStatement();
			String sql = "select * from storename where sto_user_name= " +"'"+ username +"';";
			query = statement.executeQuery(sql);
			StoreName storeName = null;
			if (query.next()) {
				storeName = new StoreName();
				//ID
				storeName.setStoID(query.getInt(1));
				//stoUserName
				storeName.setStoUserName(query.getString(2));
				//stoTel
				storeName.setStoTel(query.getString(3));
				//stoAddress
				storeName.setStoAddress(query.getString(4));
				//stoName
				storeName.setStoName(query.getString(5));
				//stoIntroduction
				storeName.setStoIntroduction(query.getString(6));
				//stoIma
				storeName.setStoImg(query.getString(7));
				//stoPwd
				storeName.setStoPwd(query.getString(8));
				
			}
			return storeName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null && statement != null && query != null) {
					conn.close();
					statement.close();
					query.close();
				}
			} catch (SQLException e) {

				//
				e.printStackTrace();

			}
		}

	}
	
	
	
}

