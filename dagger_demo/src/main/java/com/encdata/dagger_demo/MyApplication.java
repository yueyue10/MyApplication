package com.encdata.dagger_demo;


import com.encdata.dagger_demo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by zhaoyuejun on 2018/9/10.
 */

public class MyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().build();
    }
}
