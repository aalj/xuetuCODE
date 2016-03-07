/**
 * Person_MainActivity.java
 * com.itheima.login3
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2015-7-22 		����
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
 */

package com.xuetu.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.xuetu.R;
import com.xuetu.utils.LogIn_AutoLogIn;

/**
 * ClassName:Person_MainActivity Function: ��¼�����û��������룻 2����ݷ����Ƕ�ȡӦ�õ�ǰĿ¼�µ�files
 * Reason: �Լ���дһ��
 * 
 * @author ����
 * @version
 * @since Ver 1.1
 * @Date 2015-7-22 ����7:01:18
 * 
 * @see
 */
public class LogIn extends Activity {
	private EditText et_stu_id = null;
	private EditText et_stu_pw = null;
	private CheckBox cb_rp = null;
	private CheckBox cb_auto_log = null;
	private SharedPreferences preferences = null;

	protected void onCreate(Bundle savedInstanceState) {
	       
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lib_log_in);
       viewInit();
		preferences = getSharedPreferences("config", LogIn.this.MODE_PRIVATE);
		String user_name = preferences.getString("stu_id", "");
		String user_psw = preferences.getString("stu_password", "");
		boolean cb_rm = preferences.getBoolean("cb_rp",false);
		Toast.makeText(this, "��ȡ�û�����", 0).show();
		
		
		if(user_name.equals("")||user_psw.equals("")){
			
		}else {
			this.et_stu_id.setText(user_name);
			this.et_stu_pw.setText(user_psw);
			this.cb_rp.setChecked(cb_rm);
		}
		
	}

	public void viewInit() {
		this.et_stu_id = (EditText) findViewById(R.id.stu_id);
		this.et_stu_pw = (EditText) findViewById(R.id.password);
		this.cb_rp = (CheckBox) findViewById(R.id.rm_pw);
		this.cb_auto_log = (CheckBox) findViewById(R.id.auto_login);
	}

	public void stu_login(View v) {
		String username = et_stu_id.getText().toString();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this, "�û�������Ϊ�գ�", 0).show();
		}
		String password = et_stu_pw.getText().toString();
		if (TextUtils.isEmpty(password)) {
			Toast.makeText(this, "�û�������Ϊ�գ�", 0).show();
		}

		Toast.makeText(this, "��¼�û���������", 0).show();
		if (cb_auto_log.isChecked()) {
			new LogIn_AutoLogIn().setAuto_log(true);
		}
		if (cb_rp.isChecked()) {

			// ���� ѧ�ź�����
			Editor editor = preferences.edit();// ��ȡ�༭��
			//�����˺����� 
			//TODO  ��Ҫ��������м���
			editor.putString("stu_id", username);
			editor.putString("stu_password", password);
			//TODO �²��ʾ�����Ƿ��ס����
			editor.putBoolean("cb_rp", true);
			editor.commit();// �ύ�޸�
           Intent intent= new Intent(LogIn.this, PersonalListActivity.class);
           startActivity(intent);
           finish();
		}

	}

	public void login_back(View v) {
		finish();
	}
}
