/**
 * PersonalFrag.java
 * com.librarybooksearch.fragment
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年11月8日 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
 */

package com.xuetu.fragment;

import com.xuetu.R;
import com.xuetu.entity.Student;
import com.xuetu.ui.CourseActivity;
import com.xuetu.ui.LoginActivity;
import com.xuetu.ui.PersonInfomationActivity;
import com.xuetu.ui.XueTuApplication;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ClassName:PersonalFrag Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015年11月8日 下午3:47:17
 * 
 * @see
 */
public class PersonalFrag extends Fragment implements OnClickListener {
	RelativeLayout view_user;
	TextView txt_pay;
	TextView txt_youhuijuan;
	TextView txt_myquestion;
	TextView txt_mylike;
	TextView txt_course;
	TextView txt_paihangbang;
	Student student;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.personal_frag, null);
		view_user = (RelativeLayout) view.findViewById(R.id.view_user);
		txt_pay = (TextView) view.findViewById(R.id.txt_pay);
		txt_youhuijuan = (TextView) view.findViewById(R.id.txt_youhuijuan);
		txt_myquestion = (TextView) view.findViewById(R.id.txt_myquestion);
		txt_mylike = (TextView) view.findViewById(R.id.txt_mylike);
		txt_course = (TextView) view.findViewById(R.id.txt_course);
		txt_paihangbang = (TextView) view.findViewById(R.id.txt_paihangbang);
		view_user.setOnClickListener(this);
		txt_pay.setOnClickListener(this);
		txt_youhuijuan.setOnClickListener(this);
		txt_myquestion.setOnClickListener(this);
		txt_mylike.setOnClickListener(this);
		txt_course.setOnClickListener(this);
		txt_paihangbang.setOnClickListener(this);
		student = ((XueTuApplication) (getActivity().getApplication())).getStudent();
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_user:
			if (student == null || "null".equals(student)) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), LoginActivity.class);
				getActivity().startActivity(intent);
			} else {
				Intent intent = new Intent();
				intent.setClass(getActivity(), PersonInfomationActivity.class);
				getActivity().startActivity(intent);

			}

			break;
		case R.id.txt_pay:

			break;

		case R.id.txt_youhuijuan:

			break;

		case R.id.txt_myquestion:

			break;

		case R.id.txt_mylike:

			break;

		case R.id.txt_course:
			Intent intent2 = new Intent();
			intent2.setClass(getActivity(), CourseActivity.class);
			getActivity().startActivity(intent2);

			break;

		case R.id.txt_paihangbang:

			break;
		default:
			break;
		}

	}

}
