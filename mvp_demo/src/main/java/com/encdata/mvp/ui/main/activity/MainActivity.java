package com.encdata.mvp.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.encdata.mvp.R;
import com.encdata.mvp.app.Constants;
import com.encdata.mvp.mvp.activity.BaseActivity;
import com.encdata.mvp.mvp.contract.MainContract;
import com.encdata.mvp.mvp.fragment.BaseFragment;
import com.encdata.mvp.ui.main.presenter.MainPresenter;
import com.encdata.mvp.ui.article.fragment.ArticleFragment;
import com.encdata.mvp.ui.ticket.fragment.TicketFragment;
import com.encdata.mvp.utils.StatusBarUtil;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.fragment_group)
    FrameLayout fragment_group;
    @BindView(R.id.tabbar)
    JPTabBar tabbar;
    private ArrayList<BaseFragment> mFragments;
    private TicketFragment ticketFragment;
    private ArticleFragment articleFragment2;
    private ArticleFragment articleFragment3;
    private int mLastFgIndex;

    @Titles
    private static final String[] mTitles = {"购票", "乘车", "我的"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.drawable.icon_tab_ticket_pre, R.drawable.icon_nav_bycar_press, R.drawable.icon_tab_my_pre};

    @NorIcons
    private static final int[] mNormalIcons = {R.drawable.icon_tab_ticket_default, R.drawable.icon_nav_bycar_default, R.drawable.icon_tab_my_default};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        assert actionBar != null;
//        actionBar.setDisplayShowTitleEnabled(false);
//        StatusBarUtil.setPaddingSmart(this, fragment_group);
//        mTitleTv.setText(getString(R.string.home_title));
//        mToolbar.setNavigationIcon(null);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.title_bar), 1f);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new ArrayList<>();
        if (savedInstanceState == null) {
            mPresenter.setNightModeState(false);
            initPager(false, Constants.TYPE_MAIN_PAGER);
        } else {
            initPager(true, Constants.TYPE_SETTING);
        }
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

    private void initPager(boolean isRecreate, int position) {
        ticketFragment= new TicketFragment();
        articleFragment2 = ArticleFragment.getInstance(isRecreate, null);
        articleFragment3 = ArticleFragment.getInstance(isRecreate, null);
        mFragments.add(ticketFragment);
        mFragments.add(articleFragment2);
        mFragments.add(articleFragment3);
        init();
        switchFragment(position);
    }

    private void init() {
        mPresenter.setCurrentPage(Constants.TYPE_MAIN_PAGER);
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        tabbar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int index) {
                switchFragment(index);
            }

            @Override
            public boolean onInterruptSelect(int index) {
                return false;
            }
        });
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commit();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }
}
