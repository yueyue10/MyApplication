package com.encdata.mvp.ui.ticket.storebus;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.encdata.mvp.R;
import com.encdata.mvp.core.bean.WorkBusData;
import com.encdata.mvp.core.bean.WorkBusListData;
import com.encdata.mvp.mvp.contract.StoreBusContract;
import com.encdata.mvp.mvp.fragment.BaseRootFragment;
import com.encdata.mvp.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 收藏路线
 * Created by zhaoyuejun on 2018/10/16.
 */

public class StoreBusFragment extends BaseRootFragment<StoreBusPresenter> implements StoreBusContract.View {

    @BindView(R.id.storebus_recycler_view)
    RecyclerView storebus_recycler_view;
    @BindView(R.id.normal_view)
    SmartRefreshLayout smart_refresh_layout;
    @BindView(R.id.content_layout)
    LinearLayout content_layout;
    private boolean isRefresh = true;
    private int mCurrentPage;
    private StoreBusAdapter storeBusAdapter;
    private List<WorkBusData> workBusData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_storebus;
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
        mPresenter.getStoreBusList(mCurrentPage, true);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    public void showError() {
        super.showError();
    }

    private void initRecyclerView() {
        workBusData = new ArrayList<>();
        storeBusAdapter = new StoreBusAdapter(R.layout.item_workbus, workBusData);
        storeBusAdapter.setOnItemClickListener((adapter, view, position) -> startBusDetail(view, position));
        storebus_recycler_view.setLayoutManager(new LinearLayoutManager(_mActivity));
        storebus_recycler_view.setHasFixedSize(true);
        storebus_recycler_view.setAdapter(storeBusAdapter);
    }

    private void startBusDetail(View view, int position) {
        showToast("跳转详情");
    }

    @Override
    public void showStoreBusList(WorkBusListData workBusListData) {
        if (storeBusAdapter == null)
            return;
        if (isRefresh) {
            workBusData = workBusListData.getDatas();
            storeBusAdapter.replaceData(workBusListData.getDatas());
        } else {
            workBusData.addAll(workBusListData.getDatas());
            storeBusAdapter.addData(workBusListData.getDatas());
        }
        showNormal();
    }

    @Override
    public void reload() {
        if (mPresenter != null) {
            mPresenter.getStoreBusList(mCurrentPage, false);
        }
    }

    public void setRefresh() {
        mCurrentPage = 1;
        if (smart_refresh_layout == null) {
            return;
        }
        smart_refresh_layout.setOnRefreshListener(refreshLayout -> {
            mCurrentPage = 1;
            isRefresh = true;
            mPresenter.getStoreBusList(mCurrentPage, false);
            refreshLayout.finishRefresh(1000);
        });
        smart_refresh_layout.setOnLoadMoreListener(refreshLayout -> {
            mCurrentPage++;
            isRefresh = false;
            mPresenter.getStoreBusList(mCurrentPage, false);
            refreshLayout.finishLoadMore(1000);
        });
    }
}
