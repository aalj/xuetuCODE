package com.xuetu.databases;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "STUDENT".
 */
public class Student {

    private Long id;
    private String stuName;
    private String stuEmail;
    private String stuPhone;
    private String stuIma;
    private String stuSex;
    private Integer stuAge;
    private String stuUgrade;
    private String stuMajor;
    private String stuSigner;
    private Integer stuPoints;
    private Integer school;
    private String stuPwd;
    private java.util.Date Stu_create_date;

    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String stuName, String stuEmail, String stuPhone, String stuIma, String stuSex, Integer stuAge, String stuUgrade, String stuMajor, String stuSigner, Integer stuPoints, Integer school, String stuPwd, java.util.Date Stu_create_date) {
        this.id = id;
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
        this.Stu_create_date = Stu_create_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuIma() {
        return stuIma;
    }

    public void setStuIma(String stuIma) {
        this.stuIma = stuIma;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuUgrade() {
        return stuUgrade;
    }

    public void setStuUgrade(String stuUgrade) {
        this.stuUgrade = stuUgrade;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public String getStuSigner() {
        return stuSigner;
    }

    public void setStuSigner(String stuSigner) {
        this.stuSigner = stuSigner;
    }

    public Integer getStuPoints() {
        return stuPoints;
    }

    public void setStuPoints(Integer stuPoints) {
        this.stuPoints = stuPoints;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public String getStuPwd() {
        return stuPwd;
    }

    public void setStuPwd(String stuPwd) {
        this.stuPwd = stuPwd;
    }

    public java.util.Date getStu_create_date() {
        return Stu_create_date;
    }

    public void setStu_create_date(java.util.Date Stu_create_date) {
        this.Stu_create_date = Stu_create_date;
    }

}