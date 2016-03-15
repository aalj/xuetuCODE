package com.xuetu.ui;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
public class FindTaskListActivity extends Activity implements OnItemClickListener {
	@ViewInject(R.id.activity_find_task_list)
	ListView activityFindTaskList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_task_list);
		ViewUtils.inject(this);
		// viewInit();
		getData();
		activityFindTaskList.setOnItemClickListener(this);

	}

	private void getData() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetSelfStudyPlan";
		Log.i("TAG", url);
		RequestParams pram = new RequestParams();
		// TODO 无法获得学生对象的数据
		pram.addBodyParameter("StuID", "1");
		httpUtils.send(HttpMethod.POST, url, pram, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Log.i("TAG", arg1);

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				Type type = new TypeToken<List<SelfStudyPlan>>() {
				}.getType();
				List<SelfStudyPlan> users = gson.fromJson(arg0.result, type);
				activityFindTaskList.setAdapter(
						new MyBasesadapter<SelfStudyPlan>(FindTaskListActivity.this, users, R.layout.find_task_item) {

					@Override
					public void convert(ViewHodle viewHolder, SelfStudyPlan item) {
						viewHolder.setText(R.id.tilte, item.getPlanText());
						viewHolder.setText(R.id.info, DataToTime.dataToT(item.getStartTime()));
						if (item.getPlanReming() == 1) {
							viewHolder.setClick(R.id.myswitch, true);
						} else {
							viewHolder.setClick(R.id.myswitch, false);
						}

						long timete = (item.getEndTime().getTime() - item.getStartTime().getTime());
						String dataToHS = DataToTime.dataToHS(timete);
						Log.i("TAG", dataToHS);

						viewHolder.setText(R.id.tv_time, dataToHS);

					}
				});
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 使用万能适配器写ListView 数据

	}

}
