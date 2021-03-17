package com.tianyang.jetpackdemo.api;

import com.tianyang.jetpackdemo.model.ArticleBean;
import com.tianyang.jetpackdemo.model.BaseResponse;
import com.tianyang.jetpackdemo.model.PageData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetService {
    @GET("/article/list/{page}/json")
    Observable<BaseResponse<PageData<ArticleBean>>> getArticles(@Path("page") String page);
}
