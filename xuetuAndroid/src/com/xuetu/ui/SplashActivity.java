/**
 * SplashActivity.java
 * com.librarybooksearch.ui
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年11月8日 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
 */

package com.xuetu.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.xuetu.R;
import com.xuetu.utils.StreamUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.IOUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:SplashActivity Function: 页面欢迎页面
 * 
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015年11月8日 下午8:41:31
 * 
 * @see
 */
public class SplashActivity extends Activity {
	/**
	 * 表示需要升级
	 */
	private static final int SPLASH_UPDATA_DIALOG = 0;
	/**
	 * 表示不需要升级
	 */
	private static final int SPLASH_NOT_UPDATA = 1;
	/**
	 * 表示下载地址URL异常
	 */
	protected static final int SPLASH_URL_ERROR = 2;
	/**
	 * 表示从服务器读写文件异常
	 */
	protected static final int SPLASH_IO_ERROR = 3;
	/**
	 * 解析jsone异常
	 */
	protected static final int SPLASH_JSONE_ERROR = 4;
	/**
	 * 显示下载信息
	 */
	private TextView tv_splash_progress = null;
	/**
	 * 主页面显示的文本控件，显示当前的版本号
	 */
	private TextView tv_version = null;
	/**
	 * 在服务器里面的得到的应用下载地址
	 */
	private String apkurl = null;
	/**
	 * 在服务器里面得到的应用升级描述
	 */
	private String des = null;
	/**
	 * 在服务器里面得到的应用最新的版本号
	 */
	private String code = null;
	// 初始化Handle对象，并且实现相应的方法
	private Handler handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SPLASH_UPDATA_DIALOG:
				System.out.println("检测是否进入消息机制");
				// 这里弹出升级对话框
				showUpdataDialog();
				break;
			case SPLASH_NOT_UPDATA:
				enterHome();
				break;
			case SPLASH_IO_ERROR:
				Toast.makeText(getApplicationContext(),
						"错误码：" + SPLASH_IO_ERROR, 0).show();
				enterHome();
				break;
			case SPLASH_JSONE_ERROR:
				Toast.makeText(getApplicationContext(),
						"错误码：" + SPLASH_JSONE_ERROR, 0).show();
				enterHome();
				break;
			case SPLASH_URL_ERROR:
				Toast.makeText(getApplicationContext(),
						"错误码：" + SPLASH_URL_ERROR, 0).show();
				enterHome();
				break;

			default:
				break;
			}

			super.handleMessage(msg);

		}
	};
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		initView();

	}

	/**
	 * 
	 * initView:(页面初始化方法) (用于初始化一系列，对象)
	 * (同时把assets里面的一些相关文件写入到应用的内部资源里面data/data/应用包名/)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	private void initView() {
		tv_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_version.setText("版本号： " + getVersionNum());
		tv_splash_progress = (TextView) findViewById(R.id.tv_splash_progress);

		// 读取配置文件(设置功能生成的),判断设置里面是否,需要提醒升级
		preferences = getSharedPreferences("config", MODE_PRIVATE);
		// 根据配置文件判断是否需要执行检查是否有可升级应用
		if (preferences.getBoolean("updata", true)) {
			update();

		} else {
			new Thread() {
				public void run() {// 这为了让splash页面一定显示2秒
					SystemClock.sleep(2000);

					runOnUiThread(new Runnable() {// 该方法是在在子线程里面操作主线程

						@Override
						public void run() {

							enterHome();

						}
					});
				};
			}.start();
		}
		// 把归属地数据库写入应用文件
		copyDb();

	}

	/**
	 * 
	 * copyDb:(这里用一句话描述这个方法的作用) TODO(把assets里面的数据库 拷贝 到应用文件里面)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	private void copyDb() {
		// getFileDira()用于获取data/data/对应的软件包名/file文件的路径
		// 参数一、存储路径 参数二、需要创建的文件俺名字
		File file = new File(getFilesDir(), "library.db");
		if (!file.exists()) {

			// 读文件流
			InputStream open = null;
			// 写文件流
			FileOutputStream fos = null;
			AssetManager assetManager = SplashActivity.this.getAssets();

			try {
				// 打开资源文件面的文件
				open = assetManager.open("library.db");
				// 初始化文件输入流
				fos = new FileOutputStream(file);
				byte[] by = new byte[1024];
				int len = -1;
				while ((len = open.read(by)) != -1) {
					fos.write(by, 0, len);

				}

			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				// 通过XUtils里面的工具进行关闭流对象
				// 目的就是可以减少代码的行数
				IOUtils.closeQuietly(fos);
				IOUtils.closeQuietly(open);
			}
		}
	}

	/**
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选) TODO(对话框两个按钮,确定-->表示需要升级,同时调用升级方法.取消-->表示放弃这次升级)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	protected void showUpdataDialog() {

		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		// 设置对话框的图标
		dialog.setIcon(R.drawable.ic_launcher);
		// 设置对话框的标题显示的内容
		dialog.setTitle("需要升级了！！！");
		dialog.setCancelable(false);
		// 设置对话框的显示内
		dialog.setMessage(des);
		// 对话框升级按钮，及其点击事件
		dialog.setPositiveButton("升级啦", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 确定升级，然后调用下载的的方法进行下载
				Toast.makeText(SplashActivity.this, "需要升级", Toast.LENGTH_SHORT)
						.show();
				dialog.dismiss();
				// 调用下载的方法下载最新的应用
				download();

			}
		});
		// 对话框取消升级按钮
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "即可升级，享受更多服务",
						Toast.LENGTH_SHORT).show();
				enterHome();
				dialog.dismiss();
			}
		});
		dialog.create().show();

	}

	/**
	 * 
	 * download:(进行下载方法) TODO(采用开源框架xUtils实现)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	protected void download() {
		// 下载新的版本
		HttpUtils httpUtils = new HttpUtils();
		// 参数1 下载的地址 参数二 下载保存的位置
		httpUtils.download(apkurl, "/mnt/sdcard/safeAPP.apk",
				new RequestCallBack<File>() {

					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// 下载完成，调用安装应用的方法
						appInstall();

					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						// 让控件可见
						tv_splash_progress.setVisibility(View.VISIBLE);
						tv_splash_progress.setText(current + ":" + total);
						super.onLoading(total, current, isUploading);

					}

					@Override
					public void onFailure(
							com.lidroid.xutils.exception.HttpException arg0,
							String arg1) {
					}
				});

	}

	/**
	 * 
	 * appInstall:(安装下载下来的app) TODO(通过隐式意图，调用系统自带的安装应用的app)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	protected void appInstall() {

		// 采用隐式意图，通过意图过滤器，启动安装应用的，用以安装下载的应用
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(
				Uri.fromFile(new File("/mnt/sdcard/safeAPP.apk")),
				"application/vnd.android.package-archive");
		// 采用又返回值得开启Intent的方法，用以避免用户点击取消升级。
		startActivityForResult(intent, 0);

	}

	// 这是当上面的点击取消以后返回当前的页面调用的方法
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		enterHome();

		super.onActivityResult(requestCode, resultCode, data);

	}

	/**
	 * 
	 * enterHome:(进入主页面)
	 * 
	 * @param 设定文件
	 * 
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	protected void enterHome() {
		Intent intent = new Intent();
		intent.setClass(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();

	}

	/**
	 * 
	 * update:(连接服务器，并且从服务器里面下载信息 ,在线程里面进行)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	private void update() {
		System.out.println("kaishi lianjie ");
		new Thread() {
			@Override
			public void run() {
				Message msg = Message.obtain();
				long startTime = System.currentTimeMillis();
				try {
					// 设置连接服务器的地址
					URL url = new URL(
							"http://192.168.1.104:8180/downversion.html");
					// 通过服务器地址打开连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					// 设置连接超时
					conn.setConnectTimeout(5000);
					// 设置读取超时
					conn.setReadTimeout(5000);
					// 设置提交数据的类型(GET,POST)
					conn.setRequestMethod("GET");
					// 得到返回码,200表示连接成功
					int responseCode = conn.getResponseCode();
					if (responseCode == 200) {
						System.out.println("连接成功");
						// 读取服务器里面的内容 得到服务器里面的一个流对象
						InputStream inputStream = conn.getInputStream();
						// 调用工具类的inputStream转String的方法
						String json = StreamUtils.parserStream(inputStream);
						JSONObject jsonObject = new JSONObject(json);
						// 通过解析得到服务器的信息
						code = jsonObject.getString("code");
						des = jsonObject.getString("des");
						apkurl = jsonObject.getString("apkurl");
						// 打印信息用于调试
						System.out.println("code: " + code + "des: " + des
								+ "apkurl: " + apkurl);
						System.out.println("code: " + code + "    "
								+ (code.equals(getVersionNum())));
						if (code.equals(getVersionNum())) {
							// 本地版本服务器的版本相同
							// 不需要升级
							msg.what = SPLASH_NOT_UPDATA;
							System.out.println("不需要升级");
						} else {
							// 本地版本与服务器版本不相同
							// 需要升级
							msg.what = SPLASH_UPDATA_DIALOG;
							System.out.println("需要升级");
						}

					} else {
						System.out.println("连接失败");
					}
				} catch (MalformedURLException e) {
					msg.what = SPLASH_URL_ERROR;
					e.printStackTrace();

				} catch (IOException e) {
					msg.what = SPLASH_IO_ERROR;
					e.printStackTrace();

				} catch (JSONException e) {
					msg.what = SPLASH_JSONE_ERROR;
					// TODO Auto-generated catch block
					e.printStackTrace();

				} finally {
					long endRealtime = System.currentTimeMillis();
					long dTime = endRealtime - startTime;
					if (dTime < 2000) {
						SystemClock.sleep(2000 - dTime);
					}
					handle.sendMessage(msg);
				}

			};
		}.start();

	}

	/**
	 * 
	 * getVersionNum:(获取版本号)
	 * 
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @throws @since CodingExample Ver 1.1
	 */
	public String getVersionNum() {
		try {
			PackageManager pm = SplashActivity.this.getPackageManager();

			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			String version = info.versionName;
			return version;

		} catch (NameNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

}
