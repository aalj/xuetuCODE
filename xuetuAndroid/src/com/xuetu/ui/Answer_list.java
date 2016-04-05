package com.xuetu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import android.R.integer;
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
import com.xuetu.adapter.MyQuestionBaseAdapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.KeyboardUtils;
import com.xuetu.view.CircleImageView;
import com.xuetu.view.PullToRefreshView;
import com.xuetu.view.PullToRefreshView.OnFooterRefreshListener;
import com.xuetu.view.PullToRefreshView.OnHeaderRefreshListener;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Answer_list extends Activity implements OnClickListener, OnHeaderRefreshListener, OnFooterRefreshListener{

	//声明变量
//	MyBasesadapter<Answer> adapter = null;
	XueTuApplication xuetu; 
	MyQuestionBaseAdapter<Answer> adapter = null;
	Question curQues = null;
	List<Answer> list = null;
	Answer newAnswer;
	Long ans_time = null;
	String ans_img = null;
	HttpUtils hutils = new HttpUtils();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final int SELECT_PIC = 11;
	public static final int TAKE_PHOTO = 12;
	public static final int CROP_PHOTO = 13;
	File file = null;
	Bitmap bm = null;
	//声明控件
	private ListView lv_answer = null;
	private Uri imageUri;
	private EditText et_ans_text;
	TextView tv_ans1_ques_text;
	TextView tv_ans1_time;
	TextView tv_ans1_stuName;
	TextView tv_ans1_sub;
	TextView tv_ans1_num;
	ImageView iv_ans1_ques_img;
	
	CircleImageView btn_photo;
	ImageView iv_ans1_userImg;
	ImageView iv_collect;
//	TextView tv_ans_title;
	Boolean flagCollect = false;	//收藏标识
	Boolean flagAgree = false;	//点赞标识
	Button btn_ans;
	View view_title;
	TitleBar titlebar;
	String url = null;
	private String urlAgree;
	int stu_id = 0;
	Context context = null;
	HttpUtils hutilsAgree = new HttpUtils();
	RequestParams paramsAgree ;
	//存放点赞按钮tag的集合
	Set<Integer> setTag = new HashSet<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_answer);
		
		xuetu = (XueTuApplication) getApplication();
		stu_id = xuetu.getStudent().getStuId();
		getAgreeAnswer();
		initia();
		getPageAnswerByQues();
	}
	public void initia(){
		BitmapUtils bitmapUtils = new 
				BitmapUtils(Answer_list.this);
		Intent intent = getIntent();
		curQues= (Question) intent.getSerializableExtra("curQues");
		file = new File(Environment.getExternalStorageDirectory(),sdf.format(new Date(System.currentTimeMillis()))+".jpg");
		imageUri = Uri.fromFile(file);
		//初始化控件
		
		titlebar = (TitleBar) findViewById(R.id.title_my);
		btn_photo = (CircleImageView) findViewById(R.id.btn_photo);
//		btn_photo.setScaleType(ScaleType.CENTER_INSIDE);
		btn_ans = (Button) findViewById(R.id.btn_ans);
		et_ans_text = (EditText) findViewById(R.id.et_ans_text);
		lv_answer = (ListView) findViewById(R.id.lv_answer);
		tv_ans1_sub = (TextView) findViewById(R.id.tv_ans1_sub);
		iv_collect = (ImageView) findViewById(R.id.iv_collect);
		iv_ans1_userImg = (ImageView) findViewById(R.id.iv_ans1_userImg);
		//设置监听事件
		btn_ans.setOnClickListener(this);
		iv_collect.setOnClickListener(this);
//		titlebar.setLeftLayoutClickListener(this);
		btn_photo.setOnClickListener(this);
		tv_ans1_num = (TextView) findViewById(R.id.tv_ans1_num);
		tv_ans1_stuName = (TextView) findViewById(R.id.tv_ans1_stuName);
		iv_ans1_ques_img = (ImageView) findViewById(R.id.iv_ans1_ques_img);
		tv_ans1_time = (TextView) findViewById(R.id.tv_ans1_time);
		tv_ans1_ques_text = (TextView) findViewById(R.id.tv_ans1_ques_text);
		tv_ans1_ques_text.setText(curQues.getQuesText());

		tv_ans1_num.setText(curQues.getAns_num()+"");
		Drawable drawable= getResources().getDrawable(R.drawable.ic_ans);
		/// 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, 50, 50);
		tv_ans1_num.setCompoundDrawables(drawable,null,null,null);
		tv_ans1_sub.setText(curQues.getSubject().getName());
		tv_ans1_time.setText(sdf2.format(new Date(curQues.getQuesDate().getTime())));
		isSave();
		bitmapUtils.display(iv_ans1_ques_img, GetHttp.getHttpLC()+curQues.getQuesIma());
		bitmapUtils.display(iv_ans1_userImg, GetHttp.getHttpLC()+curQues.getStudent().getStuIma());
		tv_ans1_stuName.setText(curQues.getStudent().getStuName());
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutVisibility(View.INVISIBLE);
	}
	public void isSave(){
		url = GetHttp.getHttpLC()+"IsSaveQuestion";
		paramsAgree = new RequestParams();
		paramsAgree.addBodyParameter("ques_id",curQues.getQuesID()+"");
		paramsAgree.addBodyParameter("stu_id",stu_id+"");
		hutils.send(HttpMethod.POST, url, paramsAgree, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(arg0.result.equals("1")){
					flagCollect = true;
					iv_collect.setImageResource(R.drawable.ic_saved);
				}else{
					flagCollect = false;
					iv_collect.setImageResource(R.drawable.ic_save);
				}
			}
		});
	}
	//获得用户对象
	@Override
	public void onResume() {
		stu_id = ((XueTuApplication)getApplication()).getStudent().getStuId();
		getAgreeAnswer();
		super.onResume();
	}
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 1:
				list =(List<Answer>) msg.obj;break;	
			case 2:
				list = (List<Answer>) msg.obj;break;	
			}
			if(adapter !=null){
				adapter.notifyDataSetChanged();
			}else{
			setMyAapter(list);
			lv_answer.setAdapter(adapter);
			KeyboardUtils.closeKeybord(et_ans_text,Answer_list.this);
			}
			super.handleMessage(msg);
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		RequestParams params;
		switch(v.getId()){
		case R.id.btn_ans:
			InputMethodManager imm = (InputMethodManager)  
	         getSystemService(Context.INPUT_METHOD_SERVICE);  
	         imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  
			if(stu_id<=0){
				Log.i("hehe", "mei denglu");
				Toast.makeText(Answer_list.this, "请先登陆哟！", 0).show();
			}else{
				if(et_ans_text.getText().toString().equals(null)||et_ans_text.getText().toString().equals("")){
					Log.i("hehe", "no text");
					Toast.makeText(this, "混水也要打几个字吧！！", 0).show();
				}else{
					submitAnswer();
					et_ans_text.setText("");
				}
			}
			break;
		case R.id.btn_photo:
			AlertDialog.Builder builder = new AlertDialog.Builder(Answer_list.this);
			builder.setItems(new String[]{"拍照","从相册中选择"},new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// which==0,拍照	which==1,从相册中选择
					prop(which);
				}
			}).create().show();
			break;
		case R.id.left_layout:
			finish();
			break;
		case R.id.iv_collect:
			params = new RequestParams();
			//收藏记录写入数据库，判断有没被收藏
			if(flagCollect==true)
			url=GetHttp.getHttpLC()+"CollectCancelQuestion" ;
			else{
				url=GetHttp.getHttpLC()+"CollectQuestion" ;
				params.addBodyParameter("ques_time_collect",System.currentTimeMillis()+"");
			}
//			Log.i("hehe", questions.get((Integer)v1.getTag()).getQuesID()+"");
			params.addBodyParameter("stu_id",stu_id+"");
			params.addBodyParameter("ques_id",curQues.getQuesID()+"");
			hutils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					if(url.equals(GetHttp.getHttpLC()+"CollectQuestion"))
						Toast.makeText(Answer_list.this, "收藏失败", 0).show();
					else
						Toast.makeText(Answer_list.this, "取消收藏失败", 0).show();

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					if(url.equals(GetHttp.getHttpLC()+"CollectQuestion")){
						Toast.makeText(Answer_list.this, "收藏成功", 0).show();
						iv_collect.setImageResource(R.drawable.ic_saved);
						flagCollect = true;
					}else{
						Toast.makeText(Answer_list.this, "取消收藏成功", 0).show();
						iv_collect.setImageResource(R.drawable.ic_save);
						flagCollect = false;
					}
				}
			});
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
		
		public void getPageAnswerByQues(){
			url = GetHttp.getHttpLC()+"GetPageAnswer";
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
					Gson gson = new GsonBuilder()
					.enableComplexMapKeySerialization()
					.setPrettyPrinting()
					.disableHtmlEscaping()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					Type type = new TypeToken<List<Answer>>(){}.getType();
					list = gson.fromJson(arg0.result, type);
					 Message msg = Message.obtain();
					 msg.what=1;
					 msg.obj=list;
					handler.sendMessage(msg);
					;
				}
			});
			
		}
		
		public void submitAnswer(){
			RequestParams paramsSub = new RequestParams();
			ans_time = System.currentTimeMillis();
			if(file.exists()){
				url = GetHttp.getHttpLC()+"SubmitAnswer";
				paramsSub.addBodyParameter(file.getAbsolutePath().replace("/", ""),file);
				}
				else{
					url = GetHttp.getHttpLC()+"SubmitAnswerWithoutImg";
				}
			paramsSub.addBodyParameter("quesId",curQues.getQuesID()+"");
			paramsSub.addBodyParameter("stu_id",stu_id+"");
			paramsSub.addBodyParameter("ans_text",et_ans_text.getText().toString());
			paramsSub.addBodyParameter("ans_time",String.valueOf(ans_time));
			hutils.send(HttpMethod.POST, url, paramsSub,new RequestCallBack<String>() {

				

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(getApplicationContext(), "提交失败", 1).show();
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Toast.makeText(getApplicationContext(), "提交成功", 1).show();
					Gson gson = new GsonBuilder()
					.enableComplexMapKeySerialization()
					.setPrettyPrinting()
					.disableHtmlEscaping()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					Type type = new TypeToken<Answer>(){}.getType();
					newAnswer = gson.fromJson(arg0.result, type);
					list.add(newAnswer);
					 Message msg = Message.obtain();
					 msg.what=2;
					 msg.obj=list;
					handler.sendMessage(msg );
				}
			});
		}
		//标记适配器是否初始化
		boolean temp = false;
		public void setMyAapter(List<Answer> list){
			setTag = xuetu.getSet();
			Log.i("hehe", setTag.size()+"---------activityApplication");
			adapter = new MyQuestionBaseAdapter<Answer>(this,list,R.layout.question_answeritem) {
				
				@Override
				public void convert(final ViewHodle viewHolder, final Answer item,int position) {
					
					ImageView iv = (ImageView)viewHolder.getView(R.id.iv_like);
					final TextView tv = (TextView)viewHolder.getView(R.id.tv_like);
					viewHolder.setText(R.id.tv_ans_stuName, item.getStudent().getStuName());
					viewHolder.setText(R.id.tv_ans_text, item.getAnsText());
					viewHolder.setText(R.id.tv_ans_time, sdf2.format(new Date(item.getAnsTime().getTime())));
					viewHolder.SetUrlImage(R.id.iv_ans_img, GetHttp.getHttpLC()+item.getAnsImg());
					viewHolder.SetUrlImage(R.id.iv_ans_userImg, GetHttp.getHttpLC()+item.getStudent().getStuIma());
					viewHolder.setText(R.id.tv_like, item.getAgrNum()+"");
					if(setTag.contains(item.getAnsID())){
						viewHolder.setIma(R.id.iv_like, R.drawable.ic_liked);
						tv.setTextColor(0xffF48700);
					}else{
						viewHolder.setIma(R.id.iv_like, R.drawable.ic_like);
						tv.setTextColor(0xffABABAB);
					}
					iv.setTag(item.getAnsID());
					iv.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v1) {
							// TODO Auto-generated method stub	answer stu_id   answer_id
							if(item.getStudent().getStuId()==stu_id)
								Toast.makeText(getApplicationContext(), "停止赞自己的愚蠢行为吧人类！", 0).show();
							else{
							paramsAgree = new RequestParams();
							ImageView v = (ImageView) v1;
							if(setTag.contains(v.getTag())){
								setTag.remove((Integer)v1.getTag());
								//将取消赞的操作保存到数据库
								urlAgree = GetHttp.getHttpLC() + "DisAgreeAnswer";
								paramsAgree.addBodyParameter("ans_id",item.getAnsID()+"");
								paramsAgree.addBodyParameter("stu_id",stu_id+"");
								hutilsAgree.send(HttpMethod.POST, urlAgree,paramsAgree, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(Answer_list.this, "取消点赞失败", 0).show();
									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {
										// TODO Auto-generated method stub
										Toast.makeText(Answer_list.this, "取消点赞成功", 0).show();
										viewHolder.setIma(R.id.iv_like, R.drawable.ic_like);
										viewHolder.setText(R.id.tv_like, (Integer.parseInt(tv.getText()+"")-1)+"");
										tv.setTextColor(0xffABABAB);
//										viewHolder.setTextColor(R.id.tv_like, R.string.likeText);
									}
								});
							}else{
								setTag.add((Integer)v1.getTag());
//								Drawable drawable= Answer_list.this.getResources().getDrawable(R.drawable.ic_liked);
//								drawable.setBounds(0, 0,40, 40);
//								v.setCompoundDrawables(drawable,null,null,null);
//								v.setText((Integer.parseInt(v.getText()+"")+1)+"");
//								v.setTextColor(Answer_list.this.getResources().getColor(R.color.likeText));
								//将点赞的操作保存到数据库
								urlAgree = GetHttp.getHttpLC() + "AngreeAnswer";
//								Log.i("hehe", stu_id+"---zan");
								
								paramsAgree.addBodyParameter("ans_id",item.getAnsID()+"");
								paramsAgree.addBodyParameter("stu_id",stu_id+"");
								paramsAgree.addBodyParameter("agr_date",System.currentTimeMillis()+"");
								hutilsAgree.send(HttpMethod.POST, urlAgree,paramsAgree, new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(Answer_list.this, "点赞失败", 0).show();
									}

									@Override
									public void onSuccess(
											ResponseInfo<String> arg0) {
										// TODO Auto-generated method stub
										Toast.makeText(Answer_list.this, "点赞成功", 0).show();
										viewHolder.setIma(R.id.iv_like, R.drawable.ic_liked);
										viewHolder.setText(R.id.tv_like, (Integer.parseInt(tv.getText()+"")+1)+"");
										tv.setTextColor(0xffF48700);
//										viewHolder.setTextColor(R.id.tv_like, R.string.likedText);
									}
								});
							}
						}
						}});
				}
			};
			
			temp = true;
		}
		public void getAgreeAnswer(){
			urlAgree = GetHttp.getHttpLC()+"GetAgreeAnswer";
			paramsAgree = new RequestParams();
			paramsAgree.addBodyParameter("stu_id",stu_id+"");
			hutilsAgree.send(HttpMethod.POST, urlAgree, paramsAgree, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// 得到该用户的对答案的点赞集合
					Gson gson = new GsonBuilder()
					.enableComplexMapKeySerialization()
					.setPrettyPrinting()
					.disableHtmlEscaping()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					Type type = new TypeToken<Set<Integer>>(){}.getType();
					Set<Integer> sAgree = gson.fromJson(arg0.result, type);
					xuetu.setSet(sAgree);
				}
			});
		}
		@Override
		public void onHeaderRefresh(PullToRefreshView view) {
			
		}

		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			
		}
}
