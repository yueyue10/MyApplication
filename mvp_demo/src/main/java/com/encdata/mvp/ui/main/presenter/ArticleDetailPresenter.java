package com.encdata.mvp.ui.main.presenter;

import com.encdata.mvp.core.DataManager;
import com.encdata.mvp.mvp.contract.ArticleDetailContract;
import com.encdata.mvp.mvp.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * @author quchao
 * @date 2017/11/28
 */

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ArticleDetailContract.View view) {
        super.attachView(view);
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }
}
