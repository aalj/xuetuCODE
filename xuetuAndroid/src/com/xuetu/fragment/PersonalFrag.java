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

import com.lidroid.xutils.BitmapUtils;
import com.xuetu.PersonalQuestionCollectionActivity;
import com.xuetu.R;
import com.xuetu.entity.Student;
import com.xuetu.ui.CourseActivity;
import com.xuetu.ui.LoginActivity;
import com.xuetu.ui.PaiHangBangActivity;
import com.xuetu.ui.PersonInfomationActivity;
import com.xuetu.ui.TheCollectionOfYouHuiJuanActivity;
import com.xuetu.ui.WoDeShouCangActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;
import com.xuetu.view.TitleBar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	CircleImageView head;

	TextView tvname;
	TextView tvmsg;
	View view;
	SharedPreferences sp = null;
	TitleBar main_title = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.personal_frag, null);
		view_user = (RelativeLayout) view.findViewById(R.id.view_user);
		sp = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);
		txt_pay = (TextView) view.findViewById(R.id.txt_mypoint);
		txt_youhuijuan = (TextView) view.findViewById(R.id.txt_youhuijuan);
		txt_myquestion = (TextView) view.findViewById(R.id.txt_myquestion);
		txt_mylike = (TextView) view.findViewById(R.id.txt_mylike);
		txt_course = (TextView) view.findViewById(R.id.txt_course);
		head = (CircleImageView) view.findViewById(R.id.head);
		txt_paihangbang = (TextView) view.findViewById(R.id.txt_paihangbang);
		// main_title = (TitleBar) view.findViewById(R.id.main_title);
		//
		// main_title.setRightLayoutClickListener(this);
		view_user.setOnClickListener(this);
		txt_pay.setOnClickListener(this);
		txt_youhuijuan.setOnClickListener(this);
		txt_myquestion.setOnClickListener(this);
		txt_mylike.setOnClickListener(this);
		txt_course.setOnClickListener(this);
		txt_paihangbang.setOnClickListener(this);
		tvname = (TextView) view.findViewById(R.id.tvname);
		tvmsg = (TextView) view.findViewById(R.id.tvmsg);

		addView();
		return view;
	}

	public void addView() {
		Student student1 = ((XueTuApplication) (getActivity().getApplication())).getStudent();
		if (student1.getStuId() > 0) {
			boolean bool = sp.getBoolean("SANFANG", false);
			if (bool) {
				setHeadByUrl(view.getContext(), head, student1.getStuIma());

			} else {
				setHeadByUrl(view.getContext(), head, GetHttp.getHttpLC() + student1.getStuIma());

			}
		}
		tvname.setText(student1.getStuName());
		tvmsg.setText(student1.getStuSigner());
	}

	@Override
	public void onResume() {
		addView();
		super.onResume();
	}

	public void setHeadByUrl(Context context, ImageView v, String url) {
		BitmapUtils bm = new BitmapUtils(context);
		bm.display(v, url);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_user:
			Student student = ((XueTuApplication) (getActivity().getApplication())).getStudent();

			if (student.getStuId() <= 0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), LoginActivity.class);
				getActivity().startActivity(intent);
			} else {
				Intent intent1 = new Intent();
				intent1.setClass(getActivity(), PersonInfomationActivity.class);
				getActivity().startActivity(intent1);

			}

			break;
		case R.id.txt_mypoint:

			break;

		case R.id.txt_youhuijuan:// 优惠券管理
			Intent intent3 = new Intent();
			intent3.setClass(getActivity(), TheCollectionOfYouHuiJuanActivity.class);
			getActivity().startActivity(intent3);

			break;

		case R.id.txt_myquestion:
			getActivity().startActivity(new Intent(getActivity(), PersonalQuestionCollectionActivity.class));

			break;

		case R.id.txt_mylike:
			Intent intent4 = new Intent();
			intent4.setClass(getActivity(), WoDeShouCangActivity.class);
			getActivity().startActivity(intent4);

			break;

		case R.id.txt_course:
			Intent intent2 = new Intent();
			intent2.setClass(getActivity(), CourseActivity.class);
			getActivity().startActivity(intent2);

			break;

		case R.id.txt_paihangbang:
			getActivity().startActivity(new Intent(getActivity(), PaiHangBangActivity.class));
			break;

		default:
			break;
		}

	}

}
