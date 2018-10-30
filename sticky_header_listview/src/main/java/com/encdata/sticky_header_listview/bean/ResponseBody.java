package com.encdata.sticky_header_listview.bean;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 * @author cff
 * @version 创建时间：2013-11-19 下午5:16:03
 * @param <E>
 * 
 */
public class ResponseBody<T> {
	public Base base = new Base();
	@Expose
	public ArrayList<T> list = new ArrayList<T>();

	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ResponseBody [base=" + base + ", list=" + list + "]";
	}

}