package com.encdata.mvp.di.module;


import com.encdata.mvp.app.MyApplication;
import com.encdata.mvp.core.DataManager;
import com.encdata.mvp.core.db.DbHelper;
import com.encdata.mvp.core.db.DbHelperImpl;
import com.encdata.mvp.core.http.HttpHelper;
import com.encdata.mvp.core.http.HttpHelperImpl;
import com.encdata.mvp.core.prefs.PreferenceHelper;
import com.encdata.mvp.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DbHelper provideDBHelper(DbHelperImpl realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbhelper, PreferenceHelper preferencesHelper) {
        return new DataManager(httpHelper, dbhelper, preferencesHelper);
    }

}
