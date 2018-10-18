package com.encdata.mvp.mvp.contract;

import com.encdata.mvp.core.bean.WorkBusListData;
import com.encdata.mvp.mvp.presenter.AbstractPresenter;
import com.encdata.mvp.mvp.view.AbstractView;

/**
 * Created by zhaoyuejun on 2018/10/17.
 */

public interface StoreBusContract {

    interface View extends AbstractView {
        void showStoreBusList(WorkBusListData workBusListData);
    }

    interface Presenter extends AbstractPresenter<View> {
        void getStoreBusList(int page, boolean isShowError);

    }
}
