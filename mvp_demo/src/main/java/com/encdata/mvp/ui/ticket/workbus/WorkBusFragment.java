package com.encdata.mvp.ui.ticket.workbus;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.encdata.mvp.R;
import com.encdata.mvp.app.Constants;
import com.encdata.mvp.core.bean.FeedArticleData;
import com.encdata.mvp.core.bean.FeedArticleListData;
import com.encdata.mvp.core.bean.WorkBusData;
import com.encdata.mvp.core.bean.WorkBusListData;
import com.encdata.mvp.mvp.contract.WorkBusContract;
import com.encdata.mvp.mvp.fragment.BaseRootFragment;
import com.encdata.mvp.ui.article.adapter.ArticleListAdapter;
import com.encdata.mvp.ui.article.bean.BannerData;
import com.encdata.mvp.utils.CommonUtils;
import com.encdata.mvp.utils.GlideImageLoader;
import com.encdata.mvp.utils.JudgeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 上下班
 * Created by zhaoyuejun on 2018/10/12.
 */

public class WorkBusFragment extends BaseRootFragment<WorkBusPresenter> implements WorkBusContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    Banner work_bus_banner;
    @BindView(R.id.work_bus_recycler_view)
    RecyclerView work_bus_recycler_view;
    private List<WorkBusData> workBusData;
    WorkBusAdapter workBusAdapter;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_workbus;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setRefresh();
        mPresenter.loadData();
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void initRecyclerView() {
        workBusData = new ArrayList<>();
        workBusAdapter = new WorkBusAdapter(R.layout.item_workbus, workBusData);
        workBusAdapter.setOnItemClickListener((adapter, view, position) -> startBusDetail(view, position));
        workBusAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));
        work_bus_recycler_view.setLayoutManager(new LinearLayoutManager(_mActivity));
        work_bus_recycler_view.setHasFixedSize(true);
        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.head_fm_workbus, null));
        work_bus_banner = mHeaderGroup.findViewById(R.id.work_bus_banner);
        workBusAdapter.addHeaderView(mHeaderGroup);
        work_bus_recycler_view.setAdapter(workBusAdapter);
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.geton_station:
                showToast("上车");
                break;
            case R.id.getoff_station:
                showToast("下车");
                break;
        }
    }

    private void startBusDetail(View view, int position) {
        showToast("跳转详情");
    }

    @Override
    public void showWorkBusList(WorkBusListData workBusListData, boolean isRefresh) {
        if (workBusAdapter == null) {
            return;
        }
        if (isRefresh) {
            workBusData = workBusListData.getDatas();
            workBusAdapter.replaceData(workBusListData.getDatas());
        } else {
            workBusData.addAll(workBusListData.getDatas());
            workBusAdapter.addData(workBusListData.getDatas());
        }
        showNormal();
    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        work_bus_banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        work_bus_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        work_bus_banner.setImages(bannerImageList);
        //设置banner动画效果
        work_bus_banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        work_bus_banner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        work_bus_banner.isAutoPlay(true);
        //设置轮播时间
        work_bus_banner.setDelayTime(bannerDataList.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        work_bus_banner.setIndicatorGravity(BannerConfig.CENTER);

        work_bus_banner.setOnBannerListener(i -> JudgeUtils.startArticleDetailActivity(_mActivity, null,
                0, mBannerTitleList.get(i), mBannerUrlList.get(i),
                false, false, true));
        //banner设置方法全部调用完毕时最后调用
        work_bus_banner.start();
    }

    @Override
    public void reload() {
        if (mRefreshLayout != null && mPresenter != null
                && work_bus_recycler_view.getVisibility() == View.INVISIBLE
                && CommonUtils.isNetworkConnected()) {
            mRefreshLayout.autoRefresh();
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

}
