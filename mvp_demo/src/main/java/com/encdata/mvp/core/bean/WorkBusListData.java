package com.encdata.mvp.core.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyuejun on 2018/10/17.
 */

public class WorkBusListData {

    private List<WorkBusData> datas;

    public List<WorkBusData> getDatas() {
        if (datas == null) {
            return new ArrayList<>();
        }
        return datas;
    }

    public void setDatas(List<WorkBusData> datas) {
        this.datas = datas;
    }
}
