package com.encdata.catchcraycat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.encdata.catchcraycat.ui.Fragment.LeftMenu;
import com.encdata.catchcraycat.ui.view.MainUI;

/**
 * Created by zhaoyuejun on 2018/7/20.
 */

public class CustomViewActivity extends FragmentActivity {

    private MainUI mainUI;
    private LeftMenu leftMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUI = new MainUI(this);
        setContentView(mainUI);
        leftMenu = new LeftMenu();
        getSupportFragmentManager().beginTransaction().add(MainUI.LEFT_ID, leftMenu).commit();
    }
}
