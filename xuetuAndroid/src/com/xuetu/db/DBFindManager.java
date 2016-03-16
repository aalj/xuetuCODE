package com.xuetu.db;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.entity.MyClass;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;

import android.content.ContentValues;
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
public class DBFindManager {
	DBFindOpenHelper dbOpenHelper;
	SQLiteDatabase db;

	public DBFindManager(Context context) {
		dbOpenHelper = new DBFindOpenHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = dbOpenHelper.getReadableDatabase();
	}

	public void addSelf(List<SelfStudyPlan> selfStudyPlans) {
		db.beginTransaction();// 开始事务
		for (SelfStudyPlan selfS : selfStudyPlans) {
			ContentValues values=new ContentValues();
			values.put("plan_id", selfS.getPlanID());
			values.put("start_time", selfS.getStartTime().getTime());
			values.put("end_time", selfS.getEndTime().getTime());
			values.put("plan_text", selfS.getPlanText());
			values.put("plan_remind", selfS.getPlanReming());
			values.put("pattern_id", selfS.getPattern().getPatternID());
			values.put("stu_id", selfS.getStudent().getStuId());
			values.put("plan_date", selfS.getPlanDate().getTime());
			
			
			db.insert("selfstudyplan", null, values);
		}
		db.setTransactionSuccessful();// 设置事务成功完成

		db.endTransaction();// 结束事务

	}
	public void addPatter(List<Pattern> patters) {
		db.beginTransaction();// 开始事务
		for (Pattern selfS : patters) {
			ContentValues values=new ContentValues();

			values.put("pattern_id", selfS.getPatternID());
			values.put("pattern_text", selfS.getPattrenText());
			
			
			db.insert("pattern", null, values);
		}
		db.setTransactionSuccessful();// 设置事务成功完成
		
		db.endTransaction();// 结束事务
		
	}
//	public List<SelfStudyPlan> queryAllSelfPlan(){
//		List<SelfStudyPlan>  list = new ArrayList<SelfStudyPlan>();
//		String sql="select * from selfstudyplan";
//		Cursor query = db.rawQuery(sql, null);
//		SelfStudyPlan plan=null;
//		while (query.moveToNext()) {
//			plan = new SelfStudyPlan();
//			query.ge
//			plan.setPlanID(query.getInt(query.getColumnIndex("plan_id")));
//			Date date = query.getDate(query.getColumnIndex("start_time"));
//			System.out.println(date.getTime());
//			plan.setStartTime(query.getTimestamp("start_time"));
//			plan.setEndTime(query.getTimestamp("end_time"));
//			plan.setPlanText(query.getString("plan_text"));
//			plan.setPlanReming(query.getInt("plan_remind"));
//			// 通过学习模式的Id的到对应的学习模式对象
//			Pattern pattern = getPatternById(query.getInt("pattern_id"));
//			plan.setPattern(pattern);
//			// TODO 无法调用方法得到对应的对象
//			plan.setStudent(null);
//			plan.setPlanDate(query.getTimestamp("plan_date"));
//
//			list.add(plan);
//			
//		}
//		
//		return list;
//	}


	public void closeDB() {
		db.close();
	}

}
