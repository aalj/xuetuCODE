package com.xuetu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.R.color;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Xml.Encoding;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Question_ask extends Activity implements OnClickListener,
		OnKeyListener {
	// 声明变量
	int stu_id = 0;
	String quesText = null;
	String quesIma = null;
	int acpoNum = 0;
	int subId = 0;
	Long quesTime;
	TitleBar title;
	Context mContext;
	PopupWindow popupWindow;
	Bitmap bm = null;
	ProgressDialog progressDialog = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final int SELECT_PIC = 11;
	public static final int TAKE_PHOTO = 12;
	public static final int CROP_PHOTO = 13;
	File file = null;
	private String url = null;
	int curJifen = -1;
	Boolean btnFlag = true;
	// 声明页面控件
	private EditText et_question = null;
	private Button btn_ask = null;
	private ImageView iv_prop = null;
	private RelativeLayout rl_prop = null;
	private TextView tv_sub1;
	private TextView tv_sub2;
	private TextView tv_sub3;
	private TextView tv_sub4;
	private TextView tv_sub;
	private Uri imageUri;
	private RequestParams params;
	private HttpUtils hutils;
	private RequestParams paramsJ;
	private HttpUtils hutilsJ;
	String QuesTextCache = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Toast.makeText(Question_ask.this, subId + "", Toast.LENGTH_SHORT)
				.show();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_ask);
		stu_id = ((XueTuApplication) getApplication()).getStudent().getStuId();
		InitDengdaiDialog();
		getJifen(); // 获得当前用户的积分数
		title = (TitleBar) findViewById(R.id.title_questionAsk);
		iv_prop = (ImageView) findViewById(R.id.iv_prop);
		rl_prop = (RelativeLayout) findViewById(R.id.rl_prop);
		tv_sub = (TextView) findViewById(R.id.tv_sub);

		file = new File(Environment.getExternalStorageDirectory(),
				sdf.format(new Date(System.currentTimeMillis())) + ".jpg");
		imageUri = Uri.fromFile(file);
		rl_prop.setOnClickListener(this);
		title.setLeftLayoutClickListener(this);
		findViewById(R.id.btn_ask).setOnClickListener(this);
		findViewById(R.id.tv_sub).setOnClickListener(this);
		mContext = this;
		init();

	}

	// @Override
	// public void onResume() {
	//
	//
	// super.onResume();
	// }
	// 正在加载dialog
	private void InitDengdaiDialog() {
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("正在提交问题...");
		progressDialog.setCancelable(true);
		progressDialog.setOnKeyListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ask:
			if (stu_id <= 0) {
				Toast.makeText(Question_ask.this, "请先登录哟！", 0).show();
				break;
			} else {
				if (subId == 0) {
					Toast.makeText(Question_ask.this, "请选择学科！", 0).show();
					break;
				} else {
					if ("".equals(et_question.getText().toString())
							|| et_question.getText() == null) {
						Toast.makeText(Question_ask.this, "请输入问题！", 0).show();
						break;
					} else {
						if(et_question.getText().toString().equals(QuesTextCache)){
							Toast.makeText(Question_ask.this, "请不要重复相同提交问题", 0).show();}
						else{
						if(curJifen == -1) {
							Toast.makeText(Question_ask.this,
									"正在初始化积分 ", 0).show();
						}else{
						if (curJifen < 3) {
							Toast.makeText(Question_ask.this,
									"提问一条问题需要3积分哦，快去学习赚取积分吧！", 0).show();
							break;
						} else {
							btn_ask.setClickable(false);
							ask();
						}
						}
					}
				}
				}
			}
			// finish();
			break;
		case R.id.tv_sub:
			// this.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
			showPopupWinow(v);
			break;
		case R.id.rl_prop:
			AlertDialog.Builder builder = new AlertDialog.Builder(
					Question_ask.this);
			builder.setItems(new String[] { "拍照", "从相册中选择" },
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// which==0,拍照 which==1,从相册中选择
							prop(which);
						}
					}).create().show();
			break;
		case R.id.tv_sub1:
			subId = 1;
			// ((TextView)
			// v).setTextColor(this.getResources().getColor(R.color.blue1));
			tv_sub.setText("英语");
			popupWindow.dismiss();
			break;
		case R.id.tv_sub2:
			subId = 2;
			tv_sub.setText("高数");
			popupWindow.dismiss();
			break;
		case R.id.tv_sub3:
			subId = 3;
			tv_sub.setText("地理");
			popupWindow.dismiss();
			break;
		case R.id.tv_sub4:
			subId = 4;
			tv_sub.setText("化学");
			popupWindow.dismiss();
			break;
		case R.id.left_layout:
			finish();
			break;
		default:
			;
		}
	}

	public void ask() {
		// 学生id
		// 问题信息
		quesText = et_question.getText().toString();
		// 提问时间
		quesTime = System.currentTimeMillis();
		// Log.i("hehe", quesTime+"");
		// 图片
		quesIma = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/" + quesTime + ".jpg";
		// Log.i("hehe", String.valueOf(quesTime));
		// 积分
		acpoNum = 10;
		// 学科
		// subId = 1;
		HttpUtils hutils = new HttpUtils();
		RequestParams params = new RequestParams();
		if (file.exists()) {
			url = GetHttp.getHttpLC() + "SubmitQuestion";
			params.addBodyParameter(file.getAbsolutePath().replace("/", ""),
					file);
		} else {
			url = GetHttp.getHttpLC() + "SubmitQuestionWithoutImg";
		}
		params.addBodyParameter("stuId", String.valueOf(stu_id));
		params.addBodyParameter("quesText", et_question.getText().toString());
		QuesTextCache = et_question.getText().toString();
		params.addBodyParameter("quesTime", quesTime + "");
		params.addBodyParameter("acpoNum", String.valueOf(acpoNum));
		params.addBodyParameter("subId", String.valueOf(subId));

		hutils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i("hehe", "failed");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// 存入数据成功，跳回问题列表页面
						Toast.makeText(Question_ask.this,
								"submit success" + arg0.result, 0).show();
						btn_ask.setClickable(true);
						Question_ask.this.finish();
					}
				});
	}

	// 初始化控件
	public void init() {
		et_question = (EditText) findViewById(R.id.et_question);
		btn_ask = (Button) findViewById(R.id.btn_ask);
	}

	// 显示showPopupWindow

	public void showPopupWinow(View v) {
		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(mContext).inflate(
				R.layout.subject_pop, null);
		// 设置4个学科选项的监听实践
		contentView.findViewById(R.id.tv_sub1).setOnClickListener(
				Question_ask.this);
		contentView.findViewById(R.id.tv_sub2).setOnClickListener(
				Question_ask.this);
		contentView.findViewById(R.id.tv_sub3).setOnClickListener(
				Question_ask.this);
		contentView.findViewById(R.id.tv_sub4).setOnClickListener(
				Question_ask.this);

		popupWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}

		});

		// 设置按钮的点击事件
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.toumingbackground));
		// 设置好参数之后再show
		popupWindow.showAsDropDown(v);
		// popupWindow.showAtLocation(view.getParent(),ce, x, y)

	}

	public class MyOnclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			default:
				break;
			}
		}

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
			intent.putExtra("outputFormat",
					Bitmap.CompressFormat.JPEG.toString());
			intent.putExtra("noFaceDetection", true); // no face detection
			startActivityForResult(intent, SELECT_PIC);
		} else if (location == 0) {// 拍照
			// api guide: cemera
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(intent, TAKE_PHOTO);
		}
	}

	public void getJifen() {
		hutilsJ = new HttpUtils();
		paramsJ = new RequestParams();
		url = GetHttp.getHttpLC() + "DedaoJIFen";
		paramsJ.addBodyParameter("stuid", stu_id + "");
		hutilsJ.send(HttpMethod.POST, url, paramsJ,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						Log.i("hehe", arg0.result);
						curJifen = Integer.parseInt(arg0.result);
					}
				});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			Log.i("MainActivity", "select pic error!");
			return;
		}
		if (requestCode == SELECT_PIC) {
			if (imageUri != null) {
				InputStream is = null;
				try {
					// 读取图片到io流
					is = getContentResolver().openInputStream(imageUri);
					// 内存中的图片
					Bitmap bm = BitmapFactory.decodeStream(is);
					iv_prop.setImageBitmap(bm);
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
				iv_prop.setImageBitmap(bm);
			}
		}
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
