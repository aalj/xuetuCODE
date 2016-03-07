/**
 * PersonalListActivity.java
 * com.librarybooksearch.ui
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015��11��8�� 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
 */

package com.xuetu.ui;

import com.xuetu.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * ClassName:PersonalListActivity Function: TODO ADD FUNCTION Reason: TODO ADD
 * REASON
 * 
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015��11��8�� ����9:18:43
 * 
 * @see
 */
public class PersonalListActivity extends Activity {
	private SharedPreferences sp = null;
	
	private ImageView back_ima = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_list_activity);
		viewInit();
	}

	/**
	 * 
	 * viewInit:(ҳ���ʼ��,�Լ����һЩ������)
	 *
	 * @param �趨�ļ�
	 * @return void DOM����
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	private void viewInit() {
		back_ima = (ImageView) findViewById(R.id.back_ima);

		back_ima.setOnClickListener(new myOnClickListener());
		sp = getSharedPreferences("config",MODE_PRIVATE);
	}
	/**
	 * 
	 * ClassName:myOnClickListener
	 * <p>
	 * Function: ��������ʵ�ַ���
	 * Reason:	 TODO ADD REASON
	 *
	 * @author   Stone_A
	 * @version  PersonalListActivity
	 * @since    Ver 1.1
	 * @Date	 2015	2015��12��5��		����3:39:58
	 *
	 * @see
	 */
	class myOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			finish();
		}

	}

	/**
	 * 
	 * onclick:(����¼�������)
	 * 
	 * @param @param
	 *            v �趨�ļ�
	 * @return void DOM����
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public void onclick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.exit_tv:
			Editor edit = sp.edit();
			edit.putBoolean("cb_rp", false);
			edit.commit();
			finish();
			break;
		case R.id.borrow_list:

			intent = new Intent(PersonalListActivity.this, BorrowListActivity.class);

			startActivity(intent);

			break;

		default:
			break;
		}

	}
}
