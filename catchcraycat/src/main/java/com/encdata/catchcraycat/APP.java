package com.encdata.catchcraycat;

import android.app.Application;

import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by zhaoyuejun on 2018/7/19.
 */

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(true, "KLog");
        CrashReport.initCrashReport(getApplicationContext(), "5c3a140cbf", true);
    }
}
