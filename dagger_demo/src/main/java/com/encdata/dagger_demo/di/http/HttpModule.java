package com.encdata.dagger_demo.di.http;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {

    @Provides
    @Singleton
    DataManager provideDataManager(ApiService apiService) {
        return new DataManager(apiService);
    }

    @Singleton
    @Provides
    ApiService provideApiService() {
        return new ApiService("ApiServiceC");
    }
}
