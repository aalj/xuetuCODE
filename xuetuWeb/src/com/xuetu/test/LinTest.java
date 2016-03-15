package com.xuetu.test;

import org.junit.Test;

import com.xuetu.dao.QuestionIml;
import com.xuetu.dao.inter.QuestionInter;
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
		System.out.println(new QuestionIml().getSubjectBySubId(1).getName());
	}
	
	@Test
	public void getStudentByStuId(){
		System.out.println(Qservice.getStudentByStuId(1, 1).getStuEmail());
	}
}
