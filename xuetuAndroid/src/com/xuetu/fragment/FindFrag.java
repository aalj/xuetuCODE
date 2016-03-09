package com.xuetu.fragment;

import com.xuetu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FindFrag extends Fragment {

	LinearLayout linearTask;

	LinearLayout linearData;

	LinearLayout linearGetup;

	LinearLayout linearSleep;

	LinearLayout linearCountdown;

	LinearLayout linearSupervise;
	View inflate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.find_frag, null);

		initView();
		return inflate;
	}

	/**
	 * 
	 * initView:(初始化页面需要的控件以及实现的点击事件监听器)<br/>
	 *
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	private void initView() {
		linearTask = (LinearLayout) inflate.findViewById(R.id.linear_task);
		linearData = (LinearLayout) inflate.findViewById(R.id.linear_data);
		linearGetup = (LinearLayout) inflate.findViewById(R.id.linear_getup);
		linearSleep = (LinearLayout) inflate.findViewById(R.id.linear_sleep);
		linearCountdown = (LinearLayout) inflate.findViewById(R.id.linear_countdown);
		linearSupervise = (LinearLayout) inflate.findViewById(R.id.linear_supervise);

		MyOnClickLisener clickLisener = new MyOnClickLisener();
		linearTask.setOnClickListener(clickLisener);
		linearData.setOnClickListener(clickLisener);
		linearGetup.setOnClickListener(clickLisener);
		linearSleep.setOnClickListener(clickLisener);
		linearCountdown.setOnClickListener(clickLisener);
		linearSupervise.setOnClickListener(clickLisener);

	}

	public void onClick(View v) {

	}

	private class MyOnClickLisener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "dianjiashijain ", Toast.LENGTH_SHORT).show();
			switch (v.getId()) {
			case R.id.linear_task://任务

				break;
			case R.id.linear_data://资料

				break;
			case R.id.linear_getup://早睡

				break;
			case R.id.linear_sleep://早起

				break;
			case R.id.linear_countdown://倒计时

				break;
			case R.id.linear_supervise://全天监督

				break;

			default:
				break;
			}
		}

	}

}
