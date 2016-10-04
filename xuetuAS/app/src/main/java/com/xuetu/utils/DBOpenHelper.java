package com.xuetu.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "myclasses.db";
	private static final int DATABASE_VERSION = 1;

	public DBOpenHelper(Context context) {
		// CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 创建本地数据库
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE localclasses(cls_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,cls_name varchar(60) NOT NULL,cls_week integer(11) NOT NULL, cls_few integer(11) NOT NULL,cls_room varchar(30) NOT NULL)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
