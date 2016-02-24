/**
 * FamilyDao.java
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
import java.util.ArrayList;
import java.util.List;

import com.snail.utils.DBconnection;

/**
 * ClassName:FamilyDao
 * Function: 查询数据库类
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午4:42:24
 *
 * @see 	 
 */
public class FamilyDao {
	
	/*
	 * 增加家庭成员
	 */
	public void faAdd(Family family)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
		//连数据库
		conn = DBconnection.getConnection();
		//sql语句
		String sql = "insert into familymember(fm_id,fm_name,fm_user_name,fm_permiss,pwd)+values(?,?,?,?,?);";
		//获得prepareddtatement对象
		ps = conn.prepareStatement(sql);
		//设置 ?　的值
		ps.setInt(1,family.getFm_Id());
		ps.setString(2,family.getRealName());
		ps.setString(3, family.getUserName());
		ps.setInt(4, family.getPermiss());
		ps.setString(5,family.getPwd());
		} 
		catch (Exception e) 
		{
			//抛出异常
			throw new RuntimeException(e);
		}
		finally
		{
			//关闭资源
			if(conn!=null)
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					throw new RuntimeException(e);
				}
			if(ps!=null)
				try
				{
					ps.close();
				} 
				catch (SQLException e) 
				{
					throw new RuntimeException(e);
				}
		}
	}
	
	/*
	 * 通过成员id获取信息
	 */
	public Family getFamilyById(int id) throws Exception
	{
		Connection conn = DBconnection.getConnection();
		String sql = "select * from familymember where fm_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		//创建一个家庭成员对象
		Family er = new Family();
		//取到数据,装到一个学生对象
		if(rs.next())
		{
			er.setFm_Id(rs.getInt("fm_id"));
			er.setUserName(rs.getString("userName"));
			er.setRealName(rs.getString("realName"));
			er.setPwd(rs.getString("pwd"));
			er.setPermiss(rs.getInt("permiss"));
		}
		return er;
	}
	
	
	
	/*
	 * 修改家庭成员信息
	 */
	public Family update(Family f)
	{
		
		
		
	}
	
	
	
	
	
	
	/*
	 * 通过id 删除家庭成员信息
	 */
	public void delete(int[] id)
	{
		//sql语句
		String sql = "delete from familymember where id in(";
		
		for(int i=0;i<id.length;i++)
		{
			sql+=id[i];
			if(i<id.length-1)
			{
				sql+=",";
			}
		}
		sql=sql+");";
	}
	
	/*
	 * 获得全部家庭成员信息
	 */
	public List<Family> getAll()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Family> family = new ArrayList<Family>();
		try
		{
			conn = DBconnection.getConnection();
			String sql = "select * from familymember";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				Family f = new Family();
				f.setFm_Id(rs.getInt("id"));
				f.setPermiss(rs.getInt("permiss"));
				f.setPwd(rs.getString("pwd"));
				f.setRealName(rs.getString("realName"));
				f.setUserName(rs.getString("userName"));
				
				family.add(f);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return family;
	}
	
	/*
	 * 修改家庭成员信息
	 */
	public void update(Family f)
	{
		
	}
	
	//添加账单
	//删除账单
	//修改账单
	
	//添加成员
	//修改成员
	//删除成员
	
	//添加账单父项
	
	
	//新增账单子项
	
	
	//删除
	
	
	
	

}

