package com.encdata.mvp.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.encdata.mvp.BuildConfig;
import com.encdata.mvp.R;
import com.encdata.mvp.core.dao.DaoMaster;
import com.encdata.mvp.core.dao.DaoSession;
import com.encdata.mvp.di.component.DaggerAppComponent;
import com.encdata.mvp.utils.CommonUtils;
import com.encdata.mvp.utils.logger.TxtFormatStrategy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author quchao
 * @date 2017/11/27
 */
public class MyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    private static MyApplication instance;
    private RefWatcher refWatcher;
    public static boolean isFirstRun = true;
    private DaoSession mDaoSession;

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, refreshLayout) -> {
            //全局设置主题颜色
            refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //指定为Delivery Header，默认是贝塞尔雷达Header
            return new DeliveryHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //默认是 BallPulseFooter
            return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
        });
    }


    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initGreenDao();

        initBugly();

        initLogger();

        if (BuildConfig.DEBUG) {
            //Stetho.initializeWithDefaults(this);
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void initLogger() {
        //DEBUG版本才打控制台log
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().
                    tag(getString(R.string.app_name)).build()));
        }
        //把log存到本地
        Logger.addLogAdapter(new DiskLogAdapter(TxtFormatStrategy.newBuilder().
                tag(getString(R.string.app_name)).build(getPackageName(), getString(R.string.app_name))));
    }

    private void initBugly() {
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_ID, false, strategy);
    }

}
