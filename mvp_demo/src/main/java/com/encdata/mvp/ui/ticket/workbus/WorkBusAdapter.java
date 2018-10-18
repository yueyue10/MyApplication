package com.encdata.mvp.ui.ticket.workbus;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.encdata.mvp.R;
import com.encdata.mvp.core.bean.WorkBusData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/2/24
 */

public class WorkBusAdapter extends BaseQuickAdapter<WorkBusData, WorkBusViewHolder> {

    private boolean isCollectPage;
    private boolean isSearchPage;
    private boolean isNightMode;

    public WorkBusAdapter(int layoutResId, @Nullable List<WorkBusData> data) {
        super(layoutResId, data);
    }

    public void isCollectPage() {
        isCollectPage = true;
        notifyDataSetChanged();
    }

    public void isSearchPage() {
        isSearchPage = true;
        notifyDataSetChanged();
    }

    public void isNightMode(boolean isNightMode) {
        this.isNightMode = isNightMode;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(WorkBusViewHolder helper, WorkBusData workBusData) {
        helper.setText(R.id.bus_name, workBusData.getBus_name());
        helper.setText(R.id.ticket_status, workBusData.getTicket_status());

        SpannableStringBuilder style1 = new SpannableStringBuilder(workBusData.getGeton_time() + " " + workBusData.getGeton_name());
        SpannableStringBuilder style2 = new SpannableStringBuilder("-" + workBusData.getGeton_type());
        style1.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.black)), 0, style1.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置前面的字体颜色
        style2.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.title_bar)), 0, style2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置前面的字体颜色
        helper.setText(R.id.geton_station, style1.append(style2));

        helper.setText(R.id.getoff_station, workBusData.getGetoff_time() + " " + workBusData.getGetoff_name());
        helper.setText(R.id.ticket_price_old, workBusData.getTicket_price_old());
        TextView ticket_price_old = helper.getView(R.id.ticket_price_old);
        ticket_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.ticket_price_now, workBusData.getTicket_price_now());
        helper.addOnClickListener(R.id.geton_station);
        helper.addOnClickListener(R.id.getoff_station);
    }

}
