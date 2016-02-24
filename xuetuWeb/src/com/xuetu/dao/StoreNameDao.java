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
public class StoreNameDao {
	public void addStoreName(StoreName storeName){
		// 1、连数据库
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句
			String sql = "insert into storename(sto_name,sto_user_name,sto_tel,sto_address,sto_introduction,sto_pwd)" + "values (?,?,?,?,?,?)";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			//sto_name
			prep.setString(1, storeName.getStoName());
			//sto_user_name
			prep.setString(2, storeName.getStoUserName());
			//sto_tel
			prep.setString(3, storeName.getStoTel());
			//sto_address
			prep.setString(4, storeName.getStoAddress());
			//sto_introduction
			prep.setString(5, storeName.getStoIntroduction());
			//sto_pwd
			prep.setString(6, storeName.getStoPwd());
			// 5、执行sql语句
			prep.executeUpdate();
		} catch (Exception e) {
			// 一定要处理异常,异常的信息要存在日志文件
			// 转化为应用程序的异常，再抛出
			throw new RuntimeException(e);
		} finally {
			// 6、关闭资源
			try {
				if (prep != null&&conn != null)
					prep.close();
					conn.close();
				} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}

