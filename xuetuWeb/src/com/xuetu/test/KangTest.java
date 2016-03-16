package com.xuetu.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.xuetu.dao.FindIml;
import com.xuetu.dao.TimeDao;
import com.xuetu.entity.StudyTime;
import com.xuetu.service.FindService;
import com.xuetu.service.TimeService;
import com.xuetu.service.inter.FindServicesInter;
import com.xuetu.service.inter.HomeServiceInter;
import com.xuetu.web.android.AddStudyTime;

/**
 * ClassName:KangTest<br/>
 *
 *
 * @author   测试
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月22日		下午6:35:10
 *
 * @see 	 

 */




public class KangTest {
	
//	*	@Test
//	public void savaTest() throws ClassNotFoundException, SQLException, ParseException{
//		//测试StudentDao中的save方法
//		StudentDao dao = new StudentDao();
//		//创建要保存的学生对象
//		Student s = new Student();
//		//set方法设置s的属性
//		s.setName("卡伊");
//		s.setSno("5");
//		s.setPwd("11");
//		s.setSex(1);
//		s.setPolity(1);
//		SimpleDateFormat sdf = 
//				new SimpleDateFormat("yyyy-MM-dd");
//		s.setBirthday(sdf.parse("1992-2-2"));
//		s.setBrief("嘎嘎嘎");
//		dao.save(s);
//	}
	
	
//	TimeService timeservice = new TimeService();
//	FindServicesInter findService = new FindService(new FindIml());
	HomeServiceInter homeservice = new TimeService(new TimeDao());
//	StudyTime st = new StudyTime();
	
	
	
	@Test
	public void kangTest()
	{
		TimeDao dao = new TimeDao();
//		StudyTime st = new StudyTime();
//		st.setAcpo_num(10);
//		st.setSttID(15);
//		st.setStudent(null);
//		st.setTime((long) 2000);
//		SimpleDateFormat sdf = 
//				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		st.setDate(date);
//		SimpleDateFormat sdf = 
//				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date=new Date(System.currentTimeMillis()) ;
//		try {
////			date = sdf.parse("1992-02-02 00:20:00");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		long l = 3000;
		
		StudyTime st = new StudyTime( date, 55, l, null);
		System.out.println("dsd");
	new TimeDao().addTime(st);
	}
	
	
}
