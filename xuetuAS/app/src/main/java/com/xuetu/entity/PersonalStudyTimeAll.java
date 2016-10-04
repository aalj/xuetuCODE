package com.xuetu.entity;

public class PersonalStudyTimeAll {
	private int timePosition;
	private Student student;
	private String timeAll;

	public PersonalStudyTimeAll() {
	}

	public PersonalStudyTimeAll(int timePosition, Student student, String timeAll) {
		super();
		this.timePosition = timePosition;
		this.student = student;
		this.timeAll = timeAll;
	}

	public int getTimePosition() {
		return timePosition;
	}

	public void setTimePosition(int timePosition) {
		this.timePosition = timePosition;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getTimeAll() {
		return timeAll;
	}

	public void setTimeAll(String timeAll) {
		this.timeAll = timeAll;
	}

}
