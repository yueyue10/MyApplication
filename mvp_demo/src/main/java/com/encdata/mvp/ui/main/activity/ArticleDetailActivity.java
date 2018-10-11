package com.encdata.mvp.ui.main.activity;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.encdata.mvp.R;
import com.encdata.mvp.app.Constants;
import com.encdata.mvp.mvp.activity.BaseRootActivity;
import com.encdata.mvp.mvp.contract.ArticleDetailContract;
import com.encdata.mvp.ui.main.presenter.ArticleDetailPresenter;
import com.encdata.mvp.utils.CommonUtils;
import com.encdata.mvp.utils.StatusBarUtil;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * Created by zhaoyuejun on 2018/10/9.
 */

public class ArticleDetailActivity extends BaseRootActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {

    @BindView(R.id.article_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout mWebContent;
    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolbar() {
        mToolbar.setTitle(Html.fromHtml(getIntent().getStringExtra(Constants.ARTICLE_TITLE)));
        setSupportActionBar(mToolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.error_view, -1)
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra(Constants.ARTICLE_LINK));

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();
        if (mPresenter.getNoImageState()) {
            mSettings.setBlockNetworkImage(true);
        } else {
            mSettings.setBlockNetworkImage(false);
        }
        if (mPresenter.getAutoCacheState()) {
            mSettings.setAppCacheEnabled(true);
            mSettings.setDomStorageEnabled(true);
            mSettings.setDatabaseEnabled(true);
            if (CommonUtils.isNetworkConnected()) {
                mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        } else {
            mSettings.setAppCacheEnabled(false);
            mSettings.setDomStorageEnabled(false);
            mSettings.setDatabaseEnabled(false);
            mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            supportFinishAfterTransition();
        }
    }
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
