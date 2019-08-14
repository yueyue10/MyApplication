package com.encdata.dagger_demo.di.http;

public class DataManager{
    private ApiService apiService;

    public DataManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }
}
