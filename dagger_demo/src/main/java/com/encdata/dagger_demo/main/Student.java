package com.encdata.dagger_demo.main;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/9/10.
 */
public class Student {
    private String name="老王";
    @Inject
    public Student() {

    }

    public String getName() {
        return name;
    }
}
