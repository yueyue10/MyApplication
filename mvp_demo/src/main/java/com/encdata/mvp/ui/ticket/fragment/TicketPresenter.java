package com.encdata.mvp.ui.ticket.fragment;

import com.encdata.mvp.core.DataManager;
import com.encdata.mvp.mvp.contract.TicketContract;
import com.encdata.mvp.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/10/11.
 */

public class TicketPresenter extends BasePresenter<TicketContract.View> implements TicketContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public TicketPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(TicketContract.View view) {
        super.attachView(view);
    }

}
