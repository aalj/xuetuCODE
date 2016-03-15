/**
 * 
 */
package com.xuetu.service;

import java.sql.Date;
import java.util.List;

import com.xuetu.dao.inter.QuesTionDao;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.entity.Subject;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * @author Administrator
 *
 */
public class QuestionService implements QuestionServiceInter {
	QuesTionDao q = null;
	public QuestionService(QuesTionDao q){
		this.q = q;
	}

	/* (non-Javadoc)
	 * @see com.xuetu.service.inter.QuestionServiceInter#queryQuestionByStuID(com.xuetu.entity.Student)
	 */
	@Override
	public List<Question> queryQuestionByStuID(Student stu) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xuetu.service.inter.QuestionServiceInter#getAnswerByQuesId(com.xuetu.entity.Question)
	 */
	@Override
	public Answer getAnswerByQuesId(Question qu) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xuetu.service.inter.QuestionServiceInter#queryQuestionByStuJectId(com.xuetu.entity.Subject)
	 */
	@Override
	public List<Question> queryQuestionByStuJectId(Subject sub) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xuetu.service.inter.QuestionServiceInter#queryLimitQuestion(int, int)
	 */
	@Override
	public List<Question> queryLimitQuestion(int page, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xuetu.service.inter.QuestionServiceInter#getQuestionByStuId(int)
	 */
	@Override
	public Question getQuestionByStuId(int stuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School getSchoolBySch_id(int sch_id) {
		// TODO Auto-generated method stub
		return q.getSchoolBySch_id(sch_id);
	}

	@Override
	public Subject getSubjectBySubId(int subId) {
		// TODO Auto-generated method stub
		return q.getSubjectBySubId(subId);
	}

	@Override
	public Student getStudentByStuId(int stuId, int sch_id) {
		// TODO Auto-generated method stub
		return q.getStudentByStuId(stuId, sch_id);
	}

	@Override
	public void submitQuestion(Question question) {
		// TODO Auto-generated method stub
		q.submitQuestion(question);
	}

	@Override
	public Question createQuestion(int stuId, String quesText, Date quesTime, int acpoNum, int subId, int schId) {
		// TODO Auto-generated method stub
		return q.createQuestion(stuId, quesText, quesTime, acpoNum, subId, schId);
	}
	
}
