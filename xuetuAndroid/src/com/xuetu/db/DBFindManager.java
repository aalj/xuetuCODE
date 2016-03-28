package com.xuetu.db;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.loc.al;
import com.xuetu.entity.Alarm;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
			ContentValues values = new ContentValues();
			values.put("plan_id", selfS.getPlanID());
			values.put("start_time", selfS.getStartTime().getTime());
			values.put("end_time", selfS.getEndTime().getTime());
			Log.i("TAG", "保存的结束时间"+selfS.getEndTime().getTime()+"");
			values.put("plan_text", selfS.getPlanText());
			values.put("plan_remind", selfS.getPlanReming());
			values.put("pattern_id", selfS.getPattern().getPatternID());
			// TODO 现在无法获取学生对象
			values.put("stu_id", 1);
			values.put("plan_date", selfS.getPlanDate().getTime());

			db.insert("selfstudyplan", null, values);
		}
		db.setTransactionSuccessful();// 设置事务成功完成

		db.endTransaction();// 结束事务
	}
	public void addSelfOne(SelfStudyPlan selfS) {
		Log.i("TAG", "添加zihua dao ");
		db.beginTransaction();// 开始事务
			ContentValues values = new ContentValues();
			values.put("plan_id", selfS.getPlanID());
			values.put("start_time", selfS.getStartTime().getTime());
			values.put("end_time", selfS.getEndTime().getTime());
			values.put("plan_text", selfS.getPlanText());
			values.put("plan_remind", selfS.getPlanReming());
			values.put("pattern_id", selfS.getPattern().getPatternID());
			// TODO 现在无法获取学生对象
			values.put("stu_id", 1);
			values.put("plan_date", selfS.getPlanDate().getTime());
			
			db.insert("selfstudyplan", null, values);
		
		db.setTransactionSuccessful();// 设置事务成功完成
		
		db.endTransaction();// 结束事务
	}

	public void addPatter(List<Pattern> patters) {
		db.beginTransaction();// 开始事务
		for (Pattern selfS : patters) {
			ContentValues values = new ContentValues();

			values.put("pattern_id", selfS.getPatternID());
			values.put("pattern_text", selfS.getPattrenText());

			db.insert("pattern", null, values);
		}
		db.setTransactionSuccessful();// 设置事务成功完成

		db.endTransaction();// 结束事务
	}

	public List<SelfStudyPlan> querySelf(int StuID) {
		String[] columns;
		String selection = "stu_id=?";
		String[] selectionArgs = { "1" };
		SelfStudyPlan plan = null;
		List<SelfStudyPlan> list = new ArrayList<SelfStudyPlan>();
		Cursor query = db.query("selfstudyplan", null, null, null, null, null, null);
		while (query.moveToNext()) {

			plan = new SelfStudyPlan();
			plan.setPlanID(query.getInt(query.getColumnIndex("plan_id")));
			String startTime = query.getString(query.getColumnIndex("start_time"));
			Date startDate = new Date(Long.parseLong(startTime));
			plan.setStartTime(startDate);

			String endTime = query.getString(query.getColumnIndex("start_time"));
			Log.i("TAG", "去出的结束时间"+endTime);
			Date endData = new Date(Long.parseLong(endTime));
			plan.setEndTime(endData);

			plan.setPlanText(query.getString(query.getColumnIndex("plan_text")));
			plan.setPlanReming(query.getInt(query.getColumnIndex("plan_remind")));
			// 通过学习模式的Id的到对应的学习模式对象
			Pattern pattern = getPatternById(query.getInt(query.getColumnIndex("pattern_id")));
			plan.setPattern(pattern);
			// TODO 无法调用方法得到对应的对象
			// plan.setStudent(null);

			String PlanDate = query.getString(query.getColumnIndex("plan_date"));
			Date parse = new Date(Long.parseLong(PlanDate));
			plan.setPlanDate(parse);
			list.add(plan);
			

		}
		return list;
	}

	private Pattern getPatternById(int int1) {

		String selection = "pattern_id=?";
		String[] selectionArgs = { "int1" };
		Cursor query = db.query("pattern", null, selection, selectionArgs, null, null, null);
		Pattern pattern = new Pattern();
		if (query.moveToNext()) {
			pattern.setPatternID(query.getInt(query.getColumnIndex("pattern_id")));
			pattern.setPattrenText(query.getString(query.getColumnIndex("pattern_text")));
		}
		return pattern;
	}

	public List<Pattern> getPattern() {
		List<Pattern> list = new ArrayList<Pattern>();
		Cursor query = db.query("pattern", null, null, null, null, null, null);
		Pattern pattern = null;
		while (query.moveToNext()) {
			pattern = new Pattern();
			pattern.setPatternID(query.getInt(query.getColumnIndex("pattern_id")));
			pattern.setPattrenText(query.getString(query.getColumnIndex("pattern_text")));
			list.add(pattern);
		}
		return list;
	}

	public void closeDB() {
		db.close();
	}

	
	/**
	 * 存时间
	 * @param alarm
	 * @return
	 */
	public boolean insertAlarm(Alarm alarm){
		
		ContentValues values= new ContentValues();
		values.put("start_time", alarm.getStartTime().getTime());
		values.put("temp_index", alarm.getTemp_index());
		values.put("week", alarm.getWeek());
		values.put("temp", alarm.getTemp());
		
		
		long insert = db.insert("alarm", null, values);
		db.close();
		if(!(insert>0)){
			return false;
		}
		return true;
		
	}
	
	/**
	 * 去时间
	 */
	public List<Alarm> queryAlarm(int temp){
		String selection ="temp=?";
		String[] selectionArgs={temp+""};
		Cursor query = db.query("alarm", null,  selection, selectionArgs, null, null, "start_time" + " DESC");
		List<Alarm> list = new ArrayList<Alarm>();
		Alarm alarm = null;
		while(query.moveToNext()){
			alarm = new Alarm();
			alarm.setAlarm_id(query.getInt(query.getColumnIndex("alarm_id")));

			int columnIndex = query.getColumnIndex("start_time");
			alarm.setStartTime(new Date(query.getInt(columnIndex)));
			
			alarm.setTemp_index(query.getInt(query.getColumnIndex("temp_index")));
			alarm.setWeek(query.getString(query.getColumnIndex("week")));
			alarm.setTemp(query.getInt(query.getColumnIndex("temp")));
			list.add(alarm);
			
		}
		query.close();
		db.close();
		return list;
		
	}
	
	public boolean updateAlarm(int alarm_id,Alarm alarm){
		ContentValues values = new ContentValues();
		values.put("start_time", alarm.getStartTime().getTime());
		values.put("temp_index", alarm.getTemp_index());
		values.put("week", alarm.getWeek());
		values.put("temp", alarm.getTemp());
		String whereClause="alarm_id=?";
		String[] whereArgs={"alarm_id"};
		int update = db.update("alarm", values, whereClause, whereArgs);
		db.close();
		if(!(update>0))
		return false;
		
		return true;
		
	}
	public boolean deleteAlarm(int alarm_id,Alarm alarm){
		String whereClause="alarm_id=?";
		String[] whereArgs={"alarm_id"};
		int delete = db.delete("alarm", whereClause, whereArgs);
		db.close();
		if(!(delete>0))
			return false;
		
		
		return true;
		
	}
	
	
	
	
	
}
