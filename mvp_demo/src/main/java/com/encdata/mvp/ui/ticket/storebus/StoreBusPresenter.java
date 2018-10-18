package com.encdata.mvp.ui.ticket.storebus;

import com.encdata.mvp.R;
import com.encdata.mvp.app.MyApplication;
import com.encdata.mvp.core.DataManager;
import com.encdata.mvp.core.bean.WorkBusListData;
import com.encdata.mvp.mvp.contract.StoreBusContract;
import com.encdata.mvp.mvp.presenter.BasePresenter;
import com.encdata.mvp.utils.BaseObserver;
import com.encdata.mvp.utils.RxUtils;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/10/17.
 */

public class StoreBusPresenter extends BasePresenter<StoreBusContract.View> implements StoreBusContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public StoreBusPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getStoreBusList(int page, boolean isShowError) {
        addSubscribe(mDataManager.getWorkBusList(page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<WorkBusListData>(mView,
                        MyApplication.getInstance().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onNext(WorkBusListData projectListData) {
                        mView.showStoreBusList(projectListData);
                    }
                }));
    }

}
