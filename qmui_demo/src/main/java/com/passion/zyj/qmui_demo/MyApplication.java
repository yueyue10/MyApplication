package com.passion.zyj.qmui_demo;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        QMUISwipeBackActivityManager.init(this);
    }

}
