/**
 * QuesTionDao.java
 * com.xuetu.dao.inter
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月6日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.dao.inter;

import java.sql.Date;
import java.util.List;

import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.entity.Subject;

/**
 * ClassName:QuesTionDao
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月6日		上午12:06:03
 *
 * @see 	 

 */
public interface QuesTionDao {
	
	/**
	 * 
	 * queryQuestionByStuID:(根据用户名获得该用户名对应所有问题对象)<br/>
	 *
	 * @param  @param stu
	 * @param  @return    设定文件
	 * @return List<Question>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public  List<Question> queryQuestionByStuID(Student stu);
	
	/**
	 * 
	 * getAnswerByQuesId:(根据问题获得所有该问题的答案)<br/>
	 *
	 * @param  @param qu
	 * @param  @return    设定文件
	 * @return Answer    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Answer getAnswerByQuesId(Question qu);
	/**
	 * 
	 * queryQuestionByStuJectId:(根据学科类别获得属于该学科的所有问题)<br/>
	 *
	 * @param  @param sub
	 * @param  @return    设定文件
	 * @return List<Question>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<Question> queryQuestionByStuJectId(Subject sub);
	
	/**
	 * 
	 * queryLimitQuestion:(分页查询全部的question，按照时间降序)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return List<Question>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<Question> queryLimitQuestion(int page,int num);
	
	/**
	 * 
	 * getStudentByStuId:(根据stu_id获得student对象)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return school    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Student getStudentByStuId(int stuId,int sch_id);
	/**
	 * 
	 * getQuestionByQuesId:(根据ques_id获得question对象)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return school    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Question getQuestionByQuesId(int ques_id);
	/**
	 * 
	 * queryLimitQuestion:(根据sch_id获得school对象)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return school    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public School getSchoolBySch_id(int sch_id);
	/**
	 * 
	 * queryLimitQuestion:(根据subId获得Subject对象)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return Subject    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Subject getSubjectBySubId(int subId);
	/**
	 * 
	 * queryLimitQuestion:(根据int stuId,String quesText,Date quesTime,int acpoNum,int subId生成问题对象)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Question createQuestion(int stuId,String quesText,Date quesTime,int acpoNum,int subId,int schId);
	/**
	 * 
	 * queryLimitQuestion:(发表问题)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void submitQuestion(Question q);
	/**
	 * 
	 * queryLimitQuestion:(发表问题)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getSchIdByStuId(int StuId);
	/**
	 * 
	 * queryLimitQuestion:(提交答案)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void submitAnswer(Answer answer);
	/**
	 * 
	 * queryLimitQuestion:(分页查询答案，按照时间降序)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<Answer> queryLimitAnswer(int page,int num);
	/**
	 * 
	 * queryLimitQuestion:(生成一个答案对象)<br/>
	 *
	 * @param  @param page
	 * @param  @param num
	 * @param  @return    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Answer createAnswer(int ques_id,int stu_id,String ans_text,Date ans_time);
}

