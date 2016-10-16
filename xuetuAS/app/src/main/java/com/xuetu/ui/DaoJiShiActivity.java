package com.xuetu.ui;

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
import com.xuetu.R;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Countdown;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.MyBaseAdapter;
import com.xuetu.utils.ViewHolder;
import com.xuetu.widget.TitleBar;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class DaoJiShiActivity extends Activity implements OnItemClickListener, OnClickListener {
	protected static final String TAG = "TAG";
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
	NotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dao_ji_shi);
		ViewUtils.inject(this);
		db = new DBFindManager(this);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// inintView();
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
		}
		final SimpleDateFormat sim = new SimpleDateFormat("MM:dd");
		baseAdapter = new MyBaseAdapter<Countdown>(list, DaoJiShiActivity.this, R.layout.dao_jishi_item) {

			@Override
			public void convert(ViewHolder viewHolder, Countdown item) {
				long time = System.currentTimeMillis();
				TextView  view = viewHolder.getView(R.id.textView2);
				view.setVisibility(View.VISIBLE);
				viewHolder.setText(R.id.tilte, item.getCodoText());
				Date date = item.getCodoTime();
				viewHolder.setText(R.id.textView1, dateFormat.format(date) + " " + DataToTime.getWeekOfDate(date));
				if (sim.format(item.getCodoTime()).equals(sim.format(new Date(System.currentTimeMillis())))) {

					viewHolder.setText(R.id.textView3, "今天");
					viewHolder.getView(R.id.textView2).setVisibility(View.INVISIBLE);;
					
				} else {
					viewHolder.setText(R.id.textView3, getDay(item.getCodoTime().getTime()) + "");
				}
			}

		};

		activity_find_task_list.setAdapter(baseAdapter);

	}

	@Override
	protected void onResume() {
		Log.i("TAG", "好事吗");
		inintView();
		// baseAdapter.notifyDataSetChanged();
		super.onResume();
	}

	private void getData() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "CountdownServlet";
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

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
		if (s1 < s2) {
			return 365 - (day + 1);
		}
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
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1011) {// 增加优惠券
			Countdown countdown = (Countdown) data.getSerializableExtra("countdown");
			Log.i("TAG", "countdown.getCodo_index()" + countdown.getCodo_index());
			db.insertCountdown(countdown);
			showNatifacation(countdown);

		}
		if (resultCode == 1012) {// 修改优惠券
			Countdown countdown = (Countdown) data.getSerializableExtra("countdown");
			Log.i("TAG", "countdown.getCodo_index()" + countdown.getCodo_index());
			db.updateCountdown(countdown.getCodoID(), countdown);
			showNatifacation(countdown);
		}

	}

	private void showNatifacation(Countdown countdown) {
		Log.i(TAG, countdown.getTemp_time() + "");
		if (countdown.getCodo_index() == 0) {
			Log.i(TAG, "取消内容");

			manager.cancel(countdown.getTemp_time());
		} else if (countdown.getCodo_index() == 1) {
			Log.i(TAG, "asdfasdfasdfsadfasfdasd");
			notifa(countdown);

		}
	}

	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

	public void notifa(Countdown countdown) {
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,
				new Intent(this, com.xuetu.ui.DaoJiShiActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
		// 通过Notification.Builder来创建通知，注意API Level
		// API11之后才支持
		Notification notify2 = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
				.setOngoing(true) // icon)
				.setTicker("您有一个倒计时" + DataToTime.getDay(countdown.getCodoTime().getTime()) + "天")// 设置在status
				// bar上显示的提示文字
				.setContentTitle(countdown.getCodoText())// 设置在下拉status
				// bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
				.setContentText(format.format(countdown.getCodoTime()))// TextView中显示的详细内容
				.setContentIntent(pendingIntent2) // 关联PendingIntent
				.setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
				.getNotification(); // 需要注意build()是在API level
		// 16及之后增加的，在API11中可以使用getNotificatin()来代替
		notify2.flags|= Notification.FLAG_ONGOING_EVENT;
		Log.i("TAG", countdown.getTemp_time() + "------标志显示的时间------>>>>>");
		manager.notify(countdown.getTemp_time(), notify2);
	}

}
