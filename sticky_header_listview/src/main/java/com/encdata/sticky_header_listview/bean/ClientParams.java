package com.encdata.sticky_header_listview.bean;


import com.encdata.sticky_header_listview.Constant;

import java.io.Serializable;

/**
 * @author ghost
 * @version 创建时间：2013-9-6 下午4:25:09
 * 
 */
public class ClientParams implements Serializable {
	private static final long serialVersionUID = -1325540944167846125L;
	public final static String HTTP_POST = "POST";
	public final static String HTTP_GET = "GET";
	public String http_method = HTTP_GET;
	public String domain = Constant.DOMAIN;// 域名
	public String url = "";
	public String params = "";

	@Override
	public String toString() {
		return "ClientParams [http_method=" + http_method + ", domain="
				+ domain + ", url=" + url + ", params=" + params + "]";
	}

}
