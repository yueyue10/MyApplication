package com.encdata.dagger_demo.di.http;

public class ApiService {
    private String name;

    public ApiService(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
