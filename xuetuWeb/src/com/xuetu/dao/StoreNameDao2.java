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
	
	
	public boolean changeStoNamePWD(int sto_ID,String pwd){
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
//			Statement statement = conn.createStatement();
//			//updata storename set sto_ped= pwd   where Sto_id = st_ID
//			
//			
//			String sql="update storename set sto_pwd ='"+pwd+"' where sto_id="+sto_ID+";";
//			System.out.println(sql);
//			statement.executeUpdate(sql);
			
			String sql="update storename set sto_pwd =? where sto_id=? ;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, pwd);
			statement.setInt(2, sto_ID);
			statement.executeUpdate();
			
			
			return true;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
		
		
		
		
	}
	/**
	 * 
	 * changeStoName:(修改数据库里面店家表的    店名，店家电话  ，店家介绍，店家地址)<br/>
	 *
	 * @param  @param storeName
	 * @param  @return    设定文件
	 * @return boolean    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public boolean changeStoName(int sto_id,StoreName storeName){
		Connection conn=null;
		PreparedStatement statement=null;
		try {
			conn = DBconnection.getConnection();
			String sql="update storename set sto_name=? , sto_introduction=? ,sto_address =? ,sto_tel = ?, sto_img=? where  sto_id=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, storeName.getStoName());
			statement.setString(2, storeName.getStoIntroduction());
			statement.setString(3, storeName.getStoAddress());
			statement.setString(4, storeName.getStoTel());
			statement.setString(5, storeName.getStoImg());
			statement.setInt(6,sto_id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}finally{
			if(conn!=null&&statement!=null){
				try {
					conn.close();
					statement.close();
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
		
		
	}
	
	
}

