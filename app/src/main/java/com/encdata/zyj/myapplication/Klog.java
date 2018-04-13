package com.encdata.zyj.myapplication;

import android.util.Log;

/**
 * Created by zhaoyuejun on 2018/4/12.
 */

public class Klog {

    static String Tag = "LOG------";

    public static void d(String out) {
        Log.d(Tag, out);
    }
}
