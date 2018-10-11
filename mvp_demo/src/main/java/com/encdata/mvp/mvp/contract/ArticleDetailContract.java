package com.encdata.mvp.mvp.contract;


import com.encdata.mvp.mvp.presenter.AbstractPresenter;
import com.encdata.mvp.mvp.view.AbstractView;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface ArticleDetailContract {

    interface View extends AbstractView {


    }

    interface Presenter extends AbstractPresenter<View> {
        /**
         * Get auto cache state
         *
         * @return if auto cache state
         */
        boolean getAutoCacheState();

        /**
         * Get no image state
         *
         * @return if has image state
         */
        boolean getNoImageState();
    }

}
