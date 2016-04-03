package com.xuetu.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xuetu.entity.Coupon;
import com.xuetu.entity.LongTime;
import com.xuetu.entity.Student;

import android.app.Application;

public class XueTuApplication extends Application {
	 private Student student = null;
	private  List<float[]> list = new ArrayList<float[]>();
	private Set<Integer> set = new HashSet<Integer>();
	public Set<Integer> getSet() {
		return set;
	}

	public void setSet(Set<Integer> set) {
		this.set = set;
	}

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
