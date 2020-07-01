package com.tianyang.jetpackdemo.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;

import com.tianyang.jetpackdemo.api.BaseObserver;
import com.tianyang.jetpackdemo.api.NetClient;
import com.tianyang.jetpackdemo.api.NetService;
import com.tianyang.jetpackdemo.repository.NetRepository;
import com.tianyang.jetpackdemo.base.PageViewModel;
import com.tianyang.jetpackdemo.model.Article;
import com.tianyang.jetpackdemo.model.BaseResponse;
import com.tianyang.jetpackdemo.model.PageData;

import java.util.Collections;

public class DashboardViewModel extends PageViewModel<Article> {

    private NetRepository netRepository = NetRepository.getInstance();


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
        netRepository.getArticles(page)
                .subscribe(new BaseObserver<BaseResponse<PageData<Article>>>() {
                    @Override
                    public void onSuccess(BaseResponse<PageData<Article>> pageDataBaseResponse) {
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
                    public void onFailure(Throwable e) {
                        failData.postValue(e);
                        postBoundaryPageData(false);
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
        NetClient.create(NetService.class)
                .getArticles(String.valueOf(page))
                .subscribe(new BaseObserver<BaseResponse<PageData<Article>>>() {
                    @Override
                    public void onSuccess(BaseResponse<PageData<Article>> pageDataBaseResponse) {
                        callback.onResult(pageDataBaseResponse.data.datas);
                        if (page > 0) {
                            postBoundaryPageData(pageDataBaseResponse.data.datas.size() > 0);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        failData.postValue(e);
                        postBoundaryPageData(false);
                    }
                });
    }
}