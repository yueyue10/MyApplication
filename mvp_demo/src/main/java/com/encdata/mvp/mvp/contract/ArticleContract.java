package com.encdata.mvp.mvp.contract;


import com.encdata.mvp.core.bean.FeedArticleListData;
import com.encdata.mvp.mvp.presenter.AbstractPresenter;
import com.encdata.mvp.mvp.view.AbstractView;
import com.encdata.mvp.ui.article.bean.BannerData;

import java.util.List;

/**
 * @author quchao
 * @date 2017/12/7
 */

public interface ArticleContract {

    interface View extends AbstractView {
        /**
         * Show banner data
         *
         * @param bannerDataList List<BannerData>
         */
        void showBannerData(List<BannerData> bannerDataList);

        /**
         * Show content
         *
         * @param feedArticleListData FeedArticleListData
         * @param isRefresh           If refresh
         */
        void showArticleList(FeedArticleListData feedArticleListData, boolean isRefresh);

        /**
         * Show auto login fail
         */
        void showAutoLoginFail();

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get banner data
         *
         * @param isShowError If show error
         */
        void getBannerData(boolean isShowError);

        /**
         * Load main pager data
         */
        void loadMainPagerData();

        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        void getFeedArticleList(boolean isShowError);

        /**
         * Load more data
         */
        void loadMoreData();

        /**
         * Auto refresh
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);

        /**
         * Load more
         */
        void loadMore();
    }

}
