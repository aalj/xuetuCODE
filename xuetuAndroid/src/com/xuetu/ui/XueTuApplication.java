package com.xuetu.ui;

import com.xuetu.entity.Student;

import android.app.Application;

public class XueTuApplication extends Application {
	 private Student student = null;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
}
