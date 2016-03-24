package com.xuetu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





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
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.utils.GetHttp;
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Answer_list extends Activity implements OnClickListener{

	//声明变量
//	MyBasesadapter<Answer> adapter = null;
	MyListViewAdatper adapter = null;
	Question curQues = null;
	List<Answer> list = null;
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
	private TextView et_ans_text;
	TextView tv_ans1_text;
	TextView tv_ans1_time;
	TextView tv_ans1_stuName;
	TextView tv_ans1_sub;
	ImageView iv_ans1_first_img;
	ImageView btn_photo;
	ImageView iv_ans1_userImg;
//	TextView tv_ans_title;
	Button btn_ans;
	View view_title;
	TitleBar titlebar;
	RelativeLayout left_ans_layout;
	int stu_id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//获取当前用户对象
	
		
		setContentView(R.layout.question_answer);
		Intent intent = getIntent();
		curQues= (Question) intent.getSerializableExtra("curQues");
		
		file = new File(Environment.getExternalStorageDirectory(),sdf.format(new Date(System.currentTimeMillis()))+".jpg");
//		Log.i("hehe", file.getName());
		imageUri = Uri.fromFile(file);
		//初始化控件
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		lv_answer = (ListView) findViewById(R.id.lv_answer);
//		tv_ans1_text =(TextView) findViewById(R.id.tv_ans1_text);
//		tv_ans1_time =(TextView) findViewById(R.id.tv_ans1_time);
//		tv_ans1_stuName =(TextView) findViewById(R.id.tv_ans1_stuName);
//		tv_ans1_sub =(TextView) findViewById(R.id.tv_ans1_sub);
//		iv_ans1_first_img =(ImageView) findViewById(R.id.iv_ans1_first_img);
//		iv_ans1_userImg =(ImageView) findViewById(R.id.iv_ans1_userImg);
		btn_photo = (ImageView) findViewById(R.id.btn_photo);
		btn_ans = (Button) findViewById(R.id.btn_ans);
		et_ans_text = (TextView) findViewById(R.id.et_ans_text);
		left_ans_layout = (RelativeLayout) findViewById(R.id.left_ans_layout);
		//控件赋值
//		tv_ans1_text.setText(curQues.getQuesText());
//		tv_ans1_time.setText(sdf2.format(curQues.getQuesDate())+"");
//		tv_ans1_stuName.setText(curQues.getStudent().getStuName());
//		tv_ans1_sub.setText(curQues.getSubject().getName());
//		BitmapUtils bitmapUtils = new BitmapUtils(this);
		//设置监听事件
		btn_ans.setOnClickListener(this);
		left_ans_layout.setOnClickListener(this);
//		titlebar.setLeftLayoutClickListener(this);
		btn_photo.setOnClickListener(this);
		// 加载网络图片
//		bitmapUtils.display(iv_ans1_first_img, GetHttp.getHttpLC()+curQues.getQuesIma());
//		bitmapUtils.display(iv_ans1_userImg, GetHttp.getHttpLC()+curQues.getStudent().getStuIma());
		getPageAnswerByQues();
	}
	@Override
	public void onResume() {
		stu_id = ((XueTuApplication)getApplication()).getStudent().getStuId();
		
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.left_ans_layout:
			finish();
			break;
		case R.id.btn_ans:
			if(stu_id>0){
			submitAnswer();
			finish();
			}
			else
				Toast.makeText(Answer_list.this, "请先登陆哟！", 0).show();
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
			String url = GetHttp.getHttpLC()+"GetPageAnswer";
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
					
//					Log.i("hehe", "------>"+list.size());
//					Log.i("hehe", "------>"+list.get(0).toString());
//					
//					Log.i("hehe", "------>"+list.get(1).toString());
//					Log.i("hehe", "------>"+sdf2.format(new Date(list.get(0).getAnsTime().getTime())));
					/*adapter = new MyBasesadapter<Answer>(Answer_list.this, list, R.layout.question_answeritem) {
						@Override
						public void convert(ViewHodle viewHolder, Answer item) {
							// TODO Auto-generated method stub
//							viewHolder.setText(resID, info)tv_stuName
//							tv_ans1_text =(TextView) findViewById(R.id.tv_ans1_text);
//							tv_ans1_time =(TextView) findViewById(R.id.tv_ans1_time);
//							tv_ans1_stuName =(TextView) findViewById(R.id.tv_ans1_stuName);
//							tv_ans1_sub =(TextView) findViewById(R.id.tv_ans1_sub);
//							iv_ans1_first_img =(ImageView) findViewById(R.id.iv_ans1_first_img);
//							iv_ans1_userImg =(ImageView) findViewById(R.id.iv_ans1_userImg);
							viewHolder.setText(R.id.tv_ans_ques_text, item.getAnsText());

//							Log.i("hehe", "------>"+sdf2.format(new Date(item.getAnsTime().getTime())));
							viewHolder.setText(R.id.tv_ans_time,sdf2.format(new Date(item.getAnsTime().getTime())));
							viewHolder.setText(R.id.tv_ans_stuName, item.getStudent().getStuName());
							http://10.201.1.13:8080/xuetuWeb/+
							viewHolder.SetUrlImage(R.id.iv_ans_img,GetHttp.getHttpLC()+item.getAnsImg());
							viewHolder.SetUrlImage(R.id.iv_ans_userImg,GetHttp.getHttpLC()+item.getStudent().getStuIma());
							
						}
					};
					lv_answer.setAdapter(adapter);*/
					adapter = new MyListViewAdatper(list);
					lv_answer.setAdapter(adapter);
				}
			});
			
		}
		
		public void submitAnswer(){
			ans_time = System.currentTimeMillis();
			ans_img = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ans_time+".jpg";
			String url = GetHttp.getHttpLC() + "SubmitAnswer";
			
			RequestParams params = new RequestParams();
			params.addBodyParameter("ques_id",curQues.getQuesID()+"");
			params.addBodyParameter("stu_id",stu_id+"");
			params.addBodyParameter("ans_text",et_ans_text.getText().toString());
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
		
		private class MyListViewAdatper extends BaseAdapter {
			BitmapUtils bitmapUtils = new BitmapUtils(Answer_list.this);
			LayoutInflater m_inflater = null;
			private List<Answer> answers = new ArrayList<Answer>();

			public MyListViewAdatper(List<Answer> answers) {
				m_inflater = LayoutInflater.from(Answer_list.this);
				this.answers = (ArrayList<Answer>) answers;
			}

			@Override
			public int getItemViewType(int position) {
				// TODO Auto-generated method stub
				return position > 0 ? 0 : 1;
			}

			@Override
			public int getViewTypeCount() {
				// TODO Auto-generated method stub
				return 2;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return answers.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return answers.get(position);
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View view = null;
				if (getItemViewType(position) == 0)//不是第一条
				{
					ViewHolder holder = null;

					if (convertView == null) {

						view = m_inflater.inflate(R.layout.question_answeritem, null);
						holder = new ViewHolder();
//						viewHolder.setText(R.id.tv_ans_time,sdf2.format(new Date(item.getAnsTime().getTime())));
//						viewHolder.setText(R.id.tv_ans_stuName, item.getStudent().getStuName());
//						viewHolder.SetUrlImage(R.id.iv_ans_img,GetHttp.getHttpLC()+item.getAnsImg());
//						viewHolder.SetUrlImage(R.id.iv_ans_userImg,GetHttp.getHttpLC()+item.getStudent().getStuIma());
						holder.tv_ans_stuName = (TextView) view.findViewById(R.id.tv_ans_stuName);
						holder.tv_ans_time = (TextView) view.findViewById(R.id.tv_ans_time);
						holder.tv_ans_text = (TextView) view.findViewById(R.id.tv_ans_text);			
						holder.iv_ans_img = (ImageView) view.findViewById(R.id.iv_ans_img);
						holder.iv_ans_userImg = (ImageView) view.findViewById(R.id.iv_ans_userImg);
						
						view.setTag(holder);
					} else {
						view = convertView;
						holder = (ViewHolder) view.getTag();
					}

					holder.tv_ans_stuName.setText(answers.get(position).getStudent().getStuName());
					
					holder.tv_ans_text.setText(answers.get(position).getAnsText());

					holder.tv_ans_time.setText(sdf2.format(new Date(answers.get(position).getAnsTime().getTime())));

					bitmapUtils.display(holder.iv_ans_img,
							GetHttp.getHttpLC() + answers.get(position).getAnsImg());
					bitmapUtils.display(holder.iv_ans_userImg,
							GetHttp.getHttpLC() + answers.get(position).getStudent().getStuIma());
				} else if (getItemViewType(position) == 1)// 如果是顶部viewpager
				{
					FirstViewHolder holder = null;
					if (convertView == null) {

						
						view = m_inflater.inflate(R.layout.question_answer_firstitem, null);
						holder = new FirstViewHolder();

						holder.tv_ans1_ques_text = (TextView) view.findViewById(R.id.tv_ans1_ques_text);
						holder.tv_ans1_time = (TextView) view.findViewById(R.id.tv_ans1_time);
						holder.tv_ans1_stuName = (TextView) view.findViewById(R.id.tv_ans1_stuName);
						holder.tv_ans1_sub = (TextView) view.findViewById(R.id.tv_ans1_sub);
//						holder.tv_ans_num = (TextView) view.findViewById(R.id.tv_ans_num);
						holder.iv_ans1_first_img = (ImageView) view.findViewById(R.id.iv_ans1_first_img);
						holder.iv_ans1_userImg = (ImageView) view.findViewById(R.id.iv_ans1_userImg);
						view.setTag(holder);
					} else {
						view = convertView;
						holder = (FirstViewHolder) view.getTag();
					}
					
					holder.tv_ans1_ques_text.setText(curQues.getQuesText());
					holder.tv_ans1_time.setText(sdf2.format(new Date(curQues.getQuesDate().getTime())));
					holder.tv_ans1_stuName.setText(curQues.getStudent().getStuName());
					holder.tv_ans1_sub.setText(curQues.getSubject().getName());
//					holder.tv_ans_num.setText(getCount());
					bitmapUtils.display(holder.iv_ans1_first_img,
							GetHttp.getHttpLC() + curQues.getQuesIma());
					bitmapUtils.display(holder.iv_ans1_userImg,
							GetHttp.getHttpLC() + curQues.getStudent().getStuIma());
				}

				return view;
			}

		}
		public static class FirstViewHolder{
			public TextView tv_ans1_ques_text;
			public TextView tv_ans1_time;
			public TextView tv_ans1_stuName;
			public TextView tv_ans1_sub;
			public TextView tv_ans_num;
			public ImageView iv_ans1_first_img;
			public ImageView iv_ans1_userImg;
			
//			tv_ans1_text =(TextView) findViewById(R.id.tv_ans1_text);
//			tv_ans1_time =(TextView) findViewById(R.id.tv_ans1_time);
//			tv_ans1_stuName =(TextView) findViewById(R.id.tv_ans1_stuName);
//			tv_ans1_sub =(TextView) findViewById(R.id.tv_ans1_sub);
//			iv_ans1_first_img =(ImageView) findViewById(R.id.iv_ans1_first_img);
//			iv_ans1_userImg =(ImageView) findViewById(R.id.iv_ans1_userImg);

		}
		
		public static class ViewHolder {
			public TextView tv_ans_time;
			public TextView tv_ans_stuName;
			public TextView tv_ans_text;
			public ImageView iv_ans_img;
			public ImageView iv_ans_userImg;
			

			

		}
}
