package com.encdata.mvp.mvp.contract;

import com.encdata.mvp.mvp.presenter.AbstractPresenter;
import com.encdata.mvp.mvp.view.AbstractView;

/**
 * Created by zhaoyuejun on 2018/10/11.
 */

public interface TicketContract {
    interface View extends AbstractView{

    }
    interface Presenter extends AbstractPresenter<View>{


    }
}
