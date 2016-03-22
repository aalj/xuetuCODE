package com.xuetu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;





import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.MyBaseAdapter;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Answer_list extends Activity implements OnClickListener{

	//声明变量
	MyBasesadapter<Answer> adapter = null;
	Question curQues = null;
	List<Answer> list = null;
	Long ans_time = null;
	HttpUtils hutils = new HttpUtils();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final int SELECT_PIC = 11;
	public static final int TAKE_PHOTO = 12;
	public static final int CROP_PHOTO = 13;
	File file = null;
	Bitmap bm = null;
	//声明控件
	private ListView lv_answer = null;
	private Uri imageUri;
	private TextView et_ans_text;
	TextView tv_ans1_text;
	TextView tv_ans1_time;
	TextView tv_ans1_stuName;
	TextView tv_ans1_sub;
	ImageView iv_ans1_first_img;
	ImageView btn_photo;
	ImageView iv_ans1_userImg;
	TextView tv_subAll;
	Button btn_ans;
	View view_title;
	TitleBar titlebar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_answer);
		Intent intent = getIntent();
		curQues= (Question) intent.getSerializableExtra("curQues");
		
		file = new File(Environment.getExternalStorageDirectory(),sdf.format(new Date(System.currentTimeMillis()))+".jpg");
		Log.i("hehe", file.getName());
		imageUri = Uri.fromFile(file);
		//初始化控件
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		lv_answer = (ListView) findViewById(R.id.lv_answer);
		tv_ans1_text =(TextView) findViewById(R.id.tv_ans1_text);
		tv_ans1_time =(TextView) findViewById(R.id.tv_ans1_time);
		tv_ans1_stuName =(TextView) findViewById(R.id.tv_ans1_stuName);
		tv_ans1_sub =(TextView) findViewById(R.id.tv_ans1_sub);
		iv_ans1_first_img =(ImageView) findViewById(R.id.iv_ans1_first_img);
		iv_ans1_userImg =(ImageView) findViewById(R.id.iv_ans1_userImg);
		btn_photo = (ImageView) findViewById(R.id.btn_photo);
		btn_ans = (Button) findViewById(R.id.btn_ans);
		et_ans_text = (TextView) findViewById(R.id.et_ans_text);
		//控件赋值
		tv_ans1_text.setText(curQues.getQuesText());
		tv_ans1_time.setText(sdf.format(curQues.getQuesDate())+"");
		tv_ans1_stuName.setText(curQues.getStudent().getStuName());
		tv_ans1_sub.setText(curQues.getSubject().getName());
		BitmapUtils bitmapUtils = new BitmapUtils(this);
		//设置监听事件
		btn_ans.setOnClickListener(this);
//		titlebar.setLeftLayoutClickListener(this);
		btn_photo.setOnClickListener(this);
		// 加载网络图片
		bitmapUtils.display(iv_ans1_first_img, GetHttp.getHttpLC()+curQues.getQuesIma());
		bitmapUtils.display(iv_ans1_userImg, GetHttp.getHttpLC()+curQues.getStudent().getStuIma());
		
	}
	
	public void answer(View v){
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.left_layout:
			finish();
			break;
		case R.id.btn_ans:
			break;
		case R.id.btn_photo:
			Toast.makeText(this, "ththth", 0);
			AlertDialog.Builder builder = new AlertDialog.Builder(Answer_list.this);
			builder.setItems(new String[]{"拍照","从相册中选择"},new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// which==0,拍照	which==1,从相册中选择
					prop(which);
				}
			}).create().show();
		}
	}
	
	
	//拍照回答
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

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
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
						btn_photo.setImageBitmap(bm);
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
//					bm.compress(CompressFormat.JPEG, 100, new FileOutputStream());
					btn_photo.setImageBitmap(bm);
				}
			}
		}
		
		public void getAllAnswer(){
			String url = GetHttp.getHttpLC()+"GetPageQuestion";
			RequestParams paramsQuesId = new RequestParams();
			paramsQuesId.addBodyParameter("Ques_id",curQues.getQuesID()+"");
			hutils.send(HttpMethod.POST, url, paramsQuesId, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					Gson gson = new Gson();
					Type type = new TypeToken<List<Answer>>(){}.getType();
					list = gson.fromJson(arg0.result, type);
					
					adapter = new MyBasesadapter<Answer>(Answer_list.this, list, R.layout.question_answeritem) {

						@Override
						public void convert(ViewHodle viewHolder, Answer item) {
							// TODO Auto-generated method stub
//							viewHolder.setText(resID, info)
							tv_ans1_text =(TextView) findViewById(R.id.tv_ans1_text);
							tv_ans1_time =(TextView) findViewById(R.id.tv_ans1_time);
							tv_ans1_stuName =(TextView) findViewById(R.id.tv_ans1_stuName);
							tv_ans1_sub =(TextView) findViewById(R.id.tv_ans1_sub);
							iv_ans1_first_img =(ImageView) findViewById(R.id.iv_ans1_first_img);
							iv_ans1_userImg =(ImageView) findViewById(R.id.iv_ans1_userImg);
							viewHolder.setText(R.id.tv_ans1_text, item.getAnsText());
							viewHolder.setText(R.id.tv_ans1_time,sdf.format(new Date(item.getAnsTime().getTime())));
							viewHolder.setText(R.id.tv_ans1_stuName, item.getStudent().getStuName());
							viewHolder.setText(R.id.tv_ans1_sub, item.getQuestion().getSubject().getName());
							viewHolder.SetUrlImage(R.id.iv_ans1_first_img,GetHttp.getHttpLC()+item.getAnsImg());
							viewHolder.SetUrlImage(R.id.iv_ans1_userImg,GetHttp.getHttpLC()+item.getStudent().getStuIma());
							
						}
					};
					lv_answer.setAdapter(adapter);
				}
			});
			
		}
		
		public void submitAnswer(){
			ans_time = System.currentTimeMillis();
			String url = GetHttp.getHttpLC() + "SubmitAnswer";
			RequestParams params = new RequestParams();
			params.addBodyParameter("ques_id",curQues.getQuesID()+"");
			params.addBodyParameter("stu_id",2+"");
			params.addBodyParameter("ans_text",et_ans_text.getText().toString());
			params.addBodyParameter("ans_ima",file.getName());
			params.addBodyParameter(file.getAbsolutePath().replace("/", ""),file);
			params.addBodyParameter("ans_time",String.valueOf(ans_time));
			hutils.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
}
