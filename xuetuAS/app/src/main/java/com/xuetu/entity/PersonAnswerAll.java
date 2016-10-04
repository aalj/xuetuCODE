package com.xuetu.entity;

public class PersonAnswerAll {
	private int answerPosition;
	private Student student;
	private String answerAll;

	public PersonAnswerAll(int answerPosition, Student student, String answerAll) {
		super();
		this.answerPosition = answerPosition;
		this.student = student;
		this.answerAll = answerAll;
	}

	@Override
	public String toString() {
		return "PersonAnswerAll [answerPosition=" + answerPosition + ", student=" + student + ", answerAll=" + answerAll
				+ "]";
	}

	public PersonAnswerAll() {
		super();
	}

	public int getAnswerPosition() {
		return answerPosition;
	}

	public void setAnswerPosition(int answerPosition) {
		this.answerPosition = answerPosition;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getAnswerAll() {
		return answerAll;
	}

	public void setAnswerAll(String answerAll) {
		this.answerAll = answerAll;
	}

}
