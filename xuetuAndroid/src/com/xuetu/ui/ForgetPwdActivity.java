package com.xuetu.ui;

import com.xuetu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgetPwdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhaohuimima);
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.btn_finish:
			Jump();
			break;
		case R.id.text_back:
			Jump();
			break;

		default:
			break;
		}
	}

	private void Jump() {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
	}
}
