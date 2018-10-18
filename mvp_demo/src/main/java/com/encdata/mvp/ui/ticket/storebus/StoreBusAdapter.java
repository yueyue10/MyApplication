package com.encdata.mvp.ui.ticket.storebus;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.encdata.mvp.R;
import com.encdata.mvp.core.bean.WorkBusData;
import com.encdata.mvp.ui.ticket.workbus.WorkBusViewHolder;

import java.util.List;

/**
 * Created by zhaoyuejun on 2018/10/17.
 */

public class StoreBusAdapter extends BaseQuickAdapter<WorkBusData, WorkBusViewHolder> {

    public StoreBusAdapter(int layoutResId, @Nullable List<WorkBusData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(WorkBusViewHolder helper, WorkBusData workBusData) {
        helper.setText(R.id.bus_name, workBusData.getBus_name());
        TextView ticket_status = helper.getView(R.id.ticket_status);
        ticket_status.setVisibility(View.GONE);
        helper.setText(R.id.geton_station, workBusData.getGeton_name());

        helper.setText(R.id.getoff_station, workBusData.getGetoff_name());
        helper.setText(R.id.ticket_price_old, "");
        helper.setText(R.id.ticket_price_now, "");
        helper.addOnClickListener(R.id.geton_station);
        helper.addOnClickListener(R.id.getoff_station);
    }
}
