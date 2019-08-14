package com.encdata.dagger_demo.di;

import com.encdata.dagger_demo.MyApplication;
import com.encdata.dagger_demo.di.http.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        HttpModule.class,
        ActivityModule.class,
        AndroidSupportInjectionModule.class,})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        AppComponent build();
    }
}
