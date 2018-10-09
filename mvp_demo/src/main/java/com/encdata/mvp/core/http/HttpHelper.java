package com.encdata.mvp.core.http;


import com.encdata.mvp.core.bean.BaseResponse;
import com.encdata.mvp.core.bean.FeedArticleListData;

import io.reactivex.Observable;

/**
 * @author quchao
 * @date 2017/11/27
 */

public interface HttpHelper {

    /**
     * 获取feed文章列表
     *
     * @param pageNum 页数
     * @return feed文章列表数据
     */
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum);

    /**
     * 获取搜索的文章列表
     *
     * @param pageNum 页数
     * @param k 关键字
     * @return 搜索的文章数据
     */
    Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k);

}
