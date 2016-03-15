
package com.xuetu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.xuetu.dao.inter.HomeInter;
import com.xuetu.entity.ChckIns;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.utils.DBconnection;


/**
 * ClassName:TimeDao Function: 新建studytime 列  创建新列
 *
 * @author 康毅
 * @version
 * @since Ver 1.1
 * @Date 2016年2月20日 下午3:29:00
 *
 * @see
 * 
 */

public class TimeDao  {
	
	//积分
	int acpo_num=5;
	/**
	 * 
	 * studyTime:(插入新列队)<br/>
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
	public void addTime(StudyTime stu_time)
	{
		PreparedStatement prep = null;
		Connection conn = null;
		
		try
		{
			conn = DBconnection.getConnection();
			//sql语句  直接插入新列队
			String sql = "insert into studytime(st_id,st_date,sto_time,stu_id,acpo_num)"
					+ " values(?,?,?,?,?);";
			// 获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			
			prep.setInt(1, stu_time.getSttID());
			prep.setDate(2, (Date) stu_time.getDate());
			prep.setLong(3, stu_time.getTime());
			prep.setInt(4, stu_time.getStudent().getStuId());
			prep.setInt(5, acpo_num);
		} 
		catch (Exception e)
		{
			
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
	
	
}
