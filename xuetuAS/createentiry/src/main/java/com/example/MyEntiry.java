package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyEntiry {

    public static void main(String[] arg0) throws Exception {
        MyEntiry myEntiry = new MyEntiry();
        //参数一：表示的是数据库的发版本号
        //参数二：表示的是数据实体类的的包路径
        Schema schema = new Schema(1, "com.xuetu.databases");
        myEntiry.addStudent(schema);

        new DaoGenerator().generateAll(schema,"D:\\my_owe_code\\android\\Android_Studio\\xuetuCODE\\xuetuAS\\app\\src\\main\\java");

    }


    private void addStudent(Schema schema) {
        //创建数据库实体。
        //参数是数据库的数据库名
        Entity entity = schema.addEntity("Student");

        //依次增加数据库的字段
        entity.addIdProperty();//ID
        entity.addStringProperty("stuName");
        entity.addStringProperty("stuEmail");
        entity.addStringProperty("stuPhone");
        entity.addStringProperty("stuIma");
        entity.addStringProperty("stuSex");
        entity.addIntProperty("stuAge");
        entity.addStringProperty("stuUgrade");
        entity.addStringProperty("stuMajor");
        entity.addStringProperty("stuSigner");
        entity.addIntProperty("stuPoints");
        entity.addIntProperty("school");
        entity.addStringProperty("stuPwd");
        entity.addDateProperty("Stu_create_date");
    }


}
