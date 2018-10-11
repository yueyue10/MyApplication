package com.encdata.mvp.ui.article.adapter;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.encdata.mvp.R;
import com.encdata.mvp.core.bean.FeedArticleData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/2/24
 */

public class ArticleListAdapter extends BaseQuickAdapter<FeedArticleData, ArticleViewHolder> {

    private boolean isCollectPage;
    private boolean isSearchPage;
    private boolean isNightMode;

    public ArticleListAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    public void isCollectPage() {
        isCollectPage = true;
        notifyDataSetChanged();
    }

    public void isSearchPage() {
        isSearchPage = true;
        notifyDataSetChanged();
    }

    public void isNightMode(boolean isNightMode) {
        this.isNightMode = isNightMode;
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(ArticleViewHolder helper, FeedArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            helper.setText(R.id.item_search_pager_title, Html.fromHtml(article.getTitle()));
        }
        if (article.isCollect() || isCollectPage) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            helper.setText(R.id.item_search_pager_author, article.getAuthor());
        }
        setTag(helper, article);
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
            if (isCollectPage) {
                helper.setText(R.id.item_search_pager_chapterName, article.getChapterName());
            } else {
                helper.setText(R.id.item_search_pager_chapterName, classifyName);
            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            helper.setText(R.id.item_search_pager_niceDate, article.getNiceDate());
        }
        if (isSearchPage) {
            CardView cardView = helper.getView(R.id.item_search_pager_group);
            cardView.setForeground(null);
            if (isNightMode) {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
            } else {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
            }
        }

        helper.addOnClickListener(R.id.item_search_pager_chapterName);
        helper.addOnClickListener(R.id.item_search_pager_like_iv);
        helper.addOnClickListener(R.id.item_search_pager_tag_red_tv);
    }

    private void setTag(ArticleViewHolder helper, FeedArticleData article) {
        helper.getView(R.id.item_search_pager_tag_green_tv).setVisibility(View.GONE);
        helper.getView(R.id.item_search_pager_tag_red_tv).setVisibility(View.GONE);
        if (isCollectPage) {
            return;
        }
        if (article.getSuperChapterName().contains(mContext.getString(R.string.open_project))) {
            setRedTag(helper, R.string.project);
        }

        if (article.getSuperChapterName().contains(mContext.getString(R.string.navigation))) {
           setRedTag(helper, R.string.navigation);
        }

        if (article.getNiceDate().contains(mContext.getString(R.string.minute))
                || article.getNiceDate().contains(mContext.getString(R.string.hour))
                || article.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.getView(R.id.item_search_pager_tag_green_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_green_tv, R.string.text_new);
            helper.setTextColor(R.id.item_search_pager_tag_green_tv, ContextCompat.getColor(mContext, R.color.light_green));
            helper.setBackgroundRes(R.id.item_search_pager_tag_green_tv, R.drawable.shape_tag_green_background);
        }
    }

    private void setRedTag(ArticleViewHolder helper, @StringRes int tagName) {
        helper.getView(R.id.item_search_pager_tag_red_tv).setVisibility(View.VISIBLE);
        helper.setText(R.id.item_search_pager_tag_red_tv, tagName);
        helper.setTextColor(R.id.item_search_pager_tag_red_tv, ContextCompat.getColor(mContext, R.color.light_deep_red));
        helper.setBackgroundRes(R.id.item_search_pager_tag_red_tv, R.drawable.selector_tag_red_background);
    }

}
