package com.example.greendao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greendao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 普通列表适配器
 * Created by zhaoyuejun on 2017/11/22.
 */

public class RvCommonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String> mDatas = new ArrayList<>();

    public RvCommonListAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void refreshData(List<String> messCtData) {
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
        return new RvCommonListAdapter.ViewHolder(inflater.inflate(R.layout.item_common_list, parent, false));
    }

    // 填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((RvCommonListAdapter.ViewHolder) holder).bind(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root_layout)
        LinearLayout root_layout;
        @BindView(R.id.content_tv)
        TextView content_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final int position) {
            content_tv.setText(mDatas.get(position));
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
    public RvCommonListAdapter.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RvCommonListAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}