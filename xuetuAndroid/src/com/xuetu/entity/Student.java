/**
 * Student.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月29日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;

/**
 * ClassName:Student <br/>
 * Function: 学生实体 
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年1月29日 上午11:15:10
 *
 * @see
 * 
 */
public class Student {

	private int stuId = 0;

	private String stuName = null;

	private String stuEmail = null;
	private String stuPhone = null;
	private String stuIma = null;
	private String stuSex = null;

	private int stuAge = 0;
	private String stuUgrade = null;
	private String stuMajor = null;
	private String stuSigner = null;
	private int stuPoints = 0;
	private School school = null;
	private String stuPwd = null;

	public Student() {

		//

	}

	public Student(int stuId, String stuName, String stuEmail, String stuPhone, String stuIma, String stuSex,
			int stuAge, String stuUgrade, String stuMajor, String stuSigner, int stuPoints, School school,
			String stuPwd) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuEmail = stuEmail;
		this.stuPhone = stuPhone;
		this.stuIma = stuIma;
		this.stuSex = stuSex;
		this.stuAge = stuAge;
		this.stuUgrade = stuUgrade;
		this.stuMajor = stuMajor;
		this.stuSigner = stuSigner;
		this.stuPoints = stuPoints;
		this.school = school;
		this.stuPwd = stuPwd;
	}

	/**
	 * 
	 * getStuId:(得到学生的ID)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return int DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public int getStuId() {
		return stuId;
	}

	/**
	 * 
	 * setStuId:(设置学生的ID)<br/>
	 *
	 * @param @param
	 *            stuId 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	/**
	 * 
	 * getStuName:(得到学生的姓名)<br/>
	 *
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuName() {
		return stuName;
	}

	/**
	 * 
	 * setStuName:(设置学生姓名)<br/>
	 *
	 * @param @param
	 *            stuName 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	/**
	 * 
	 * getStuEmail:(得到学生的邮箱)<br/>
	 *
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuEmail() {
		return stuEmail;
	}

	/**
	 * 
	 * setStuEmail:(设置学生的邮箱)<br/>
	 *
	 * @param @param
	 *            stuEmail 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	/**
	 * 
	 * getStuPhone:(的到学生的电话号码)<br/>
	 *
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuPhone() {
		return stuPhone;
	}

	/**
	 * 
	 * setStuPhone:(设置学生的电话号码)<br/>
	 *
	 * @param @param
	 *            stuPhone 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	/**
	 * 
	 * getStuIma:(的到学生的头像地址)<br/>
	 *
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuIma() {
		return stuIma;
	}

	/**
	 * 
	 * setStuIma:(设置学生头像的地址)<br/>
	 *
	 * @param @param
	 *            stuIma 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuIma(String stuIma) {
		this.stuIma = stuIma;
	}

	/**
	 * 
	 * getStuSex:(得到学生的性别)<br/>
	 *
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuSex() {
		return stuSex;
	}

	/**
	 * 
	 * setStuSex:(设置学生的性别)<br/>
	 *
	 * @param @param
	 *            stuSex 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	/**
	 * 
	 * getStuAge:(得到学生的年龄)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return int DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public int getStuAge() {
		return stuAge;
	}

	/**
	 * 
	 * setStuAge:(设置学生的年龄)<br/>
	 *
	 * 
	 * @param @param
	 *            stuAge 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}

	/**
	 * 
	 * getStuUgrade:(得到学生的年级)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuUgrade() {
		return stuUgrade;
	}

	/**
	 * 
	 * setStuUgrade:(设置学生的年级)<br/>
	 *
	 * 
	 * @param @param
	 *            stuUgrade 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuUgrade(String stuUgrade) {
		this.stuUgrade = stuUgrade;
	}

	/**
	 * 
	 * getStuMajor:(得到学生的专业)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuMajor() {
		return stuMajor;
	}

	/**
	 * 
	 * setStuMajor:(设置学生的专业)<br/>
	 *
	 * 
	 * @param @param
	 *            stuMajor 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuMajor(String stuMajor) {
		this.stuMajor = stuMajor;
	}

	/**
	 * 
	 * getStuSigner:(得到学生的签名)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuSigner() {
		return stuSigner;
	}

	/**
	 * 
	 * setStuSigner:(设置学生的签名)<br/>
	 *
	 * 
	 * @param @param
	 *            stuSigner 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuSigner(String stuSigner) {
		this.stuSigner = stuSigner;
	}

	/**
	 * 
	 * getStuPoints:(得到学生的点赞数)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return int DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public int getStuPoints() {
		return stuPoints;
	}

	/**
	 * 
	 * setStuPoints:(设置学生的点赞数)<br/>
	 *
	 * 
	 * @param @param
	 *            stuPoints 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuPoints(int stuPoints) {
		this.stuPoints = stuPoints;
	}

	/**
	 * 
	 * getSchool:(得到学生属于那所学校)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return School DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * 
	 * setSchool:(设置学生属于那所学校)<br/>
	 *
	 * 
	 * @param @param
	 *            school 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * 
	 * getStuPwd:(得到学生的密码)<br/>
	 *
	 * 
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public String getStuPwd() {
		return stuPwd;
	}

	/**
	 * 
	 * setStuPwd:(设置学生的密码)<br/>
	 *
	 * 
	 * @param @param
	 *            stuPwd 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}

}
