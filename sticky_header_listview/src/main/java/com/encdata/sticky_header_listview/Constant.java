package com.encdata.sticky_header_listview;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constant {

	public final static String DOMAIN = "https://mobile.silkroaddunhuang.com:8088";
	public final static String domain = "mobile.silkroaddunhuang.com";

	public final static String apkTarget = "Download"; // apk下载路径
	public final static String target = "/sdcard/wenbohui/cache/"; // 大会资料的下载路径
	public final static String dianpingTarget = "/sdcard/wenbohui/photo/"; // 点评图片下载路径
	public final static String uploadTarget = "/sdcard/wenbohui/upload/"; // 点评图片下载路径
	public final static String databaseTarget = "/sdcard/wenbohui/databases/";

	// 验证手机号码
	public static boolean checkString(String s) {
		return s.matches("^1[3|4|5|7|8][0-9]\\d{8}$");
	}

	public static boolean checkUrlString(String s) {
		return s.matches("[a-zA-z]+://[^\\s]*");

	}

	public static Gson gson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();

}
