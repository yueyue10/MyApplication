package com.encdata.mvp.ui.main;

import com.encdata.mvp.R;
import com.encdata.mvp.mvp.activity.BaseActivity;
import com.encdata.mvp.mvp.contract.MainContract;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @Titles
    private static final String[] mTitles = {"购票", "乘车", "我的"};

    @SeleIcons
    private static final int[] mSeleIcons = { R.drawable.icon_tab_ticket_pre,R.drawable.icon_nav_bycar_press, R.drawable.icon_tab_my_pre};

    @NorIcons
    private static final int[] mNormalIcons = { R.drawable.icon_tab_ticket_default,R.drawable.icon_nav_bycar_default, R.drawable.icon_tab_my_default};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchNavigation() {

    }

    @Override
    public void showAutoLoginView() {

    }
}
