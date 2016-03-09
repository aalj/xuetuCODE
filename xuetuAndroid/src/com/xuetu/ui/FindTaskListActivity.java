package com.xuetu.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;

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
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年3月9日		下午11:05:19
 *
 * @see
 */
public class FindTaskListActivity extends Activity implements OnItemClickListener  {
	@ViewInject(R.id.activity_find_task_list)
	ListView activityFindTaskList ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_task_list);
		ViewUtils.inject(this);
		activityFindTaskList.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	//使用万能适配器写ListView 数据
		
	
	
	
	}
	
	
	

	
}
