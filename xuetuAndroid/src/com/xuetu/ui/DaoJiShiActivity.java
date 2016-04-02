package com.xuetu.ui;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.params.HttpParams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Countdown;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.MyBaseAdapter;
import com.xuetu.utils.ViewHolder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DaoJiShiActivity extends Activity implements OnItemClickListener {
	@ViewInject(R.id.activity_find_task_list)
	ListView activity_find_task_list;
	SharedPreferences sp = null;
	DBFindManager db = null;
	MyBaseAdapter<Countdown> baseAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dao_ji_shi);
		ViewUtils.inject(this);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		inintView();
		db = new DBFindManager(this);
		activity_find_task_list.setOnItemClickListener(this);

	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
	private void inintView() {
		if (sp.getBoolean("countdown", true)) {
			getData();
		} else {
			list = db.queryCountdown();
		}
		baseAdapter = new MyBaseAdapter<Countdown>(list,DaoJiShiActivity.this,R.layout.dao_jishi_item) {
			
			@Override
			public void convert(ViewHolder viewHolder, Countdown item) {
				viewHolder.setText(R.id.tilte, item.getCodoText());
				Date date = item.getCodoTime();
				viewHolder.setText(R.id.textView1, dateFormat.format(date)+" "+DataToTime.getWeekOfDate(date));
				
			}
		};
		
		

	}

	List<Countdown> list = new ArrayList<>();

	private void getData() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "CountdownServlet";
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
						.disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<Countdown>>() {
				}.getType();
				list = gson.fromJson(arg0.result, type);
				//把第一次方文的数据访问到本地数据库
				sp.edit().putBoolean("countdown", false).commit();
				for (Countdown i : list) {
					db.insertCountdown(i);

				}

			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

}
