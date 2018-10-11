package com.encdata.mvp.ui.article.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.encdata.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/2/24
 */

public class ArticleViewHolder extends BaseViewHolder {

    @BindView(R.id.item_search_pager_like_iv)
    ImageView mItemSearchPagerLikeIv;
    @BindView(R.id.item_search_pager_title)
    TextView mItemSearchPagerTitle;
    @BindView(R.id.item_search_pager_author)
    TextView mItemSearchPagerAuthor;
    @BindView(R.id.item_search_pager_tag_green_tv)
    TextView mTagGreenTv;
    @BindView(R.id.item_search_pager_tag_red_tv)
    TextView mTagRedTv;
    @BindView(R.id.item_search_pager_chapterName)
    TextView mItemSearchPagerChapterName;
    @BindView(R.id.item_search_pager_niceDate)
    TextView mItemSearchPagerNiceDate;

    public ArticleViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
