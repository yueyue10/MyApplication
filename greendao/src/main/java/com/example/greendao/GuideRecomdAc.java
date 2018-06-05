package com.example.greendao;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.greendao.adapter.RvGuideRecomdAdapter;
import com.example.greendao.bean.JdlbBean;
import com.example.greendao.bean.MessCtData;
import com.example.greendao.db.MessCtDaoOpe;
import com.example.greendao.utils.ConverterUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 导引过程中推荐的消息列表界面
 * Created by zhaoyuejun on 2017/11/15.
 */
public class GuideRecomdAc extends BaseActivity {

    @BindView(R.id.recomd_rv)
    RecyclerView recomd_rv;
    String title = "正在建设中...";
    String type = "";
    RvGuideRecomdAdapter rvGuideRecomdAdapter;
    List<MessCtData> messCtDatas;
    List<MessCtData> mDatas = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_guide_recomd);
        if (getIntent().getStringExtra("title") != null)
            title = getIntent().getStringExtra("title");
        if (getIntent().getStringExtra("type") != null)
            type = getIntent().getStringExtra("type");
        setTitle(title);
        setImageBack();
        process();
        initData();
    }

    private void process() {
        rvGuideRecomdAdapter = new RvGuideRecomdAdapter(mContext);
        recomd_rv.setAdapter(rvGuideRecomdAdapter);
        recomd_rv.setLayoutManager(new LinearLayoutManager(mContext));
        rvGuideRecomdAdapter.setOnItemClickListener(new RvGuideRecomdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mDatas.get(position).getResult() == null)
                    return;
                JdlbBean jdlbBean = ConverterUtils.modelA2B(mDatas.get(position), JdlbBean.class);

            }
        });
    }

    private void initData() {
        messCtDatas = MessCtDaoOpe.queryAll(this);
        switch (getIntent().getStringExtra("type")) {
            case "recomd_other":
                for (int i = 0; i < messCtDatas.size(); i++) {
                    KLog.d(messCtDatas.get(i).getResult());
                    if (messCtDatas.get(i).getResult() != null && !"9".equals(messCtDatas.get(i).getResult()))
                        mDatas.add(messCtDatas.get(i));
                }
                break;
            case "spot_media":
                for (int i = 0; i < messCtDatas.size(); i++) {
                    if (messCtDatas.get(i).getResult() != null && "9".equals(messCtDatas.get(i).getResult()))
                        mDatas.add(messCtDatas.get(i));
                }
                break;
        }
        rvGuideRecomdAdapter.refreshData(mDatas);
    }

}