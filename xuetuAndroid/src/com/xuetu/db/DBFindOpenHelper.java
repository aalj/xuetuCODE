package com.xuetu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBFindOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "find.db";
	private static final int DATABASE_VERSION = 1;

	public DBFindOpenHelper(Context context) {
		// CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 创建本地数据库
	@Override
	public void onCreate(SQLiteDatabase db) {

		String pattern = "CREATE TABLE `pattern` ( `pattern_id` " + "integer NOT NULL PRIMARY KEY AUTOINCREMENT, "
				+ " `pattern_text` varchar(300) NOT NULL )";
		db.execSQL(pattern);
		String sql = "CREATE TABLE `alarm` ( " + "`alarm_id` integer NOT NULL PRIMARY KEY AUTOINCREMENT, "
				+ "`time_hour` integer NOT NULL,  " 
				+ "`time_min` integer NOT NULL,  " 
				+ "`temp_index`  NOT NULL default 0, " 
				+ "`week` varchar(30) default '1',"
				+ "'tishiyu' 	varchar(100) not null default '？？？？'," 
				+ "`temp` integer NOT NULL default 0,"
				+ "'pickedUri'  varchar(100) default '2')";
		db.execSQL(sql);

		String sql2 = "CREATE TABLE `countdown` (  " + "`codo_id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `code_time` varchar(100) default 0,  " + "`codo_text` varchar(100) default NULL unique );";
		db.execSQL(sql2);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
