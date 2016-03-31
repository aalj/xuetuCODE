
package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
		try 
			{
				conn = DBconnection.getConnection();
				cls_id=new ArrayList();
				sql="select cls_id from courselist where stu_id=?;";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, stu_id);
				ResultSet rs = prep.executeQuery();
				
				while(rs.next())
				{
					cls_id.add(rs.getInt("cls_id"));
				}
				return cls_id;
				
			} 
		catch (SQLException e)
			{
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
	 *  * 取得今天的课程
	 *    获取学生选课id之后获取对应的课程对象            获得MyClass对象
	 */
	public List<MyClass> getMyClass()  //List<String> cls_id
	{
		
		MyClass myclass = new MyClass();
		List<MyClass>listmyclass = new ArrayList<>();
		ClassTime classtime = new ClassTime();
		int week = classtime.getWeek();
		Student student = new Student();
		PreparedStatement prep =null ;
		Connection conn=null;
		int few = classtime.getFwe();
		try {                                       //通过星期几,第几节课,获取课程表中的课程对象,
			conn=DBconnection.getConnection();
			String sql="select * from class where cls_week="+week+" and cls_few="+few+";";
//			String sql="select * from class where cls_week=1 and cls_few=2;";

			System.out.println("week>>>>>>>"+week+">>>few>>>>>"+few);
			
			prep = conn.prepareStatement(sql);
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
//			listmyclass.get(0).getClasName();
			return listmyclass;
			
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
		return null;
	}
	
	
	
	/**
	 * 通过已经获取  学生选课id,与 MyClass对象中的      选课id匹配
	 * @param cls_id
	 * @param listmyclass
	 * @return
	 */
	
	public MyClass isStudy(List<Integer> cls_id,List<MyClass> listmyclass)
	{
		MyClass myclass = null;
		boolean  bl=false;
		System.out.println("ccccccccccccccccc"+cls_id.size());
		for(int i=0;i<cls_id.size();i++)
		{
			for(int j=0;j<listmyclass.size();j++)
			{
				if((cls_id.get(i))==listmyclass.get(j).getClsId())
				{
					myclass=listmyclass.get(j);
					bl=true;
					break;
				}
			}
		}
		return myclass;
	}
	
	
	/**
	 * 条件全部符合,返回时间,   现在的时间,到下课之间的时间
	 * @param boo
	 * @return
	 */
	public long sendTime_SS()
	{
		long l=0;
			//创建一个对象
			ClassTime  classtime = new ClassTime();
			//用对象获取现在应该是第几节课,然后将第几节课传入 getSS()内,获取现在距离课程结束所需要的秒数,SS
			l = classtime.getSS(classtime.getFwe());
		return l;
	}


	@Override
	public List<MyClass> getMyClass(List<String> cls_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
//
//	/**
//	 * 通过已经获取  学生选课id,与 MyClass对象中的      选课id匹配
//	 * @param cls_id                 返回课程名
//	 * @param listmyclass
//	 * @return
//	 */
//	
//	public int getcls_id  (List<Integer> cls_id,List<MyClass> listmyclass)
//	{
//		int jj=0;
//		boolean bl = false;
//		System.out.println("ccccccccccccccccc"+cls_id.size());
//		for(int i=0;i<cls_id.size();i++)
//		{
//			for(int j=0;j<listmyclass.size();j++)
//			{
//				if((cls_id.get(i))==listmyclass.get(j).getClsId())
//				{
//					bl=true;
//					jj=
//					break;
//				}
//			}
//		}
//		return jj;
//	}
	
	
	
}	
