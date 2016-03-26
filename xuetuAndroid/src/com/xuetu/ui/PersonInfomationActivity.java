package com.xuetu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonInfomationActivity extends Activity implements OnClickListener {
	TitleBar titlebar;
	RelativeLayout view_user;
	RelativeLayout nicheng;
	RelativeLayout gexingqianming;
	RelativeLayout xingbie;
	RelativeLayout nianling;
	RelativeLayout nianji_grade;
	RelativeLayout xuexiao;
	TextView text_nicheng;
	TextView study_gexingqianming;
	TextView sex;
	TextView text_age;
	TextView text_grade;
	TextView text_school;
	CircleImageView img_head;
	HttpUtils hutils = new HttpUtils();
	private String SexData[] = { "男", "女" };
	int stuId;

	public static final int SELECT_PIC = 11;
	public static final int TAKE_PHOTO = 12;
	public static final int CROP_PHOTO = 13;

	private Uri imageUri;
	File file = null;
	Bitmap bm = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	private Student student ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_infomation);
		titlebar = (TitleBar) findViewById(R.id.backtoperson);
		titlebar.setLeftLayoutClickListener(this);
		view_user = (RelativeLayout) findViewById(R.id.view_user);
		view_user.setOnClickListener(this);
		nicheng = (RelativeLayout) findViewById(R.id.nicheng);
		nicheng.setOnClickListener(this);
		gexingqianming = (RelativeLayout) findViewById(R.id.gexingqianming);
		study_gexingqianming = (TextView) findViewById(R.id.study_gexingqianming);
		gexingqianming.setOnClickListener(this);
		xingbie = (RelativeLayout) findViewById(R.id.xingbie);
		xingbie.setOnClickListener(this);
		nianling = (RelativeLayout) findViewById(R.id.nianling);
		nianling.setOnClickListener(this);
		nianji_grade = (RelativeLayout) findViewById(R.id.nianji_grade);
		nianji_grade.setOnClickListener(this);
		xuexiao = (RelativeLayout) findViewById(R.id.xuexiao);
		xuexiao.setOnClickListener(this);
		text_nicheng = (TextView) findViewById(R.id.text_nicheng);
		text_age = (TextView) findViewById(R.id.text_age);
		text_grade = (TextView) findViewById(R.id.text_grade);
		text_school = (TextView) findViewById(R.id.text_school);
		sex = (TextView) findViewById(R.id.sex);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
		student = ((XueTuApplication) getApplication()).getStudent();
		stuId = student.getStuId();
		loadView();
		img_head = (CircleImageView) findViewById(R.id.img_head);
		// 保存照片的file对象和imageUri
		file = new File(Environment.getExternalStorageDirectory(),
				sdf.format(new Date(System.currentTimeMillis())) + ".jpg");
		imageUri = Uri.fromFile(file);

	}

	public void loadView() {
		Student student1 = ((XueTuApplication) (getApplication())).getStudent();
		BitmapUtils bt = new BitmapUtils(this);
		bt.display(img_head, GetHttp.getHttpLC() + student1.getStuIma());
		text_nicheng.setText(student1.getStuName());
		study_gexingqianming.setText(student1.getStuSigner());
		sex.setText(student1.getStuSex());
		text_age.setText(student1.getStuAge() + "");
		text_grade.setText(student1.getStuUgrade());

	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what==123){
			Bitmap bb=	(Bitmap) msg.obj;
			img_head.setImageBitmap(bb);
			}
			super.handleMessage(msg);
		}
	};
	
	
	
	

	@Override
	public void onResume() {
//		student = ((XueTuApplication) (getApplication())).getStudent();
//		BitmapUtils bt = new BitmapUtils(this);
//		bt.display(img_head, GetHttp.getHttpLC() + student.getStuIma());
		loadView();
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			finish();
			break;
		case R.id.backtoperson:
			finish();
			break;
		case R.id.view_user:
			// 换头像
			showPropDialog();

			break;

		case R.id.nicheng:
			Intent intent = new Intent();
			intent.setClass(this, EditNameActivity.class);
			intent.putExtra("key2", 2);
			startActivityForResult(intent, 2);
			break;
		case R.id.gexingqianming:
			Intent intent3 = new Intent();
			intent3.setClass(this, EditSignerActivity.class);
			intent3.putExtra("key2", 6);
			startActivityForResult(intent3, 6);

			break;
		case R.id.xingbie:
			showChangeSexDialog();
			break;
		case R.id.nianling:
			Intent intentage = new Intent();
			intentage.setClass(this, ChangeAgeActivity.class);
			intentage.putExtra("key2", 3);
			startActivityForResult(intentage, 3);
			break;
		case R.id.nianji_grade:
			Intent intentgrade = new Intent();
			intentgrade.setClass(this, ChangeGradeActivity.class);
			intentgrade.putExtra("key2", 4);
			startActivityForResult(intentgrade, 4);
			break;

		case R.id.xuexiao:
			// Intent intentschool = new Intent();
			// intentschool.setClass(this, ChangeAgeActivity.class);
			// startActivityForResult(intentschool, 5);
			break;
		default:
			break;
		}

	}

	public void setHeadByUrl(Context context, ImageView v, String url) {
		BitmapUtils bm = new BitmapUtils(context);
		bm.display(v, url);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Message message = Message.obtain();
		message.what=123;
		if (resultCode == 2 && requestCode == 2) {
			String backResult = data.getStringExtra("ed_name");
			if (backResult != null)
				text_nicheng.setText(backResult);
		}
		if (resultCode == 3 && requestCode == 3) {
			String ageResult = data.getStringExtra("edit_age");
			if (ageResult != null)
				text_age.setText(ageResult);
		}
		if (resultCode == 4 && requestCode == 4) {
			String gradeResult = data.getStringExtra("ed_grade");
			if (gradeResult != null)
				text_grade.setText(gradeResult);
		}
		if (resultCode == 5 && requestCode == 5) {
			String schoolResult = data.getStringExtra("ed_name");
			if (schoolResult != null)

				text_school.setText(schoolResult);
		}
		if (resultCode == 6 && requestCode == 6) {
			String schoolResult = data.getStringExtra("edit_qianming");
			if (schoolResult != null)

				study_gexingqianming.setText(schoolResult);
		}

		// 调用系统相机
		if (resultCode != RESULT_OK) {
			Log.i("MainActivity", "select pic error!");
			
			return;
		}
		if (requestCode == SELECT_PIC) {
			Log.i("SYS", "开始搞图了"+bm);
			if (imageUri != null) {
				InputStream is ;
				try {
					// 读取图片到io流
					is = getContentResolver().openInputStream(imageUri);
					// 内存中的图片
					Bitmap bm = BitmapFactory.decodeStream(is);
					//TODO   FADFAS 
					
					message.obj=bm;
					
					handler.sendMessage(message);
//					img_head.setImageBitmap(bm);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else if (requestCode == TAKE_PHOTO) {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(imageUri, "image/*");
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 300);
			intent.putExtra("outputY", 300);
			intent.putExtra("scale", true);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, CROP_PHOTO);// 启动裁剪
		} else if (requestCode == CROP_PHOTO) {// 获取裁剪后的结果
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				bm = data.getParcelableExtra("data");// bundle.putParceable("data",bm);
				// bm.compress(CompressFormat.JPEG, 100, new
				// FileOutputStream());
				Log.i("SYS", "这边可是很腻害的图片哦"+bm);
				message.obj=bm;
				
				handler.sendMessage(message);
//				img_head.setImageBitmap(bm);
			}
		}
		setphotoByUrl();

	}
	
	
	

	private void showChangeSexDialog() {

		Dialog dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).setTitle("请选择您的性别")
				.setSingleChoiceItems(SexData, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dInterface, int whitch) {
						String sexdata = SexData[whitch];
						sex.setText(sexdata);
						Toast.makeText(getApplication(), "您选择了：" + sexdata, 2).show();
						/**
						 * 传值
						 */
						HttpUtils httpUtils = new HttpUtils();
						String url = GetHttp.getHttpBCL() + "ChangeSexServlet";
						RequestParams params = new RequestParams();
						try {
							params.addBodyParameter("id", URLEncoder.encode(stuId + "", "utf-8"));
							params.addBodyParameter("changesex", URLEncoder.encode(sexdata.toString(), "utf-8"));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0, String arg1) {
								// TODO Auto-generated method stub

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
								} else {
									Toast.makeText(getApplicationContext(), "boom", 1).show();
								}

							}
						});

					}
				}).setPositiveButton("确定", null).create();
		dialog.show();
	}

	private void showPropDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(PersonInfomationActivity.this,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setItems(new String[] { "拍照", "从相册中选择" }, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// which==0,拍照 which==1,从相册中选择
				prop(which);
			}
		}).create().show();
	}

	// 拍照
	public void prop(int location) {

		if (location == 1) {// 从相册中选择
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_PICK);
			intent.setType("image/*");
			// 裁剪
			intent.putExtra("crop", "true");
			// 宽高比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// 定义宽和高
			intent.putExtra("outputX", 300);
			intent.putExtra("outputY", 300);
			// 图片是否缩放
			intent.putExtra("scale", true);
			// 是否要返回值
			intent.putExtra("return-data", false);
			// 把图片存放到imageUri
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			// 图片输出格式
			intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
			intent.putExtra("noFaceDetection", true); // no face detection
			startActivityForResult(intent, SELECT_PIC);
		} else if (location == 0) {// 拍照
			// api guide: cemera
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(intent, TAKE_PHOTO);
		}

	}

	//
	public void setphotoByUrl() {

		String url = GetHttp.getHttpLC() + "SaveHead";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stu_id", student.getStuId() + "");
		Log.i("hehe", student.getStuId() + "");
		params.addBodyParameter(file.getAbsolutePath().replace("/", ""), file);
		Log.i("hehe", file + "");
		hutils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "buxing");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				Log.i("hehe", "keyi");
				Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
						.disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<Student>() {
				}.getType();
				Student stuNew = gson.fromJson(arg0.result, type);
				((XueTuApplication) (getApplication())).setStudent(stuNew);

			}
		});
	}
}
