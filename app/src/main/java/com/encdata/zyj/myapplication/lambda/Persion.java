package com.encdata.zyj.myapplication.lambda;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by zhaoyuejun on 2018/4/12.
 */
@Data
public class Persion {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private int age;
}
