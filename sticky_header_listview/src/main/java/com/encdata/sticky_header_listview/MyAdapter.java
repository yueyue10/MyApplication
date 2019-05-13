package com.encdata.sticky_header_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.encdata.sticky_header_listview.bean.DaHuiDate;
import com.encdata.sticky_header_listview.bean.DaHuiInfo;
import com.encdata.sticky_header_listview.utils.StickyListHeadersAdapter;

import java.util.List;


public class MyAdapter extends BaseAdapter implements
        StickyListHeadersAdapter {

    private String[] mCountries = {"aBC", "bar"};
    private List<DaHuiDate> datas; // 原始数据源
    private final Context context;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<DaHuiDate> datas) {
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.vip_schdule_list_item, parent, false);
            holder.container_ll = convertView.findViewById(R.id.vip_list_item_container);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DaHuiDate item = datas.get(position);// 接口数据
        System.out.println("adapter:" + item.toString());
        int Num = item.getMeetting().size();
        holder.container_ll.removeAllViews();
        for (int i = 0; i < Num; i++) {
            final DaHuiInfo bean = item.getMeetting().get(i);
            View view = LayoutInflater.from(context).inflate(R.layout.huiyi_riqi_list_container, null);
            TextView tv_title = view.findViewById(R.id.huiyi_riqiContainer_tv_title);
            TextView tv_host = view.findViewById(R.id.huiyi_riqiContainer_host);
            TextView tv_date = view.findViewById(R.id.huiyi_riqiContainer_tv_date);
            LinearLayout containerLayout = view.findViewById(R.id.huiyi_riqiContainer_ll);
            tv_title.setText(bean.meettingtitle);
            tv_host.setText(bean.meettingtalkman);
            System.out.println("=============host=>"+ bean.meettingtalkman.length());
            if (bean.meettingtalkman.length() == 0) {
                tv_host.setVisibility(View.GONE);
            }
            String[] split = bean.meettingtime.split(" ");
            tv_date.setText(split[1]);
            // item项的点击事件
            containerLayout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "您点击的是日程位置为："+bean.meettingtitle,
                    // Toast.LENGTH_LONG).show();
                    // Intent intent = new Intent(context,
                    // HuiYiXiangQingActivity.class);
                    // intent.putExtra("pchi_id", bean.meettingid);
                    // context.startActivity(intent);
                }
            });
            holder.container_ll.addView(view);
        }
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.mytask_list_stickyhead,parent, false);
            holder.text = convertView.findViewById(R.id.mytask_stickTvHead);
            holder.iv = convertView.findViewById(R.id.vipschedule_stickIvIcon);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        holder.text.setText(datas.get(position).date + "  "+ datas.get(position).week);
        if (position % 3 == 0) {
            holder.text.setBackgroundResource(R.drawable.bg_date1);
            holder.iv.setImageResource(R.drawable.btn_meetingschedule_1);
        } else if (position % 3 == 1) {
            holder.text.setBackgroundResource(R.drawable.bg_date2);
            holder.iv.setImageResource(R.drawable.btn_meetingschedule_2);
        } else if (position % 3 == 2) {
            holder.text.setBackgroundResource(R.drawable.bg_date3);
            holder.iv.setImageResource(R.drawable.btn_meetingschedule_3);
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }

    class HeaderViewHolder {
        TextView text;
        ImageView iv;
    }

    class ViewHolder {
        LinearLayout container_ll;
    }

}
