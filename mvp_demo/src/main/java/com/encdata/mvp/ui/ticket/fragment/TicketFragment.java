package com.encdata.mvp.ui.ticket.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.encdata.mvp.R;
import com.encdata.mvp.app.Constants;
import com.encdata.mvp.mvp.contract.TicketContract;
import com.encdata.mvp.mvp.fragment.BaseRootFragment;
import com.encdata.mvp.ui.ticket.storebus.StoreBusFragment;
import com.encdata.mvp.ui.ticket.workbus.WorkBusFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 购票模块
 * Created by zhaoyuejun on 2018/10/11.
 */

public class TicketFragment extends BaseRootFragment<TicketPresenter> implements TicketContract.View {

    @BindView(R.id.ticket_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.ticket_divider)
    View mDivider;
    @BindView(R.id.ticket_viewpager)
    ViewPager mViewPager;
    WorkBusFragment workBusFragment;
    StoreBusFragment storeBusFragment1;
    StoreBusFragment storeBusFragment2;
    StoreBusFragment storeBusFragment3;
    private List<Fragment> mFragments= new ArrayList<>();
    private String[] mData ={"上下班","收藏路线","企业班车","包车"};
    List<String> mDatas = Arrays.asList(mData);
    private int currentPage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ticket;
    }

    @Override
    protected void initView() {
        super.initView();
        initViewPagerAndTabLayout();
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
//        if (CommonUtils.isNetworkConnected()) {
//            showLoading();
//        }
        setChildViewVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        setChildViewVisibility(View.INVISIBLE);
        super.showError();
    }

    private void initViewPagerAndTabLayout() {
        workBusFragment= new WorkBusFragment();
        storeBusFragment1=new StoreBusFragment();
        storeBusFragment2=new StoreBusFragment();
        storeBusFragment3=new StoreBusFragment();

        mFragments.add(workBusFragment);
        mFragments.add(storeBusFragment1);
        mFragments.add(storeBusFragment2);
        mFragments.add(storeBusFragment3);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mDatas == null ? 0 : mDatas.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mDatas.get(position);
            }

        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setViewPager(mViewPager,mData);
        mViewPager.setCurrentItem(Constants.TAB_ONE);
        mViewPager.setOffscreenPageLimit(5);
    }

    private void setChildViewVisibility(int visibility) {
        mTabLayout.setVisibility(visibility);
        mDivider.setVisibility(visibility);
        mViewPager.setVisibility(visibility);
    }

}
