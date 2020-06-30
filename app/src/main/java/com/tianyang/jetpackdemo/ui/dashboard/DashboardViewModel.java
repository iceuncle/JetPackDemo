package com.tianyang.jetpackdemo.ui.dashboard;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;

import com.tianyang.jetpackdemo.model.Article;
import com.tianyang.jetpackdemo.model.BaseResponse;
import com.tianyang.jetpackdemo.model.PageData;
import com.tianyang.jetpackdemo.api.NetApi;
import com.tianyang.jetpackdemo.api.NetClient;
import com.tianyang.jetpackdemo.base.PageViewModel;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends PageViewModel<Article> {


    @Override
    public DataSource<Integer, Article> createDataSource() {
        return new PageDataSource();
    }


    //普通分页
    class PageDataSource extends PageKeyedDataSource<Integer, Article> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Article> callback) {
            loadData(0, callback, null);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {
            loadData(params.key + 1, null, callback);
        }
    }

    private void loadData(int page, PageKeyedDataSource.LoadInitialCallback<Integer, Article> initialCallback,
                          PageKeyedDataSource.LoadCallback<Integer, Article> callback) {
        Log.d("http", "page..." + page);
        NetClient.create(NetApi.class)
                .getArticles(String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<PageData<Article>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<PageData<Article>> pageDataBaseResponse) {
                        if (page == 5) {
                            //模拟第一次加载没有数据
                            pageDataBaseResponse.data.datas = new ArrayList<>();
                        }
                        if (initialCallback != null) {
                            initialCallback.onResult(pageDataBaseResponse.data.datas, -1, 0);
                        } else {
                            callback.onResult(pageDataBaseResponse.data.datas, page);
                        }
                        if (page > 0) {
                            postBoundaryPageData(pageDataBaseResponse.data.datas.size() > 0);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        postBoundaryPageData(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //Feeds流选用ItemKeyedDataSource
    class FeedDataSource extends ItemKeyedDataSource<Integer, Article> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Article> callback) {
            //加载初始化数据的
            loadData(0, params.requestedLoadSize, callback);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Article> callback) {
            //向后加载分页数据的
            loadData(params.key, params.requestedLoadSize, callback);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Article> callback) {
            //能够向前加载数据的
            callback.onResult(Collections.emptyList());
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull Article item) {
            return item.id;
        }
    }

    //feeds流
    private void loadData(int page, int pageSize, ItemKeyedDataSource.LoadCallback<Article> callback) {
        Log.d("http", "page..." + page);
        NetClient.create(NetApi.class)
                .getArticles(String.valueOf(page))
                .subscribe(new Observer<BaseResponse<PageData<Article>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<PageData<Article>> pageDataBaseResponse) {
                        callback.onResult(pageDataBaseResponse.data.datas);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}