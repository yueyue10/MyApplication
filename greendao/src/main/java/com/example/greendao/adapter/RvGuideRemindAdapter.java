package com.example.greendao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greendao.R;
import com.example.greendao.bean.RecomdRouteData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导引过程中的提示信息适配器
 * Created by zhaoyuejun on 2017/11/22.
 */

public class RvGuideRemindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<RecomdRouteData> mDatas = new ArrayList<>();

    public RvGuideRemindAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void refreshData(List<RecomdRouteData> messCtData) {
        this.mDatas.clear();
        this.mDatas.addAll(messCtData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RvGuideRemindAdapter.ViewHolder(inflater.inflate(R.layout.item_guide_remind, parent, false));
    }

    // 填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((RvGuideRemindAdapter.ViewHolder) holder).bind(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root_layout)
        LinearLayout root_layout;
        @BindView(R.id.remind_titletv)
        TextView remind_titletv;
        @BindView(R.id.remind_contenttv)
        TextView remind_contenttv;
        @BindView(R.id.remind_timetv)
        TextView remind_timetv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final int position) {
            remind_titletv.setText(mDatas.get(position).getName());
            remind_contenttv.setText(mDatas.get(position).getComment());
            remind_timetv.setText(mDatas.get(position).getUpdateTime());
            root_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(position);
                }
            });
        }
    }

    // 给RecyclerView的Item添加点击事件
    public RvGuideRemindAdapter.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RvGuideRemindAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}