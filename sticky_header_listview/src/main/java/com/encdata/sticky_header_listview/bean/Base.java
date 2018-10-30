package com.encdata.sticky_header_listview.bean;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class Base implements Serializable {
	public int responseCode = 0;
	@Expose
	public String code = "999";
	@Expose
	public JsonElement info=null;
	@Expose
	public int pagecount = 0000;
	@Expose
	public String smallPorurl = "";
	@Expose
	public String baoanphone="";
	@Expose
	public String fanyiphone = "";
	@Expose
	public String yihurenyuanphone="";
	@Expose
	public String sixin="";//马晓勇加，是否可以发私信 1可以  2不可以
	@Expose
	public String message = "服务器繁忙,请稍后再试";
	public ClientParams params;// 请求参数

	@Override
	public String toString() {
		return "Base [responseCode=" + responseCode + ", errorCode=" + code
				+ ", info=" + info + ", errorMsg=" + message + ", params="
				+ params + "]";
	}

}
