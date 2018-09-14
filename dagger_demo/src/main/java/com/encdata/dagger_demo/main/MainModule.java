package com.encdata.dagger_demo.main;


import com.encdata.dagger_demo.di.ActivityScoped;
import com.encdata.dagger_demo.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 第一步 添加@Module 注解
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract MainPresenter taskPresenter(MainPresenter presenter);

}
