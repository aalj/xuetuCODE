package com.xuetu.ui;

import java.util.ArrayList;
import java.util.List;

import com.xuetu.entity.Coupon;
import com.xuetu.entity.LongTime;
import com.xuetu.entity.Student;

import android.app.Application;

public class XueTuApplication extends Application {
	 private Student student = null;
	private  List<float[]> list = new ArrayList<float[]>();
	public List<float[]> getList() {
		return list;
	}

	public void setList(List<float[]> list) {
		this.list = list;
	}

	private List<Coupon> listConpun = new ArrayList<Coupon>();
	public Student getStudent() {
		return student;
	}

	public List<Coupon> getListConpun() {
		return listConpun;
	}

	public void setListConpun(List<Coupon> listConpun) {
		this.listConpun = listConpun;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void onCreate() {
		
		super.onCreate();
		student=new Student();
	}
}
