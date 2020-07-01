package com.tianyang.jetpackdemo.repository;

import com.tianyang.jetpackdemo.api.NetClient;
import com.tianyang.jetpackdemo.api.NetService;
import com.tianyang.jetpackdemo.model.Article;
import com.tianyang.jetpackdemo.model.BaseResponse;
import com.tianyang.jetpackdemo.model.PageData;

import io.reactivex.Observable;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
public class NetRepository {

    private static volatile NetRepository instance;

    private NetService netService;

    private NetRepository(NetService netService) {
        this.netService = netService;
    }

    public static NetRepository getInstance() {
        if (instance == null) {
            instance = new NetRepository(NetClient.create(NetService.class));
        }
        return instance;
    }

    public Observable<BaseResponse<PageData<Article>>> getArticles(int page) {
        return netService.getArticles(String.valueOf(page));
    }


}
