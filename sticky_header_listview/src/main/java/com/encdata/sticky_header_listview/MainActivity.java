package com.encdata.sticky_header_listview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.encdata.sticky_header_listview.view.StickyListHeadersListView;
import com.encdata.sticky_header_listview.bean.Base;
import com.encdata.sticky_header_listview.bean.DaHuiDate;
import com.encdata.sticky_header_listview.bean.ResponseBody;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {

	private MyAdapter adapterForRiqi;
	StickyListHeadersListView riqi_stickHeadersListView;
	TextView in_title;
	SwipeRefreshLayout swipe_anriqi;

	Handler mhandler = new Handler() {
		public void handleMessage(Message msg) {
			swipe_anriqi.setRefreshing(false);
			swipe_anriqi.setEnabled(true);
			switch (msg.what) {
			case 0:
				//
				ResponseBody<DaHuiDate> res = (ResponseBody<DaHuiDate>) msg.obj;
				ArrayList<DaHuiDate> dates = new ArrayList<DaHuiDate>();
				dates.addAll(res.list);
				System.out.println("mhandler:" + dates.toString());
				adapterForRiqi = new MyAdapter(MainActivity.this, dates);
				riqi_stickHeadersListView.setAdapter(adapterForRiqi);
				break;
			case 1:

				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 透明状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		loadData();
		setAllClick();
	}

	private void initView() {
		swipe_anriqi = findViewById(R.id.swipe_anriqi);
		in_title = findViewById(R.id.in_title);
		riqi_stickHeadersListView = findViewById(R.id.huiyi_riqi_stickyHeaderview);

		in_title.setText("大会日程");
		swipe_anriqi.setColorSchemeColors(this.getResources().getColor(R.color.swiperefreshlayoutcolor));
	}

	private void loadData() {
		try {
			Type typeToken = new TypeToken<ArrayList<DaHuiDate>>() {
			}.getType();
			InputStream in = getAssets().open("DaHuiRiCheng.json");
			// 将字符流转换成字节流
			// InputStreamReader json = new InputStreamReader(in);
			String content = convertStreamToString(in);
			Base entity = Constant.gson.fromJson(content, Base.class);
			//
			ResponseBody<DaHuiDate> body = new ResponseBody<DaHuiDate>();
			body.list = Constant.gson.fromJson(entity.info, typeToken);
			System.out.println("loadData" + body.list);
			Message msg = mhandler.obtainMessage();
			msg.what = 0;
			msg.obj = body;
			msg.sendToTarget();
			// mhandler.sendMessageDelayed(msg, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setAllClick() {
		swipe_anriqi.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// loadData();
				// 启动线程然后只为了延时让swipe停止刷新
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(1 * 1000);
							mhandler.sendEmptyMessage(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});

	}

	// 将下载的流转成string
	private String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
