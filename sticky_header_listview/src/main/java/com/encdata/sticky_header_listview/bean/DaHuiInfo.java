package com.encdata.sticky_header_listview.bean;

import com.google.gson.annotations.Expose;

/**
 * 大会概要信息实体类
 */
public class DaHuiInfo {
	@Expose
	public String meettingtime = "";// 大会时间安排
	@Expose
	public String meettingid = "";// 会议id
	@Expose
	public String meettingtalkman = "";// 主持人
	@Expose
	public String meettingtitle = "";// 大会名称
	@Expose
	public String startTime = "";// 开始时间
	@Expose
	public String endTiime = "";// 结束时间

	@Override
	public String toString() {
		return "DaHuiInfo [meettingtime=" + meettingtime + ", meettingid="
				+ meettingid + ", meettingtalkman=" + meettingtalkman
				+ ", meettingtitle=" + meettingtitle + ", startTime="
				+ startTime + ", endTiime=" + endTiime + "]";
	}

}
