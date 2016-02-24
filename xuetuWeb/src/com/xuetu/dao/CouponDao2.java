/**
 * CouponDao.java
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.utils.*;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StoreName;

/**
 * ClassName:CouponDao Function: 优惠券的增删改查方法 Reason: 商家需要对优惠券进行增删改查等操作
 *
 * @author Adminstrator
 * @version
 * @since Ver 1.1
 * @Date 2016年2月20日 下午3:29:00
 *
 * @see
 * 
 */
public class CouponDao2 {
	StoreNameDao2 dao2 = new StoreNameDao2();

	public List<Coupon> queryCouponList(int storeNameId) {
		Connection conn = null;
		Statement statement = null;
		ResultSet query = null;
		try {
			conn = DBconnection.getConnection();
			statement = conn.createStatement();
			String sql = "select * from coupon where sto_id=" + storeNameId + ";";
			System.out.println(sql);
			query = statement.executeQuery(sql);
			List<Coupon> listCoupon = new ArrayList<>();
			Coupon coupon = null;
			while (query.next()) {
				coupon = new Coupon();
				coupon.setCouID(query.getInt("cou_id"));
				StoreName storeName = dao2.getStoreNameById(query.getInt("sto_id"));
				coupon.setStoreName(storeName);
				coupon.setCouInfo(query.getString("cou_info"));
				coupon.setConNum(query.getInt("cou_num"));

				coupon.setConValidity(query.getDate("cou_Validity"));
				coupon.setCoouRedeemPoints(query.getInt("cou_redeem_points"));
				coupon.setCouName(query.getString("cou_name"));
				coupon.setCouPrice(query.getInt("cou_price"));
				listCoupon.add(coupon);

			}
			return listCoupon;
		} catch (SQLException e) {

			//
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
	/**
	 * 
	 * queryLimitCouponList:(分组查询coupon数据表)<br/>
	
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @param storeNameId
	 * @param  @return    设定文件
	 * @return List<Coupon>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<Coupon> queryLimitCouponList(int page,int num,int storeNameId) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet query = null;
		try {
			conn = DBconnection.getConnection();
			String sql = "select * from coupon where sto_id=? limit ?,?;";
			
			
			statement = conn.prepareStatement(sql);
			statement.setInt(1, storeNameId);
			statement.setInt(2, (page-1)*num);
			statement.setInt(3, num);
			
			query  = statement.executeQuery();
			List<Coupon> listCoupon = new ArrayList<>();
			Coupon coupon = null;
			while (query.next()) {
				coupon = new Coupon();
				coupon.setCouID(query.getInt("cou_id"));
				StoreName storeName = dao2.getStoreNameById(query.getInt("sto_id"));
				coupon.setStoreName(storeName);
				coupon.setCouInfo(query.getString("cou_info"));
				coupon.setConNum(query.getInt("cou_num"));
				
				coupon.setConValidity(query.getDate("cou_Validity"));
				coupon.setCoouRedeemPoints(query.getInt("cou_redeem_points"));
				coupon.setCouName(query.getString("cou_name"));
				coupon.setCouPrice(query.getInt("cou_price"));
				listCoupon.add(coupon);
				
			}
			return listCoupon;
		} catch (SQLException e) {
			
			//
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

	/**
	 * 
	 * addCoupon:(新增优惠券到数据库)<br/>
	 * TODO(这里描述这个方法适用条件 – 可选)<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选)<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选)<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选)<br/>
	 *
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public Coupon queryCoupon(int cou_id) {
		// 1、连数据库
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句
			String sql = "select * from coupon where cou_id = ?;";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1, cou_id);
			ResultSet query = prep.executeQuery();
			Coupon coupon = null;
			if (query.next()) {
				coupon = new Coupon();
				coupon.setCouID(query.getInt("cou_id"));
				StoreName storeName = dao2.getStoreNameById(query.getInt("sto_id"));
				coupon.setStoreName(storeName);
				coupon.setCouInfo(query.getString("cou_info"));
				coupon.setConNum(query.getInt("cou_num"));

				coupon.setConValidity(query.getDate("cou_Validity"));
				coupon.setCoouRedeemPoints(query.getInt("cou_redeem_points"));
				coupon.setCouName(query.getString("cou_name"));
				coupon.setCouPrice(query.getInt("cou_price"));
			}
			return coupon;

		} catch (Exception e) {
			// 一定要处理异常,异常的信息要存在日志文件
			// 转化为应用程序的异常，再抛出
			e.printStackTrace();
			return null;
		} finally {
			// 6、关闭资源
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public boolean ChangeCoupon(Coupon coupon) {
		System.out.println("sheli shi Cou[onDao2");
		// 1、连数据库
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句
			String sql = "update   coupon  set  " + "cou_name =?, " + "cou_info=?, " + "cou_num = ?, "
					+ "cou_Validity= ?, " + "cou_redeem_points = ?, " + "cou_price = ?  " + " where cou_id = ?;";
			// 3、获得preparedStatement对象
			System.out.println(sql);
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setString(1, coupon.getCouName());
			prep.setString(2, coupon.getCouInfo());
			prep.setInt(3, coupon.getConNum());
			prep.setDate(4, new java.sql.Date(coupon.getConValidity().getTime()));
			prep.setInt(5, coupon.getCoouRedeemPoints());
			prep.setInt(6, coupon.getCouPrice());
			prep.setInt(7, coupon.getCouID());

			// 5、执行sql语句
			prep.executeUpdate();
			return true;
		} catch (Exception e) {
			// 一定要处理异常,异常的信息要存在日志文件
			// 转化为应用程序的异常，再抛出
			e.printStackTrace();
			return false;
		} finally {
			// 6、关闭资源
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
/**
 * 
 * ChangeCoupon:(临时的方法)<br/>

 *
 * @param  @param couID
 * @param  @return    设定文件
 * @return boolean    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public boolean delCoupon(int couID) {
		Connection conn = null;
		Statement statement= null;
		try {
			conn = DBconnection.getConnection();
			statement = conn.createStatement();
			String sql  = "delete from coupon where cou_id="+couID+";";
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			
			//
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (conn != null && statement != null ) {
					conn.close();
					statement.close();
					
				}
			} catch (SQLException e) {

				//
				e.printStackTrace();

			}
		}
		

	}
	
	
	
			
	

}
