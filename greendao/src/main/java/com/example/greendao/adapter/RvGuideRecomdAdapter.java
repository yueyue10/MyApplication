package com.example.greendao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.greendao.R;
import com.example.greendao.bean.MessCtData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导引过程中的推荐信息适配器
 * Created by zhaoyuejun on 2017/11/22.
 */

public class RvGuideRecomdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<MessCtData> mDatas = new ArrayList<>();

    public RvGuideRecomdAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void refreshData(List<MessCtData> messCtData) {
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
        return new RvGuideRecomdAdapter.ViewHolder(inflater.inflate(R.layout.item_guide_recomd, parent, false));
    }

    // 填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((RvGuideRecomdAdapter.ViewHolder) holder).bind(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root_layout)
        LinearLayout root_layout;
        @BindView(R.id.recomd_iv)
        ImageView recomd_iv;
        @BindView(R.id.recomd_titletv)
        TextView recomd_titletv;
        @BindView(R.id.recomd_texttv)
        TextView recomd_texttv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final int position) {
            String picUrl = TextUtils.isEmpty(mDatas.get(position).getPicUrl()) ? "http://travel.enn.cn/group1/M00/00/34/CiaAUlsPSZKAJw79AAI_aCrfk0g165.jpg" : mDatas.get(position).getPicUrl();
            Glide.with(mContext).load(picUrl)
                    .apply(new RequestOptions().centerCrop().error(R.mipmap.ic_launcher)).into(recomd_iv);
            recomd_titletv.setText(mDatas.get(position).getName());
            recomd_texttv.setText(mDatas.get(position).getDescription());
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
    public RvGuideRecomdAdapter.OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RvGuideRecomdAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}