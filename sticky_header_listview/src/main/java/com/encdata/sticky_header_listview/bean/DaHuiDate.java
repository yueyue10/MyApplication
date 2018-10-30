package com.encdata.sticky_header_listview.bean;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 * 大会日期分类实体类
 */
public class DaHuiDate {
	@Expose
	public String date;// 日期
	@Expose
	public String week;// 星期几
	@Expose
	public ArrayList<DaHuiInfo> meetting;// 当天大会数据集合

	public ArrayList<DaHuiInfo> getMeetting() {
		return meetting;
	}

	public void setMeetting(ArrayList<DaHuiInfo> meetting) {
		this.meetting = meetting;
	}

	@Override
	public String toString() {
		return "DaHuiDate [date=" + date + ", week=" + week + ", meetting="
				+ meetting + "]";
	}

}
