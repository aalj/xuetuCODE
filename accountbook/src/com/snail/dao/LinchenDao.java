/**
 * LinchenDao.java
 * com.snail.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月27日 		Administrator
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.snail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.snail.entity.Account;
import com.snail.entity.AccountType;
import com.snail.entity.SubAccountType;

/**
 * ClassName:LinchenDao
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Administrator
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午9:13:28
 *
 * @see 	 

 */
public class LinchenDao {
	/**
	 * 获得所有Account
	 * */
	
		public Account[] getAllAccount(){
			Connection conn = null;
			try {
				Statement stat = conn.createStatement();
				String sql = "select * from bill";
				ResultSet rs = stat.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}	
	/**
	* 新增账单
	* */
	public void insertAccount(Account account,AccountType actype,SubAccountType suatype){
		Connection conn = null;
		String sql1 = "insert into bill(in_ex_id,) values(?,?,?,?)";
		String sql2 = "insert into bill_type(in_ex_id,by_name) values(?,?)";
		String sql3 = "insert into sub_bill_type() values(?,?)";
		PreparedStatement prep = conn.prepareStatement();
		
		int aType = account.getAccountType();
		String aTypeName = actype.getAccountTypeNma();
		String subaTypeName = suatype.getSubAccountTypeNma();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(account.getIncomeDate());
		
	}
}

