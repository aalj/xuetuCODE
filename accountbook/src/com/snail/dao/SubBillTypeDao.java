/**
 * SubBillType.java
 * com.snail.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月27日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.snail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.snail.entity.SubAccountType;
import com.snail.utils.DBconnection;

/**
 * ClassName:SubBillType Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年1月27日 下午7:52:07
 *
 * @see
 * 
 */
public class SubBillTypeDao {
	/**
	 * 
	 * querySubType:(查询账单类型的全部的内容)<br/>
	 * TODO(这里描述这个方法适用条件 – 可选)<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选)<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选)<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选)<br/>
	 *
	 * @param  @return    设定文件
	 * @return List<SubAccountType>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<SubAccountType> querySubType() {
		try {
			Connection conn = DBconnection.getConnection();
			Statement statement = conn.createStatement();
			String sql = "select * from sub_bill_type;";
			ResultSet query = statement.executeQuery(sql);
			SubAccountType sub = null;
			List<SubAccountType> list = new ArrayList<>();

			while (query.next()) {
				sub = new SubAccountType();
				sub.setSubAccountTypeID(query.getInt("sub_bt_id"));
				sub.setAccountTypeID(query.getInt("by_id"));
				sub.setSubAccountTypeNma(query.getString("sub_bt_name"));
				list.add(sub);
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 
	 * queryLimitSubType:(分页 查询)<br/>
	 *
	 * @param  @param page 表示第几页
	 * @param  @param num  表示每页显示数目
	 * @param  @return    设定文件
	 * @return List<SubAccountType>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<SubAccountType> queryLimitSubType(int page, int num) {
		try {
			Connection conn = DBconnection.getConnection();
			String sql = "select * from sub_bill_type limit ?,?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, (page-1)*num);
			statement.setInt(2, num);
			ResultSet query = statement.executeQuery();
			SubAccountType sub = null;
			List<SubAccountType> list = new ArrayList<>();

			while (query.next()) {
				sub = new SubAccountType();
				sub.setSubAccountTypeID(query.getInt("sub_bt_id"));
				sub.setAccountTypeID(query.getInt("by_id"));
				sub.setSubAccountTypeNma(query.getString("sub_bt_name"));
				list.add(sub);
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	

}
