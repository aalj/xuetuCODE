package com.xuetu.entity;

import java.util.Date;

public class Question {
	
	public Question(){
		
	};
	public Question(String ques_text, int ques_img, Date ques_time,
			int sub_id) {
		super();
		this.ques_text = ques_text;
		this.ques_img = ques_img;
		this.ques_time = ques_time;
		this.sub_id = sub_id;
	}
	public Question(int ques_id, int stu_id, String ques_text, int ques_img,
			Date ques_time, int acpo_num, int sub_id) {
		super();
		this.ques_id = ques_id;
		this.stu_id = stu_id;
		this.ques_text = ques_text;
		this.ques_img = ques_img;
		this.ques_time = ques_time;
		this.acpo_num = acpo_num;
		this.sub_id = sub_id;
	}
	//问题id
	int ques_id;
	//学生id
	int stu_id;
	//问题的描述
	String ques_text;
	//问题图片
	int ques_img;
	//问题时间
	Date ques_time;
	//积分数
	int acpo_num;
	//学科类别
	int sub_id;
	public int getQues_id() {
		return ques_id;
	}
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getQues_text() {
		return ques_text;
	}
	public void setQues_text(String ques_text) {
		this.ques_text = ques_text;
	}
	public int getQues_img() {
		return ques_img;
	}
	public void setQues_img(int ques_img) {
		this.ques_img = ques_img;
	}
	public Date getQues_time() {
		return ques_time;
	}
	public void setQues_time(Date ques_time) {
		this.ques_time = ques_time;
	}
	public int getAcpo_num() {
		return acpo_num;
	}
	public void setAcpo_num(int acpo_num) {
		this.acpo_num = acpo_num;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	@Override
	public String toString() {
		return "question [ques_id=" + ques_id + ", stu_id=" + stu_id
				+ ", ques_text=" + ques_text + ", ques_img=" + ques_img
				+ ", ques_time=" + ques_time + ", acpo_num=" + acpo_num
				+ ", sub_id=" + sub_id + "]";
	}
}
