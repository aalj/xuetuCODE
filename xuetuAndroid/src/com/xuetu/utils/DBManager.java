package com.xuetu.utils;

import java.util.ArrayList;
import java.util.List;

import com.xuetu.entity.MyClass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 封装所有的业务方法
 * 
 * 
 * @author BCL
 *
 */
public class DBManager {
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;

	public DBManager(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = dbOpenHelper.getWritableDatabase();
	}

	/**
	 * 添加对象
	 * 存值进本地数据库
	 */
	public void add(List<MyClass> myclass) {
		db.beginTransaction();// 开始事务
		for (MyClass class1 : myclass) {
			db.execSQL("insert into localclasses values(?,?,?,?,?)", new Object[] { class1.getClsId(),
					class1.getClasName(), class1.getClsWeek(), class1.getClsFew(), class1.getClsRoom() });
		}
		db.setTransactionSuccessful();// 设置事务成功完成

		db.endTransaction();// 结束事务

	}

	/**
	 * query all classes,return list
	 * 显示本地数据库的值
	 * @return
	 */
	public List<MyClass> query() {
		ArrayList<MyClass> myclass = new ArrayList<MyClass>();
		Cursor c = queryTheCursor();
		while (c.moveToNext()) {
			MyClass mycls = new MyClass();
			mycls.setClsId(c.getInt(c.getColumnIndex("cls_id")));
			mycls.setClasName(c.getString(c.getColumnIndex("cls_name")));
			mycls.setClsFew(c.getInt(c.getColumnIndex("cls_few")));
			mycls.setClsRoom(c.getString(c.getColumnIndex("cls_room")));
			mycls.setClsWeek(c.getInt(c.getColumnIndex("cls_week")));
			myclass.add(mycls);
		}
		c.close();
		return myclass;
	}

	private Cursor queryTheCursor() {
		Cursor c = db.rawQuery("select * from localclasses", null);
		return c;
	}
/***
 * 关闭database
 */
	public void closeDB() {
		db.close();
	}

}
