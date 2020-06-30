package com.tianyang.jetpackdemo.api;

import com.tianyang.jetpackdemo.model.Article;
import com.tianyang.jetpackdemo.model.BaseResponse;
import com.tianyang.jetpackdemo.model.PageData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetApi {
    @GET("/article/list/{page}/json")
    Observable<BaseResponse<PageData<Article>>> getArticles(@Path("page") String page);
}
