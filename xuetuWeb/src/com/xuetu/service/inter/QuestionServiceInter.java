/**
 * 
 */
package com.xuetu.service.inter;

import java.sql.Date;
import java.util.List;

import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.entity.Subject;

/**
 * ClassName:QuestionServiceInter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lin
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月14日		下午04:54
 *
 * @see 	 

 */
public interface QuestionServiceInter {
	
	public List<Question> queryQuestionByStuID(Student stu);

	
	public List<Answer> getAnswerByQuesId(int ques_id,int page,int num);

	
	public List<Question> queryQuestionByStuJectId(Subject sub);

	public List<Question> queryLimitQuestion(int page, int num);

	public Question getQuestionByStuId(int stuId);
	
	public School getSchoolBySch_id(int sch_id);
	
	public Subject getSubjectBySubId(int subId);
	
	public Student getStudentByStuId(int stuId,int sch_id);
	
	public void submitQuestion(Question q);

	public Question createQuestion(int stuId,String quesText,String quesImg,Date quesTime,int acpoNum,int subId,int schId);

	public int getSchIdByStuId(int StuId); 
	
	public Answer createAnswer(int ques_id,int stu_id,String ans_text,String ans_ima,Date ans_time);
	
	public List<Answer> queryLimitAnswer(int page,int num);
	
	public void submitAnswer(Answer answer);
	
	public Question getQuestionByQuesId(int ques_id);
	
	
}
