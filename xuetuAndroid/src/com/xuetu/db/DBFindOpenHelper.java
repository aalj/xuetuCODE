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

		String pattern = "CREATE TABLE `pattern` ( `pattern_id` "
				+ "integer NOT NULL PRIMARY KEY AUTOINCREMENT, "
				+ " `pattern_text` varchar(300) NOT NULL )";
		String self = "CREATE TABLE `selfstudyplan` (" + "`plan_id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "`start_time` date NOT NULL," + "`end_time` date NOT NULL," + "`plan_text` varchar(300) NOT NULL,"
				+ "`plan_remind` integer NOT NULL," + "`pattern_id` integer DEFAULT NULL,"
				+ "`stu_id` integer DEFAULT NULL," + "`plan_date` date NOT NULL)";
		db.execSQL(pattern);
		db.execSQL(self);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
