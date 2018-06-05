package com.example.greendao;

import android.app.Application;

import com.example.greendao.db.DbManager;
import com.socks.library.KLog;

/**
 * Created by zhaoyuejun on 2018/6/5.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbManager.getDaoMaster(this);
        KLog.init(BuildConfig.LOG_DEBUG, "Log---");
    }

}
