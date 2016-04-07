/**
 * RecomFrag.java
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.QuestionFragAdapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.Question;
import com.xuetu.ui.Answer_list;
import com.xuetu.ui.ExchangeCouponActivity;
import com.xuetu.ui.Question_ask;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.KeyboardUtils;
import com.xuetu.view.OnRefreshListener;
import com.xuetu.view.RefreshListView;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:RecomFrag Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015年11月8日 下午3:46:46
 * 
 * @see
 */
public class QuestionFrag extends Fragment implements OnRefreshListener,
		OnKeyListener {

	// 显示所有问题的列表

	protected static final String TAG = "TAG";
	private final int REFRESH_TEMP = 1;
	private final int REFRESH_LIMIT = 2;
	int countpage = 1;
	/** 请求数据的页数 */
	private int pageIndex = 0;
	// 表示是否是第一次进入改也页面
	boolean firstInto = true;
	XueTuApplication xt ;
	Message msg5 = null;
	RefreshListView lv = null;
	HttpUtils hutils = new HttpUtils();
	List<Question> list = new ArrayList<Question>();
	List<Question> oldlist = new ArrayList<Question>();
	View view = null;
	View viewPop = null;
	// ListView lv = null;
	RelativeLayout rl_top;
	RelativeLayout rl_left;
	RelativeLayout rl_right;
	RelativeLayout right_layout;
	TextView tv_title;
	TextView tv_ansNum;
	ImageView iv_back;
	ImageView iv_like;
	String url = null;
	Drawable dr_save;
	Drawable dr_saved;
	int sub_id = 0;
	int stu_id = 0;
	LinearLayout ll_question_item;
	ProgressDialog progressDialog = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	QuestionFragAdapter adapter = null;
	PopupWindow popupWindow = null;
	HttpUtils hutilsGetSubQues = new HttpUtils();
	String urlSub = null;
	RequestParams paramsSub = null;

	// private Set<Integer> listtag = new HashSet<Integer>();
	// MyBasesadapter<Question> adapter = null;
	// MyQuestionListBaseAdapter adapter = null;
	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		xt = (XueTuApplication) getContext().getApplicationContext();
		//得到所有没有图片的集合
		
		//第一次加载数据
//		InitData(1, REFRESH_TEMP);
		
		view = inflater.inflate(R.layout.question_frag, null);
		right_layout = (RelativeLayout) view.findViewById(R.id.right_layout);
		viewPop = inflater.inflate(R.layout.title, null);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		lv = (RefreshListView) view.findViewById(R.id.lv_question);
		setOnclickListener();
		
		InitDengdaiDialog();
//		adapter = new QuestionFragAdapter(list, getContext());
//		lv.setAdapter(adapter);
		lv.setOnRefreshListener(this);
		return view;
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// case 4:
			// list =(List<Question>) msg.obj;
			// adapter.notifyDataSetChanged();
			// lv.setSelection(0);
			// showDengdai();break;
			case 5: // 切换fragment时刷新listview
				Log.i(TAG, "回到页面");
//				list = (List<Question>) msg.obj;
				// Log.i("hehe", "切换时notifychange");
				// adapter.notifyDataSetChanged();
				if(adapter!=null){
					adapter.notifyDataSetChanged();
				}
//					adapter = new QuestionFragAdapter(list, getActivity());
//				lv.setAdapter(adapter);}
				else{
					adapter = new QuestionFragAdapter(list, getActivity());
					lv.setAdapter(adapter);
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	// fragment 从被隐藏回到显示状态时自动刷新
//	@Override
//	public void onHiddenChanged(boolean hidden) {
//		// TODO Auto-generated method stub
//		if (!hidden) {
//
//			InitData(1, REFRESH_TEMP);
//			// handler.sendMessage(msg5);
//		}
//		super.onHiddenChanged(hidden);
//	}
	
	public class MyOnclickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			sub_id = 0;
			switch (v.getId()) {
			case R.id.tv_title:
				showPopupWinow(v);
				lv.setFocusableInTouchMode(true);
				break;
			case R.id.right_layout:
				if (stu_id > 0) {
					Intent intent = new Intent(getContext(), Question_ask.class);
					getContext().startActivity(intent);
				} else {
					Toast.makeText(getContext(), "请先登录哟！", 0).show();
				}
				break;
			case R.id.tv_sub1:
				sub_id = 1;
				tv_title.setText("英语");
				popupWindow.dismiss();
				break;
			case R.id.tv_sub2:
				sub_id = 2;
				tv_title.setText("物理");
				popupWindow.dismiss();
				break;
			case R.id.tv_sub3:
				sub_id = 3;
				tv_title.setText("地理");
				popupWindow.dismiss();
				break;
			case R.id.tv_sub4:
				sub_id = 4;
				tv_title.setText("化学");
				popupWindow.dismiss();
				break;
			}
			if (sub_id != 0) {
				getXueke();
			}
		}
		
		
		
		
		
		private void getXueke() {
			// 按学科显示问题
			progressDialog.show();
			urlSub = GetHttp.getHttpLC() + "GetSubQues";
			paramsSub = new RequestParams();
			paramsSub.addBodyParameter("sub_id", sub_id + "");
			hutilsGetSubQues.send(HttpMethod.POST, urlSub, paramsSub,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(getContext(), "请求数据失败", 0).show();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							Gson gson = new GsonBuilder()
									.enableComplexMapKeySerialization()
									.setPrettyPrinting().disableHtmlEscaping()
									.setDateFormat("yyyy-MM-dd HH:mm:ss")
									.create();
							Type typeSub = new TypeToken<List<Question>>() {
							}.getType();
							List<Question> questionSub = gson.fromJson(
									arg0.result, typeSub);
							list.removeAll(oldlist);
							list.addAll(0, questionSub);
							adapter.notifyDataSetChanged();
							oldlist = questionSub;
							progressDialog.dismiss();
							lv.setSelection(0);
							// Message msg = Message.obtain();
							// msg.what=4;
							// msg.obj=list;
							// handler.sendMessage(msg);
						}
					});
		}
	}
	//得到所有有问题图片的问题id，存到application对象中
	public void getQuesIdWithImg(){
		url = GetHttp.getHttpLC()+"GetQuesWithImg";
		paramsSub = new RequestParams();
		hutils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

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
				Type type = new TypeToken<Set<Integer>>(){}.getType();
				Set<Integer> sQuesId = gson.fromJson(arg0.result, type);
				xt.setSetQuesWithImg(sQuesId);
			}
		});
	}
	// 正在加载dialog
	private void InitDengdaiDialog() {
		progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("正在加载...");
		progressDialog.setCancelable(true);
		progressDialog.setOnKeyListener(this);
	}

	@Override
	public void onResume() {
		lv.setSelection(0);
		stu_id = ((XueTuApplication) getActivity().getApplication())
				.getStudent().getStuId();
		getQuesIdWithImg();
		InitData(1, REFRESH_TEMP);
		super.onResume();
	}

	public void setOnclickListener() {
		// rl_right.setOnClickListener(this);
		tv_title.setOnClickListener(new MyOnclickListener());
		right_layout.setOnClickListener(new MyOnclickListener());
	}

	/**
	 * 发送网络请求，下载所有问题信息
	 * 
	 */
	int count = 0;

	public void InitData(final int tempnum, int temp) {
		url = GetHttp.getHttpLC() + "GetPageQuestion";
		RequestParams params = new RequestParams();
		if (temp == REFRESH_TEMP) {
			params.addBodyParameter("page", "1");// 查询第1页
		} else {
			countpage++;
			params.addBodyParameter("page", countpage + "");// 查询第1页
		}
		params.addBodyParameter("num", "10");// 每页显示5条
		 hutils.configCurrentHttpCacheExpiry(0);
		hutils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), "网络不太好哦！", 0).show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						// 指定date格式的gson对象
						Gson gson = new GsonBuilder()
								.enableComplexMapKeySerialization()
								.setPrettyPrinting().disableHtmlEscaping()
								.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
						List<Question> lists = new ArrayList<Question>();
						Type listtype = new TypeToken<List<Question>>() {
						}.getType();
						lists = gson.fromJson(arg0.result, listtype);

						if (tempnum == 1) {
							list.removeAll(oldlist);
							list.addAll(0, lists);
							if (adapter != null) {
								adapter.notifyDataSetChanged();
							}
							lv.hideHeaderView();
							oldlist = lists;
						} else {
							list.addAll(lists);
							adapter.notifyDataSetChanged();
							// 控制脚布局隐藏
							lv.hideFooterView();
						}
						msg5 = Message.obtain();
						msg5.what = 5;
						msg5.obj = list;
						handler.sendMessage(msg5);
					}
				});
	}

	//
	@Override
	public void onDownPullRefresh() {
		// 这是下拉刷新出来的数据

		InitData(1, REFRESH_TEMP);

	}

	@Override
	public void onLoadingMore() {
		// 这是加载更多出来的数据1
		InitData(2, REFRESH_LIMIT);
	}

	// 弹出学科列表
	public void showPopupWinow(View v) {
		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(getContext()).inflate(
				R.layout.subject_pop, null);
		// 设置4个学科选项的监听实践
		contentView.findViewById(R.id.tv_sub1).setOnClickListener(
				new MyOnclickListener());
		contentView.findViewById(R.id.tv_sub2).setOnClickListener(
				new MyOnclickListener());
		contentView.findViewById(R.id.tv_sub3).setOnClickListener(
				new MyOnclickListener());
		contentView.findViewById(R.id.tv_sub4).setOnClickListener(
				new MyOnclickListener());
		// 设置按钮的点击事件
		//
		// Button button = (Button) contentView.findViewById(R.id.button1);
		// button.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Toast.makeText(mContext, "button is pressed",
		// Toast.LENGTH_SHORT).show();
		// }
		// });

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
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.toumingbackground));
		// 设置好参数之后再show
		popupWindow.showAsDropDown(v);
		// popupWindow.showAtLocation(view.getParent(),ce, x, y)
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (progressDialog != null)
				progressDialog.dismiss();
		}
		return false;
	}

}
