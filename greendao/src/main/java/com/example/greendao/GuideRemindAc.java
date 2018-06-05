package com.example.greendao;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.greendao.adapter.RvGuideRemindAdapter;
import com.example.greendao.bean.RecomdRouteData;
import com.example.greendao.db.RecomdRouteDaoOpe;

import java.util.List;

import butterknife.BindView;

/**
 * 导引过程中提示的消息列表界面
 * Created by zhaoyuejun on 2017/11/15.
 */
public class GuideRemindAc extends BaseActivity {

    @BindView(R.id.recomd_rv)
    RecyclerView recomd_rv;
    String title = "正在建设中...";
    RvGuideRemindAdapter rvGuideRecomdAdapter;
    List<RecomdRouteData> recomdRouteData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_guide_recomd);
        if (getIntent().getStringExtra("title") != null)
            title = getIntent().getStringExtra("title");
        setTitle(title);
        setImageBack();
        process();
        initData();
    }

    private void process() {
        rvGuideRecomdAdapter = new RvGuideRemindAdapter(mContext);
        recomd_rv.setAdapter(rvGuideRecomdAdapter);
        recomd_rv.setLayoutManager(new LinearLayoutManager(mContext));
        rvGuideRecomdAdapter.setOnItemClickListener(new RvGuideRemindAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void initData() {
        recomdRouteData = RecomdRouteDaoOpe.queryAll(this);
        rvGuideRecomdAdapter.refreshData(recomdRouteData);
    }

}