package com.tianyang.jetpackdemo.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;

import com.tianyang.jetpackdemo.api.BaseObserver;
import com.tianyang.jetpackdemo.api.NetClient;
import com.tianyang.jetpackdemo.api.NetService;
import com.tianyang.jetpackdemo.model.ArticleBean;
import com.tianyang.jetpackdemo.repository.NetRepository;
import com.tianyang.jetpackdemo.base.PageViewModel;
import com.tianyang.jetpackdemo.model.BaseResponse;
import com.tianyang.jetpackdemo.model.PageData;

import java.util.Collections;

public class DashboardViewModel extends PageViewModel<ArticleBean> {

    private NetRepository netRepository = NetRepository.getInstance();


    @Override
    public DataSource<Integer, ArticleBean> createDataSource() {
        return new PageDataSource();
    }


    //普通分页
    class PageDataSource extends PageKeyedDataSource<Integer, ArticleBean> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ArticleBean> callback) {
            loadData(0, callback, null);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticleBean> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticleBean> callback) {
            loadData(params.key + 1, null, callback);
        }
    }

    private void loadData(int page, PageKeyedDataSource.LoadInitialCallback<Integer, ArticleBean> initialCallback,
                          PageKeyedDataSource.LoadCallback<Integer, ArticleBean> callback) {
        netRepository.getArticles(page)
                .subscribe(new BaseObserver<BaseResponse<PageData<ArticleBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<PageData<ArticleBean>> pageDataBaseResponse) {
                        if (initialCallback != null) {
                            initialCallback.onResult(pageDataBaseResponse.data.datas, -1, 0);
                        } else {
                            callback.onResult(pageDataBaseResponse.data.datas, page);
                        }
                        postBoundaryPageData(pageDataBaseResponse.data.datas.size() > 0);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        failData.postValue(e);
                        postBoundaryPageData(false);
                    }
                });

    }


    //Feeds流选用ItemKeyedDataSource
    class FeedDataSource extends ItemKeyedDataSource<Integer, ArticleBean> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<ArticleBean> callback) {
            //加载初始化数据的
            loadData(0, params.requestedLoadSize, callback);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ArticleBean> callback) {
            //向后加载分页数据的
            loadData(params.key, params.requestedLoadSize, callback);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ArticleBean> callback) {
            //能够向前加载数据的
            callback.onResult(Collections.emptyList());
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull ArticleBean item) {
            return item.id;
        }
    }

    //feeds流
    private void loadData(int page, int pageSize, ItemKeyedDataSource.LoadCallback<ArticleBean> callback) {
        NetClient.create(NetService.class)
                .getArticles(String.valueOf(page))
                .subscribe(new BaseObserver<BaseResponse<PageData<ArticleBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<PageData<ArticleBean>> pageDataBaseResponse) {
                        callback.onResult(pageDataBaseResponse.data.datas);
                        postBoundaryPageData(pageDataBaseResponse.data.datas.size() > 0);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        failData.postValue(e);
                        postBoundaryPageData(false);
                    }
                });
    }
}