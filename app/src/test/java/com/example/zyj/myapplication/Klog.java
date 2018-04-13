package com.example.zyj.myapplication;

import android.util.Log;

/**
 * Created by zhaoyuejun on 2018/4/12.
 */

public class Klog {

    static String Tag = "LOG------";

    public static void p(String out) {
        System.out.print(Tag + out);
    }

    public static void pl(String out) {
        System.out.println(Tag + out);
    }
}
