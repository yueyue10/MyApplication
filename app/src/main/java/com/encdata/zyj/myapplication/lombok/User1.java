package com.encdata.zyj.myapplication.lombok;

/**
 * Created by zhaoyuejun on 2018/4/12.
 */

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @RequiredArgsConstructor会生成构造方法（可能带参数也可能不带参数）， 如果带参数，这参数只能是以final修饰的未经初始化的字段，或者是以@NonNull注解的未经初始化的字段
 * @RequiredArgsConstructor(staticName = "of")会生成一个of()的静态方法，并把构造方法设置为私有的
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor()
@ToString(exclude = {"name"}, callSuper = true)
public class User1 {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private final String phone;
}