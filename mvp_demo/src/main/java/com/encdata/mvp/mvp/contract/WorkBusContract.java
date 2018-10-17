package com.encdata.mvp.mvp.contract;

import com.encdata.mvp.core.bean.WorkBusListData;
import com.encdata.mvp.mvp.presenter.AbstractPresenter;
import com.encdata.mvp.mvp.view.AbstractView;
import com.encdata.mvp.ui.article.bean.BannerData;

import java.util.List;

/**
 * Created by zhaoyuejun on 2018/10/12.
 */

public interface WorkBusContract {

    interface View extends AbstractView {
        /**
         * Show banner data
         */
        void showBannerData(List<BannerData> bannerDataList);

        /**
         * Show content
         */
        void showWorkBusList(WorkBusListData workBusListData, boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<View> {
        void loadData();

        void autoRefresh(boolean isShowError);

        void loadMore();

        void loadMoreData();

        void getBannerData(boolean isShowError);

        void getWorkBusList(boolean isShowError);
    }
}
