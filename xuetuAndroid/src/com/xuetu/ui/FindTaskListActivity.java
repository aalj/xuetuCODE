package com.xuetu.ui;

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

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
		viewInit();
		getData();
		activityFindTaskList.setOnItemClickListener(this);

	}

	private void viewInit() {

		activityFindTaskList.setAdapter(new MyBasesadapter<SelfStudyPlan>(this, null, R.layout.find_task_item) {

			@Override
			public void convert(ViewHodle viewHolder, SelfStudyPlan item) {

			}
		});

	}

	private void getData() {
		HttpUtils httpUtils = new HttpUtils();
		String url = "http://10.40.5.10:8080/xuetuWeb/GetSelfStudyPlan";
		RequestParams pram= new RequestParams();
		//TODO 无法获得学生对象的数据
		pram.addBodyParameter("StuID","1");
		httpUtils.send(HttpMethod.POST, url,pram ,new RequestCallBack<String>( ) {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 使用万能适配器写ListView 数据

	}

}
