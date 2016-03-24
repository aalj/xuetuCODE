package com.xuetu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
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
	TextView sex;
	TextView text_age;
	TextView text_grade;
	TextView text_school;
	CircleImageView img_head;
	HttpUtils hutils = new HttpUtils();
	private String SexData[] = { "男", "女" };
	

	public static final int SELECT_PIC = 11;
	public static final int TAKE_PHOTO = 12;
	public static final int CROP_PHOTO = 13;

	private Uri imageUri;
	File file = null;
	Bitmap bm = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	private Student student = new Student();
	
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
		img_head = (CircleImageView) findViewById(R.id.img_head);
		//保存照片的file对象和imageUri
		file = new File(Environment.getExternalStorageDirectory(),sdf.format(new Date(System.currentTimeMillis()))+".jpg");
		imageUri = Uri.fromFile(file);

	}
	@Override
	public void onResume() {
		student  = ((XueTuApplication) (getApplication())).getStudent();
		BitmapUtils bt = new BitmapUtils(this);
		bt.display(img_head, GetHttp.getHttpLC()+student.getStuIma());
		super.onResume();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backtoperson:
			finish();
			break;
		case R.id.view_user:
			//换头像
			showPropDialog();
			
			break;

		case R.id.nicheng:
			Intent intent = new Intent();
			intent.setClass(this, EditNameActivity.class);
			startActivityForResult(intent, 2);
			break;
		case R.id.gexingqianming:

			break;
		case R.id.xingbie:
			showChangeSexDialog();
			break;
		case R.id.nianling:
			Intent intentage = new Intent();
			intentage.setClass(this, ChangeAgeActivity.class);
			startActivityForResult(intentage, 3);
			break;
		case R.id.nianji_grade:
			Intent intentgrade = new Intent();
			intentgrade.setClass(this, ChangeAgeActivity.class);
			startActivityForResult(intentgrade, 4);
			break;

		case R.id.xuexiao:
			Intent intentschool = new Intent();
			intentschool.setClass(this, ChangeAgeActivity.class);
			startActivityForResult(intentschool, 5);
			break;
		default:
			break;
		}

	}

	public void setHeadByUrl(Context context,ImageView v,String url){
		BitmapUtils bm = new BitmapUtils(context);
		bm.display(v, url);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 2 && requestCode == 2) {
			String backResult = data.getStringExtra("ed_name");
			text_nicheng.setText(backResult);
		}
		if (requestCode == 3 && requestCode == 3) {
			String ageResult = data.getStringExtra("ed_age");
			text_age.setText(ageResult);
		}
		if (requestCode == 4 && requestCode == 4) {
			String gradeResult = data.getStringExtra("ed_grade");
			text_grade.setText(gradeResult);
		}
		if (requestCode == 5 && requestCode == 5) {
			String schoolResult = data.getStringExtra("ed_school");
			text_grade.setText(schoolResult);
		}

		//调用系统相机
		if(resultCode != RESULT_OK){
			Log.i("MainActivity", "select pic error!");
			return;
		}
		if(requestCode == SELECT_PIC){
			if(imageUri != null){
				InputStream is = null;
				try {
					//读取图片到io流
					is = getContentResolver().openInputStream(imageUri);
					//内存中的图片
					Bitmap bm = BitmapFactory.decodeStream(is);
					img_head.setImageBitmap(bm);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}else if(requestCode == TAKE_PHOTO){
		    Intent intent = new Intent("com.android.camera.action.CROP");
		    intent.setDataAndType(imageUri, "image/*");
		    intent.putExtra("crop", "true");
		    intent.putExtra("aspectX", 1);
		    intent.putExtra("aspectY", 1);
		    intent.putExtra("outputX", 300);
		    intent.putExtra("outputY", 300);
		    intent.putExtra("scale", true);
		    intent.putExtra("return-data", true);
		    startActivityForResult(intent, CROP_PHOTO);//启动裁剪
		}else if(requestCode == CROP_PHOTO){//获取裁剪后的结果
			Bundle bundle = data.getExtras();
			if(bundle != null){
				bm = data.getParcelableExtra("data");//  bundle.putParceable("data",bm);
//				bm.compress(CompressFormat.JPEG, 100, new FileOutputStream());
				img_head.setImageBitmap(bm);
			}
		}
		setphotoByUrl();
		
	}

	private void showChangeSexDialog() {

		Dialog dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).setTitle("请选择您的性别")
				.setSingleChoiceItems(SexData, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dInterface, int whitch) {
						sex.setText(SexData[whitch]);
						Toast.makeText(getApplication(), "您选择了：" + SexData[whitch], 2).show();
					}
				}).setPositiveButton("确定", null).create();
		dialog.show();
	}
	
	private void showPropDialog(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(PersonInfomationActivity.this,AlertDialog.THEME_HOLO_LIGHT);
		builder.setItems(new String[]{"拍照","从相册中选择"},new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// which==0,拍照	which==1,从相册中选择
				prop(which);
			}
		}).create().show();
	}
	
	//拍照
			public void prop(int location){
			
				if(location == 1){//从相册中选择
					Intent intent = new Intent(); 
					intent.setAction(Intent.ACTION_PICK);
					intent.setType("image/*");
					//裁剪
					intent.putExtra("crop", "true");
					//宽高比例
					intent.putExtra("aspectX", 1);
					intent.putExtra("aspectY", 1);
					//定义宽和高
					intent.putExtra("outputX", 300);
					intent.putExtra("outputY", 300);
					//图片是否缩放
					intent.putExtra("scale", true);
					//是否要返回值
					intent.putExtra("return-data", false);
					//把图片存放到imageUri
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					//图片输出格式
					intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
					intent.putExtra("noFaceDetection", true); // no face detection
					startActivityForResult(intent, SELECT_PIC);
				}else if(location==0){//拍照
					// api guide: cemera
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, TAKE_PHOTO);
				}
				
			}
			
			//
			public void setphotoByUrl(){
				
				String url = GetHttp.getHttpLC()+"SaveHead";
				RequestParams params = new RequestParams();
				params.addBodyParameter("stu_id",student.getStuId()+"");
				Log.i("hehe", student.getStuId()+"");
				params.addBodyParameter(file.getAbsolutePath().replace("/", ""),file);
				Log.i("hehe", file+"");
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
						Gson gson = new GsonBuilder()
						.enableComplexMapKeySerialization()
						.setPrettyPrinting()
						.disableHtmlEscaping()
						.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
						Type type = new TypeToken<Student>(){}.getType();
						Student stuNew = gson.fromJson(arg0.result, type);
						((XueTuApplication) (getApplication())).setStudent(stuNew);
						
					}
				});
			}
}
