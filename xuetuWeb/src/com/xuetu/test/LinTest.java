package com.xuetu.test;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.xuetu.dao.QuestionIml;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * ClassName:LinTest<br/>
 * Function: TODO ADD FUNCTION<br/>
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月14日		下午9:06:10
 *
 * @see 	 

 */
public class LinTest {
	QuestionServiceInter Qservice = new QuestionService(new QuestionIml());
	@Test 
	public void getSchoolBySchId(){
		System.out.println(Qservice.getSchoolBySch_id(1).getSchLatitude());
		//System.out.println(new QuestionIml().getSchoolBySch_id(1).getSchLatitude());
		
	}
	
	@Test
	public void getSubjectBySubId(){
		System.out.println(Qservice.getSubjectBySubId(1).getName());
	}
	
	@Test
	public void getStudentByStuId(){
		System.out.println(Qservice.getStudentByStuId(1, 1).getStuEmail());
	}
	@Test
	public void createQuestion(){
		System.out.println(Qservice.createQuestion(2, "高数怎么这么难", new Date(System.currentTimeMillis()), 10, 2, 1));
	}
	@Test
	public void submitQuestion(){
		Qservice.submitQuestion(Qservice.createQuestion(2, "求极限怎么求", new Date(System.currentTimeMillis()), 10, 2, 1));
		System.out.println("OK");
	}
	@Test
	public void getSchIdBystuId(){
		System.out.println(new QuestionIml().getSchIdByStuId(1));
	}
	@Test
	public void getPageQuestion(){
		System.out.println(Qservice.queryLimitQuestion(1, 20).get(7).getQuesDate());
	}
}
