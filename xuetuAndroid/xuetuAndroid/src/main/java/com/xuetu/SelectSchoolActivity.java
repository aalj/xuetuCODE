package com.xuetu;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.ui.MainActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectSchoolActivity extends Activity implements OnClickListener {
	Student student = null;
	RelativeLayout xuexiao;
	RelativeLayout zhuanye;
	RelativeLayout nianji;
	TextView text_xuexiao;
	TextView text_zhuanye;
	TextView text_nianji;
	Button btn_sure;
	ProgressDialog progressDialog = null;
	private String SchoolData[] = { "哈尔滨佛学院", "西交利物浦大学", "南京邮电大学通达学院", "福建师范大学", "江苏大学", "常熟理工学院", "南华大学", "南京林业大学",
			"苏州百年职业技术学院", "苏州万年职业技术学院", "高博国际学院", "港大思培", "苏州大学", };
	private String ProfessorData[] = { "教育学院", "经济学院", "法学院", "马克思主义学院", "文学院", "传播学院", "外国语学院", "社会历史学院", "公共管理学院",
			"旅游学院", "体育科学学院", "软件学院", "生命科学学院", "地理科学学院", "光电与信息工程学院" };
	private String GradeData[] = { "大一", "大二", "大三", "大四" };
	boolean flag = false;
	boolean flag1 = false;
	boolean flag2 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		student = ((XueTuApplication) getApplication()).getStudent();
		// if (sp.getBoolean("yes", false)) {
		// Intent intent = new Intent(this, MainActivity.class);
		// startActivity(intent);
		// finish();
		// }
		setContentView(R.layout.activity_select_school);
		xuexiao = (RelativeLayout) findViewById(R.id.xuexiao);
		zhuanye = (RelativeLayout) findViewById(R.id.zhuanye);
		nianji = (RelativeLayout) findViewById(R.id.nianji);
		text_xuexiao = (TextView) findViewById(R.id.text_xuexiao);
		text_zhuanye = (TextView) findViewById(R.id.text_zhuanye);
		text_nianji = (TextView) findViewById(R.id.text_nianji);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		xuexiao.setOnClickListener(this);
		zhuanye.setOnClickListener(this);
		nianji.setOnClickListener(this);
		btn_sure.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.xuexiao:
			shouSchoolDialog();
			flag = true;
			break;
		case R.id.zhuanye:
			shouProfessorDialog();
			flag1 = true;
			break;
		case R.id.nianji:
			shouGradeDialog();
			flag2 = true;
			break;
		case R.id.btn_sure:
			jump();
			finish();
			break;

		default:
			break;
		}

	}

	String schooldata;
	int i = -1;

	public void shouSchoolDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).setTitle("请选择您的学校");
		builder.setCancelable(false);
		builder.setSingleChoiceItems(SchoolData, i, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				schooldata = SchoolData[which];
				i = which;
				Log.i("XXX", i + "");
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i("XXX", i + "");
				if (i != -1) {

					text_xuexiao.setText(schooldata);
					try {
						Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
						field.setAccessible(true);
						field.set(dialog, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// dialog.dismiss();

				} else {
					try {
						Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
						field.setAccessible(true);
						// 将mShowing变量设为false，表示对话框已关闭
						field.set(dialog, false);
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Toast.makeText(getApplicationContext(), "请选择学校", 0).show();
				}
			}
		}).create().show();

	}

	String professordata;
	int j = -1;

	public void shouProfessorDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
		builder.setTitle("请选择您的专业");
		builder.setCancelable(false);
		builder.setSingleChoiceItems(ProfessorData, j, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				professordata = ProfessorData[which];
				j = which;
				Log.i("XXX", j + "");
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i("XXX", j + "");
				if (j != -1) {

					text_zhuanye.setText(professordata);
					try {
						Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
						field.setAccessible(true);
						field.set(dialog, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// dialog.dismiss();
				} else {
					try {
						Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
						field.setAccessible(true);
						// 将mShowing变量设为false，表示对话框已关闭
						field.set(dialog, false);
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Toast.makeText(getApplicationContext(), "请选择专业", 0).show();
				}
			}
		}).create().show();

	}

	String gradedata;
	int k = -1;

	public void shouGradeDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
		builder.setTitle("请选择您的年级");
		builder.setCancelable(false);
		builder.setSingleChoiceItems(GradeData, k, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				gradedata = GradeData[which];
				k = which;
				Log.i("XXX", k + "");
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i("XXX", k + "");
				if (k != -1) {

					text_nianji.setText(gradedata);
					try {
						Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
						field.setAccessible(true);
						field.set(dialog, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// dialog.dismiss();
				} else {
					try {
						Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
						field.setAccessible(true);
						// 将mShowing变量设为false，表示对话框已关闭
						field.set(dialog, false);
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Toast.makeText(getApplicationContext(), "请选择年级", 0).show();
				}
			}
		}).create().show();

	}

	public void jump() {
		if (flag == true && flag1 == true && flag2 == true) {
			// Editor edit = sp.edit();
			// edit.putBoolean("yes", true);
			// edit.commit();
			http();
			showDengdai();
			startActivity(new Intent(this, MainActivity.class));
			finish();

		} else {
			Toast.makeText(this, "请将信息填写完整",0).show();
		}

	}

	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(this, "", "正在生成个人课表");
			progressDialog.setCancelable(true);
			progressDialog.show();
			// new Handler() {
			// @Override
			// public void handleMessage(Message msg) {
			// // 千万别忘了告诉控件加载完毕了哦！
			// //
			// pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			// if (progressDialog != null)
			// progressDialog.dismiss();
			// }
			// }.sendEmptyMessageDelayed(0, 5000);

		} else {

		}
	}

	public void http() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLC() + "ChangeSchoolServlet";
		RequestParams params = new RequestParams();
		params.addBodyParameter("phone", student.getStuPhone());
		params.addBodyParameter("school", (i + 1) + "");

		try {
			params.addBodyParameter("grade", URLEncoder.encode(GradeData[k], "UTF-8"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {


			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new Gson();
				Type type = new TypeToken<Boolean>() {
				}.getType();
				Boolean result_back = gson.fromJson(arg0.result, type);
				Log.i("TAG", "-----" + result_back);
				if (result_back == true) {
					Toast.makeText(getApplicationContext(), "修改成功", 1).show();
					student.setSchool(new School(i + 1, SchoolData[i], "36.1", "263.4"));
					student.setStuUgrade(GradeData[k]);
				} else {
					Toast.makeText(getApplicationContext(), "boom", 1).show();
				}

			}
		});
	}

	@Override
	public void onBackPressed() {

	}

}
