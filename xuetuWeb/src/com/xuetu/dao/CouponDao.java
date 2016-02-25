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

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.xuetu.utils.*;
import com.xuetu.entity.Coupon;

/**
 * ClassName:CouponDao
 * Function: 优惠券的增删改查方法
 * Reason:	 商家需要对优惠券进行增删改查等操作
 *
 * @author   Adminstrator
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		下午3:29:00
 *
 * @see 	 

 */
public class CouponDao {
	/**
	 * 
	 * addCoupon:(新增优惠券到数据库)<br/>
	 * TODO(这里描述这个方法适用条件 – 可选)<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选)<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选)<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选)<br/>
	 *
	 * @param      设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void addCoupon(Coupon coupon,int sto_id){
		// 1、连数据库
				PreparedStatement prep = null;
				Connection conn = null;
				try {
					conn = DBconnection.getConnection();
					// 2、SQL语句
					String sql = "insert into coupon(sto_id,cou_info,cou_num,cou_Validity,cou_redeem_points,cou_name,cou_price)" + "values (?,?,?,?,?,?,?)";
					// 3、获得preparedStatement对象
					prep = conn.prepareStatement(sql);
					// 4、设置？的值
					//sto_id,cou_info,cou_num,cou_Validity,cou_redeem_points,cou_name,cou_price
					//
					prep.setInt(1, sto_id);
					prep.setString(2, coupon.getCouInfo());
					prep.setInt(3, coupon.getConNum());
					prep.setDate(4, new java.sql.Date(coupon.getConValidity().getTime()));
					prep.setInt(5, coupon.getCoouRedeemPoints());
					prep.setString(6, coupon.getCouName());
					prep.setInt(7, coupon.getCouPrice());
					// 5、执行sql语句
					prep.executeUpdate();
				} catch (Exception e) {
					// 一定要处理异常,异常的信息要存在日志文件
					// 转化为应用程序的异常，再抛出
					throw new RuntimeException(e);
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
	
}

