package com.encdata.zyj.myapplication.lombok;

import com.encdata.zyj.myapplication.Klog;

import lombok.SneakyThrows;

/**
 * 【Lombok】lombok使用教程
 * https://blog.csdn.net/u011719271/article/details/53842420
 */

public class Demo2 {

    @SneakyThrows
    public void test() {
        String s = new String("test".getBytes(), "utf-8");
        Klog.d(s);
    }
}
