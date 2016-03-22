package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.HomeInter;
import com.xuetu.entity.ChckIns;
import com.xuetu.entity.ClassTime;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.service.inter.ClassTimeServiceInter;
import com.xuetu.utils.DBconnection;
import com.xuetu.web.android.GetClassTime;

public class CLassTimeDao implements ClassTimeServiceInter{
	/**
	 * 获得学生的选课id 
	 * @param stu_id
	 * @return List<String>
	 */
	public List<Integer> get_student_clsID(int stu_id)
	{
		List<Integer>cls_id = null;
		PreparedStatement prep=null;
		Connection conn=null;;
		
		conn = DBconnection.getConnection();
		
		String sql;
		try {
			conn = DBconnection.getConnection();
			cls_id=new ArrayList();
			sql="select cls_id from courselist where stu_id=?;";
			prep.setInt(1, stu_id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				cls_id.add(rs.getInt("cls_id"));
			}
			return cls_id;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				if(conn!=null)
				{
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(prep!=null)
				{
					prep.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//		System.out.println(cls_id);
		return null;
		
	}
	
	/**
	 * 取得今天的课程
	 * 获取学生选课id之后获取对应的课程对象
	 */
	public List<MyClass> getMyClass(List<String> cls_id)
	{
		MyClass myclass = new MyClass();
		List<MyClass>listmyclass = new ArrayList<>();
		ClassTime classtime = new ClassTime();
		int week = classtime.getWeek();
		Student student = new Student();
		PreparedStatement prep =null ;
		Connection conn=null;
		
		try {
			String sql="select * from class where cls_wek="+week;
			conn=DBconnection.getConnection();
			ResultSet rs = prep.executeQuery();
			while(rs.next())
			{
				myclass.setClasName(rs.getString("cls_name"));
				myclass.setClsWeek(rs.getInt("cls_week"));
				myclass.setClsFew(rs.getInt("cls_few"));
				myclass.setClsId(rs.getInt("cls_id"));
				myclass.setClsRoom(rs.getString("cls_room"));
				listmyclass.add(myclass);
			}
			return listmyclass;
			
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	
	
}	
