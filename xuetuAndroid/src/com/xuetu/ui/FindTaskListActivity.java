package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.gc.materialdesign.views.Switch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.adapter.MyQuestionBaseAdapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.ui.AddAlarm.TimeListener;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * ClassName:FindTaskListActivity<br/>
 * 
 * Function: 显示任务大致信息的页面<br/>
 * 
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年3月9日 下午11:05:19
 *
 * @see
 */
public class FindTaskListActivity extends Baseactivity
		implements OnItemClickListener, OnClickListener, OnRefreshListener, OnKeyListener {
	@ViewInject(R.id.activity_find_task_list)
	ListView activityFindTaskList;
	// 从网上下来的数据源
	List<SelfStudyPlan> users = new ArrayList<SelfStudyPlan>();
	public static final int SELF_CODE = 100;
	private static final int ADD_SELF = 101;
	SharedPreferences sp = null;
	DBFindManager dbFindManager = null;
	@ViewInject(R.id.title_my)
	TitleBar titleBar = null;
	// 用于保存是点击那个对象，然后进行修改
	int index = 0;
	// listView的适配器
	MyQuestionBaseAdapter adapter = null;
	// 求情
	HttpUtils httpUtils = null;
	// 用于保存修改的数据
	List<Integer> list = new ArrayList<Integer>();
	List<Pattern> listpattern;
	Gson gson = null;
	SwipeRefreshLayout mSwipeLayout;
	boolean tempre = true;
	ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_task_list);
		ViewUtils.inject(this);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
		mSwipeLayout.setOnRefreshListener(this);
		/**
		 * 设置刷新时候的颜色
		 */
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		dbFindManager = new DBFindManager(this);
		getData();
		viewInit();
		boolean savePatternBoolean = sp.getBoolean("savePatternBoolean", true);
		if (savePatternBoolean) {
			mySengHttp();
		}
		activityFindTaskList.setOnItemClickListener(this);

	}

	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(FindTaskListActivity.this, "", "正在加载...");
			progressDialog.setCancelable(true);
			progressDialog.show();
			progressDialog.setOnKeyListener(this);
		} else {

		}
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (progressDialog != null)
				progressDialog.dismiss();
		}
		return false;
	}

	// 页面初始化方法
	private void viewInit() {
		showDengdai();
		titleBar.setRightLayoutClickListener(this);
		titleBar.setLeftLayoutClickListener(this);

	}

	//
	List<Integer> listTag = new ArrayList<Integer>();
	private void getData() {

		httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetSelfStudyPlan";
		Log.i("TAG", url);
		RequestParams pram = new RequestParams();
		// TODO 无法获得学生对象的数据

		String StuId = ((XueTuApplication) getApplication()).getStudent().getStuId() + "";

		pram.addBodyParameter("StuID", StuId);
		httpUtils.send(HttpMethod.POST, url, pram, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Log.i("TAG", arg1);

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				Type type = new TypeToken<List<SelfStudyPlan>>() {
				}.getType();
				users = gson.fromJson(arg0.result, type);
				Log.i("TAG", "gerenjihua tinahus " + users.size() + "");

				
				dbFindManager.addSelf(users);
				sp.edit().putBoolean("SELELIST", true).commit();
				adapter = new MyQuestionBaseAdapter<SelfStudyPlan>(FindTaskListActivity.this, users,
						R.layout.find_task_item) {

					@Override
					public void convert(ViewHodle viewHolder, SelfStudyPlan item, int position) {
						viewHolder.setReLayoutBgColor(R.id.layoutback, R.drawable.back_find_list_self2);
						long time = System.currentTimeMillis();
						long timete = (item.getEndTime().getTime() - item.getStartTime().getTime());

						String dataToHS = DataToTime.secToTime((int) (timete / 1000));
						RelativeLayout re = viewHolder.getView(R.id.layoutback);
						viewHolder.setText(R.id.tilte, item.getPlanText());
						viewHolder.setText(R.id.info, DataToTime.dataToT(item.getStartTime()));
						viewHolder.setText(R.id.tv_time, dataToHS);
						
						// 在集合中标记是否有同时用途判断是否复用
//						if (listTag.contains(position)) {
//							viewHolder.setReLayoutBgColor(R.id.layoutback, R.drawable.back_find_list_self);
//						}
//						re.setTag(position);
						if (item.getStartTime().getTime() > time) {

						} else {
//							viewHolder.setText(R.id.lishi, "历史");
//							listTag.add((Integer) re.getTag());
							viewHolder.setReLayoutBgColor(R.id.layoutback, R.drawable.back_find_list_self);

						}
						// 设置选择选项
						if (item.getPlanReming() == 1) {
							viewHolder.setClick(R.id.myswitch, true);
						} else {
							viewHolder.setClick(R.id.myswitch, false);
						}

						
						Log.i("TAG", dataToHS);

						

						

					}

				};

				if (tempre) {

					if (progressDialog != null)
						progressDialog.dismiss();
					activityFindTaskList.setAdapter(adapter);
					if (users.size() <= 0) {
						Toast.makeText(getApplicationContext(), "今天还没有计划,赶快去添加吧！", 0).show();
					}

				} else {
					activityFindTaskList.setAdapter(adapter);

					// adapter.notifyDataSetChanged();
					/**
					 * 设置刷新结束之后，圈停止转动
					 */
					mSwipeLayout.setRefreshing(false);
				}

			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 使用万能适配器写ListView 数据
		Intent intent = new Intent(this, FindTaskItemActivity.class);
		intent.putExtra("plans", users.get(position));
		index = position;

		startActivityForResult(intent, SELF_CODE);

	}

	// 又返回值得页面跳转
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1010 && requestCode == SELF_CODE) {
			SelfStudyPlan selfStudyPlan = (SelfStudyPlan) data.getSerializableExtra("name1");
			// 得到修改过后的对象-----获取是那个对象的修改
			users.remove(index);
			users.add(index, selfStudyPlan);
			adapter.notifyDataSetChanged();
			Log.i("TAG", "selfStudyPlan.toString()------->>>>" + selfStudyPlan.toString());
			saveChangSelf(selfStudyPlan);
			Toast.makeText(getApplicationContext(), selfStudyPlan.getPlanText(), 0).show();
		}

		if (resultCode == 1011 && requestCode == ADD_SELF) {
			Toast.makeText(getApplicationContext(), "qingjian sekf", 0).show();
			SelfStudyPlan selfStudyPlan = (SelfStudyPlan) data.getSerializableExtra("selfa");
			users.add(selfStudyPlan);
			// addChangSelf(selfStudyPlan);
			adapter.notifyDataSetChanged();

		}
		if (resultCode == 1012 && requestCode == SELF_CODE) {
			// SelfStudyPlan selfStudyPlan = (SelfStudyPlan)
			// data.getSerializableExtra("name1");
			users.remove(index);
			adapter.notifyDataSetChanged();

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	// 保存修改的内容
	private void saveChangSelf(SelfStudyPlan selfStudyPlan) {
		if (httpUtils != null) {
			String url = GetHttp.getHttpLJ() + "SavaChangSelfPlan";
			RequestParams patram = new RequestParams();
			String json = gson.toJson(selfStudyPlan);
			try {
				patram.addBodyParameter("self", URLEncoder.encode(json, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpUtils.send(HttpMethod.POST, url, patram, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Toast.makeText(getApplicationContext(), arg0.result, 0).show();

				}
			});
		}

	}

	// 标题栏的点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_layout:
			Intent intent = new Intent();
			intent.setClass(FindTaskListActivity.this, AddSelfPlanActivity.class);
			intent.putExtra("tempself", 1);
			startActivityForResult(intent, ADD_SELF);
			Toast.makeText(getApplicationContext(), "添加", 0).show();

			break;
		case R.id.left_layout:

			finish();
			Toast.makeText(getApplicationContext(), "返回", 0).show();

			break;

		default:
			break;
		}

	}

	// 获取学习类型的模式
	private void mySengHttp() {
		Log.i("TAG", "获取学习类型");
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetPatterServlet";
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new Gson();
				Type type = new TypeToken<List<Pattern>>() {
				}.getType();
				listpattern = gson.fromJson(arg0.result, type);
				Editor edit = sp.edit();

				dbFindManager.addPatter(listpattern);
				edit.putBoolean("savePatternBoolean", false);
				edit.commit();
				// TODO 需要把数据存到本地数据库

			}
		});

	}


	@Override
	public void onRefresh() {
		tempre = false;
		getData();

	}

}
