package com.encdata.mvp.ui.ticket.workbus;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.encdata.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/2/24
 */

public class WorkBusViewHolder extends BaseViewHolder {

    @BindView(R.id.bus_name)
    TextView bus_name;
    @BindView(R.id.ticket_status)
    TextView ticket_status;
    @BindView(R.id.geton_station)
    TextView geton_station;
    @BindView(R.id.getoff_station)
    TextView getoff_station;
    @BindView(R.id.ticket_price_old)
    TextView ticket_price_old;
    @BindView(R.id.ticket_price_now)
    TextView ticket_price_now;

    public WorkBusViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
