package com.xuetu.test;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.xuetu.dao.QuestionIml;
import com.xuetu.dao.inter.QuesTionDao;
import com.xuetu.entity.CollectionQuestion;
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
	QuesTionDao q = new QuestionIml();
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
		System.out.println(Qservice.createQuestion(2, "煞笔","sds", new Date(System.currentTimeMillis()), 10, 2, 1));
	}
	@Test
	public void submitQuestion(){
		Qservice.submitQuestion(Qservice.createQuestion(2, "煞笔王毅","sd", new Date(System.currentTimeMillis()), 10, 2, 1));
		System.out.println("OK");
	}
	@Test
	public void getSchIdBystuId(){
		System.out.println(new QuestionIml().getSchIdByStuId(1));
	}
	@Test
	public void getPageQuestion(){
		System.out.println(Qservice.queryLimitQuestion(1, 10).get(5));
	}
	@Test
	public void getQuestionByQuesId(){
		System.out.println(Qservice.getQuestionByQuesId(1).getQuesText());
	}
	@Test
	public void createAnswer(){
		System.out.println(Qservice.createAnswer(1, 2, "x趋近于0，求y的值", null,new Date(System.currentTimeMillis())));
	}
	@Test
	public void submitAnswer(){
		Qservice.submitAnswer(Qservice.createAnswer(1, 2, "听着老歌","xuetuImg/1.jpg", new Date(System.currentTimeMillis())));
	}
	@Test
	public void getAnswerByQuesId(){
		System.out.println(Qservice.getAnswerByQuesId(1,1,2).get(1).getAnsText());
	}
	@Test
	public void querylimitAnswers(){
		System.out.println(Qservice.queryLimitAnswer(1, 1).get(0).getAnsTime());
	}
	@Test
	public void agree(){
		q.agreeAnswer(2, 3, new Date(System.currentTimeMillis()));
		System.out.println("ok");
	}
	@Test
	public void diaAgree(){
		q.disAgreeAnswer(2, 3);
		System.out.println("ok");
	}
	@Test
	public void collectCancelQuestion(){
		q.collectCancelQuestion(2, 3);
	}
	@Test
	public void isSave(){
		System.out.println(q.isSave(29, 3)+"");
	}
	@Test 
	public void getQuesBySubId(){
		System.out.println(q.getQuesBySubId(1).size());
	}
	@Test 
	public void haha(){ 
		Boolean s = true;
		if(s==true)
		System.out.println("p");
		else
			System.out.println("a");
	}
	@Test 
	public void getAgreeAnswerByStuId(){
		for(Integer i:q.getAgreeAnswerByStuId(3)){
			System.out.println(i);
		}	
	}
	@Test
	public void getAgrNumByAnsId(){
		System.out.println(q.getAgrNumByAnsId(2));
	}
	@Test
	public void getQuesWithImg(){
		System.out.println(q.getQuesIdsWithImg().size());
	}
}
