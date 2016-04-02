package com.xuetu.ui;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.xuetu.EditCountdownActivity;
import com.xuetu.R;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Countdown;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.MyBaseAdapter;
import com.xuetu.utils.ShowDialog;
import com.xuetu.utils.ViewHolder;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class DaoJiShiActivity extends Activity implements OnItemClickListener, OnClickListener {
	protected static final String TAG = null;
	@ViewInject(R.id.activity_find_task_list)
	ListView activity_find_task_list;
	@ViewInject(R.id.title_my)
	TitleBar title_my;
	SharedPreferences sp = null;
	DBFindManager db = null;
	MyBaseAdapter<Countdown> baseAdapter = null;
	List<Countdown> list = new ArrayList<>();
	Handler handler = new Handler() {
		@Override
		public String getMessageName(Message message) {
			if (message.what == 123) {
				activity_find_task_list.setAdapter(baseAdapter);
			}
			return super.getMessageName(message);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dao_ji_shi);
		ViewUtils.inject(this);
		db = new DBFindManager(this);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		inintView();
		title_my.setLeftLayoutClickListener(this);
		title_my.setRightLayoutClickListener(this);
		activity_find_task_list.setOnItemClickListener(this);

	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

	private void inintView() {
		if (sp.getBoolean("countdown", true)) {
			getData();
		} else {
			list = db.queryCountdown();
			Log.i("TAG", "本地-----》》》" + list.toString());
		}
		// List<Countdown> liatold = new ArrayList<>();
		// for (Countdown i : list) {
		// if(i.getCodoTime().getTime()>System.currentTimeMillis()){
		// liatold.add(i);
		// }
		// }
		// list.clear();
		// list.addAll(liatold);

		baseAdapter = new MyBaseAdapter<Countdown>(list, DaoJiShiActivity.this, R.layout.dao_jishi_item) {

			@Override
			public void convert(ViewHolder viewHolder, Countdown item) {
				long time = System.currentTimeMillis();

				viewHolder.setText(R.id.tilte, item.getCodoText());
				Date date = item.getCodoTime();
				Log.i(TAG, "shaijian semgksdflkg " + date.getTime() + "");
				viewHolder.setText(R.id.textView1, dateFormat.format(date) + " " + DataToTime.getWeekOfDate(date));
				viewHolder.setText(R.id.textView3, getDay(item.getCodoTime().getTime()) + "");

			}

		};

		activity_find_task_list.setAdapter(baseAdapter);

	}

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
				Message message = Message.obtain();
				message.what = 123;
				handler.sendMessage(message);

				Log.i("TAG", list.toString());
				// 把第一次方文的数据访问到本地数据库
				sp.edit().putBoolean("countdown", false).commit();
				for (Countdown i : list) {
					Log.i("TAG", "shijian shiao " + i.getCodoTime().getTime());
					db.insertCountdown(i);

				}

			}
		});

	}

	public int getDay(long s1) {

		long s2 = System.currentTimeMillis();// 得到当前的毫秒
		int day = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
		return day + 1;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.putExtra("countdown", list.get(position));
		intent.setClass(this, EditCountdownActivity.class);
		startActivityForResult(intent, 1010);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_layout:
			Intent intent = new Intent();
			intent.setClass(this, AddCountDownActivity.class);
			startActivityForResult(intent, 1010);

			break;
		case R.id.left_layout:
			finish();
			break;
		default:
			break;
		}

	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1011){
			Log.i("TAG", "传回地搜集和电话imrojdfg  ");
			Countdown countdown = (Countdown) data.getSerializableExtra("countdown");
			list.add(0,countdown);
			db.insertCountdown(countdown);
			baseAdapter.notifyDataSetChanged();
		}
		if(resultCode==1012){
			Log.i("TAG", "传回地搜集和电话imrojdfg  ");
			Countdown countdown = (Countdown) data.getSerializableExtra("countdown");
			list.remove(countdown);
			baseAdapter.notifyDataSetChanged();
		}
		
		
		
	}

}
