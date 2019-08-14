package com.encdata.dagger_demo.di;

import com.encdata.dagger_demo.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract MainActivity mainactivity();
}
