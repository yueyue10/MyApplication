package com.example.greendao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greendao.R;
import com.example.greendao.bean.MessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MessageListAdapter extends BaseAdapter {

    private Context context;
    private List<MessageBean> datas;
    private LayoutInflater inflater;
    MessageBean data;

    public MessageListAdapter(Context context, List<MessageBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_mymessage_list, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (datas.size() > 0) {
            data = datas.get(position);
            holder.message_title_tv.setText(data.title);
            switch (data.msg_type) {
                case "ask":
                    holder.message_tvpeiv.setBackgroundResource(R.mipmap.icon_ask_reply);
                    break;
                case "travel":
                    holder.message_tvpeiv.setBackgroundResource(R.mipmap.icon_travel_notification);
                    break;
                case "order":
                    holder.message_tvpeiv.setBackgroundResource(R.mipmap.icon_order_notification);
                    break;
                case "system":
                    holder.message_tvpeiv.setBackgroundResource(R.mipmap.icon_system_notification);
                    break;
                case "travel_tips":
                    holder.message_tvpeiv.setBackgroundResource(R.mipmap.icon_travel_pretips);
                    break;
                case "wonderful":
                    holder.message_tvpeiv.setBackgroundResource(R.mipmap.icon_wonderful_recomd);
                    break;
            }
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.message_tvpeiv)
        ImageView message_tvpeiv;
        @BindView(R.id.message_title_tv)
        TextView message_title_tv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
