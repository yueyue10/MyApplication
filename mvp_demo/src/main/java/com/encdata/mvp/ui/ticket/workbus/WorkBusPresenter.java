package com.encdata.mvp.ui.ticket.workbus;

import android.support.annotation.NonNull;

import com.encdata.mvp.R;
import com.encdata.mvp.app.Constants;
import com.encdata.mvp.app.MyApplication;
import com.encdata.mvp.core.DataManager;
import com.encdata.mvp.core.bean.BaseResponse;
import com.encdata.mvp.core.bean.FeedArticleListData;
import com.encdata.mvp.core.bean.WorkBusListData;
import com.encdata.mvp.mvp.contract.WorkBusContract;
import com.encdata.mvp.mvp.presenter.BasePresenter;
import com.encdata.mvp.ui.article.bean.BannerData;
import com.encdata.mvp.utils.BaseObserver;
import com.encdata.mvp.utils.CommonUtils;
import com.encdata.mvp.utils.RxUtils;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by zhaoyuejun on 2018/10/12.
 */

public class WorkBusPresenter extends BasePresenter<WorkBusContract.View> implements WorkBusContract.Presenter {

    private DataManager mDataManager;
    private boolean isRefresh = true;
    private int mCurrentPage;

    @Inject
    public WorkBusPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void loadData() {
        Observable<BaseResponse<List<BannerData>>> mBannerObservable = mDataManager.getBannerDatas();
        Observable<BaseResponse<WorkBusListData>> mWorkBusObservable = mDataManager.getWorkBusList(0);
        addSubscribe(Observable.zip(mBannerObservable, mWorkBusObservable, this::createResponseMap)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {
                        BaseResponse<List<BannerData>> bannerResponse = CommonUtils.cast(map.get(Constants.BANNER_DATA));
                        if (bannerResponse != null) {
                            mView.showBannerData(bannerResponse.getData());
                        }
                        BaseResponse<WorkBusListData> workBusListDataResponse = CommonUtils.cast(map.get(Constants.WORK_BUS_DATA));
                        if (workBusListDataResponse != null) {
                            mView.showWorkBusList(workBusListDataResponse.getData(), isRefresh);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                }));
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getBannerData(isShowError);
        getWorkBusList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void getBannerData(boolean isShowError) {
        addSubscribe(mDataManager.getBannerDatas()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,
                        MyApplication.getInstance().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onNext(List<BannerData> bannerDataList) {
                        mView.showBannerData(bannerDataList);
                    }
                }));
    }

    @Override
    public void getWorkBusList(boolean isShowError) {
        addSubscribe(mDataManager.getWorkBusList(mCurrentPage)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(workBusListData -> mView != null)
                .subscribeWith(new BaseObserver<WorkBusListData>(mView,
                        MyApplication.getInstance().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onNext(WorkBusListData workBusListData) {
                        mView.showWorkBusList(workBusListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getWorkBusList(mCurrentPage)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(workBusListData -> mView != null)
                .subscribeWith(new BaseObserver<WorkBusListData>(mView,
                        MyApplication.getInstance().getString(R.string.failed_to_obtain_article_list),
                        false) {
                    @Override
                    public void onNext(WorkBusListData workBusListData) {
                        mView.showWorkBusList(workBusListData, isRefresh);
                    }
                }));
    }

    @NonNull
    private HashMap<String, Object> createResponseMap(BaseResponse<List<BannerData>> bannerResponse,
                                                      BaseResponse<WorkBusListData> workBusListDataBaseResponse) {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put(Constants.BANNER_DATA, bannerResponse);
        map.put(Constants.WORK_BUS_DATA, workBusListDataBaseResponse);
        return map;
    }
}
