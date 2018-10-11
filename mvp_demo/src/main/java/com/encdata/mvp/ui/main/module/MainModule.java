package com.encdata.mvp.ui.main.module;


import com.encdata.mvp.di.scope.FragmentScoped;
import com.encdata.mvp.ui.article.fragment.ArticleFragment;

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
    abstract ArticleFragment ticketFragment();

//    @ActivityScoped
//    @Binds
//    abstract MainPresenter taskPresenter(MainPresenter presenter);

}
