package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.xuetu.utils.DBconnection;

public class SendQianDaoDateDao {
	
//	SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
//	Date date =null;
	
	
	
	/**
	 *   传入学生id  然后输入签到信息
	 * @param stu_id
	 */
	public void send_qiandao_date(int stu_id)
	{
		String sql=null;		
		Connection conn=null;
		PreparedStatement prep = null;
		Calendar c = Calendar.getInstance();
		String s = (c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"
			+c.get(Calendar.DAY_OF_MONTH)+" " +
			c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
		System.out.println(s);
		try {
			conn = DBconnection.getConnection();
//			date =  simpleDateFormat.parse(s);//当前日期的date类型
			sql="insert into check_ins (stu_id,chi_time,chi_is) values (?,?,?);";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, stu_id);
			prep.setTimestamp(2, Timestamp.valueOf(s));
			prep.setInt(3, 1);
			prep.executeUpdate();
		} catch (Exception e) {
		}
		finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prep!=null)
			{
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public List<String> get_qiandao_date(int stu_id)
	{
		Calendar c = Calendar.getInstance();
		int yy_s =  c.get(Calendar.YEAR);
		int mm_s =  c.get(Calendar.MONTH)+1;
		SimpleDateFormat dd = new SimpleDateFormat("dd");
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		SimpleDateFormat mm = new SimpleDateFormat("MM");
		String sql=null;		
		Connection conn=null;
		PreparedStatement prep = null;
		List<String> list = new ArrayList<>();
		try {
				conn = DBconnection.getConnection();
				sql  = "select chi_time from check_ins where stu_id=?;";
				System.out.println("stu_id"+stu_id);
				prep = conn.prepareStatement(sql);
				prep.setInt(1, stu_id);
				ResultSet rs = prep.executeQuery();
				
				while(rs.next())
					{
						if(Integer.parseInt(yy.format(rs.getTimestamp("chi_time")))==yy_s)  
						{	
							if(  Integer.parseInt(mm.format(rs.getTimestamp("chi_time")))==mm_s   )
							{
								list.add( dd.format(rs.getTimestamp("chi_time"))   );
							}
						}
					}
						System.out.println(dd.format(rs.getTimestamp("chi_time")));
			} 
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prep!=null)
			{
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 	
		return list;
	}
	
	/**
	 * 获得当天学生学习的时间,(分)
	 */
	
	public long get_today_studytime(int stu_id)
	{
		long ss=0;
		Connection conn = null ; 
		PreparedStatement prep = null;
		String sql = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
		String str = sdf.format(now);
		System.out.println(str+"<<<<<<<<<<<<<<<<");
		
		try {
			conn =DBconnection.getConnection();
			sql="select * from studytime where stu_id=?;";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, stu_id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				if(str.equals((sdf.format(rs.getTimestamp("st_date")))))
				{
					ss+=rs.getInt("sto_time");
//					list.add(rs.getInt("sto_time"));
//					System.out.println(list);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prep!=null)
			{
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ss;
	}
	
	/**
	 * 获得所有用户的数量,以及他们各自的学习时间
	 */
	
	public List <Integer>  get_paiming(int stu_id)
	{
		Connection conn = null ; 
		PreparedStatement prep = null;
		String sql = null;
		List <Integer> list = new ArrayList<>();
		
		try {
			sql="select stu_id from student ;";
			conn =DBconnection.getConnection();
			prep = conn.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				list.add(rs.getInt("stu_id"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prep!=null)
			{
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
	
	/**
	 * 获得所有学生的学习总时间(当天)
	 */
	
	public  int[]  get_every_studytime( List<Integer> list)
	{
		int [] all_time = new int [list.size()];
		Connection conn = null ; 
		PreparedStatement prep = null;
		String sql = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
		String str = sdf.format(now);
		try {
			conn = DBconnection.getConnection();
			for(int i=0;i<list.size();i++)
			{
				sql="select * from studytime where stu_id=?;";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, list.get(i));
				ResultSet rs = prep.executeQuery();
				all_time[i]=0;
				while(rs.next())
				{
					if(str.equals((sdf.format(rs.getTimestamp("st_date")))))
					{
						all_time[i]+=rs.getInt("sto_time");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prep!=null)
			{
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		for(int i=0;i<all_time.length;i++){
//			System.out.println(all_time[i]);
//		}
//		
		return all_time;
	}
	
	
}
