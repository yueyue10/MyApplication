package com.encdata.dagger_demo.main;

import com.encdata.dagger_demo.di.ActivityScoped;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/9/10.
 */
@ActivityScoped
public class Persion {

    private String type;

    @Inject
    public Persion() {

    }
}
