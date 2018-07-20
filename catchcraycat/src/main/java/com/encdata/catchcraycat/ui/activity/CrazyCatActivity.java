package com.encdata.catchcraycat.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.encdata.catchcraycat.ui.view.Playground;

/**
 * Created by zhaoyuejun on 2018/7/20.
 */

public class CrazyCatActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Playground(this));
    }
}
