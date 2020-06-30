package com.tianyang.jetpackdemo.base;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/30.
 */
public abstract class PageViewModel<T> extends ViewModel {
    protected PagedList.Config config;
    private DataSource<Integer, T> dataSource;
    private LiveData<PagedList<T>> pageData;

    //是否有数据
    private MutableLiveData<Boolean> boundaryPageData = new MutableLiveData<>();

    public PageViewModel() {
        config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(22)
//                .setMaxSize(100)
//                .setEnablePlaceholders(false)
//                .setPrefetchDistance(10)
                .build();
        pageData = new LivePagedListBuilder<>(factory, config)
                .setInitialLoadKey(0)
                .setBoundaryCallback(callback)
                .build();
    }


    public LiveData<PagedList<T>> getPageData() {
        return pageData;
    }

    public DataSource<Integer, T> getDataSource() {
        return dataSource;
    }

    public LiveData<Boolean> getBoundaryPageData() {
        return boundaryPageData;
    }

    public void postBoundaryPageData(boolean value) {
        boundaryPageData.postValue(value);
    }

    PagedList.BoundaryCallback<T> callback = new PagedList.BoundaryCallback<T>() {
        @Override
        public void onZeroItemsLoaded() {
            //新提交的PagedList中没有数据
            Log.d("http", "onZeroItemsLoaded....");
            boundaryPageData.postValue(false);
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull T itemAtFront) {
            //新提交的PagedList中第一条数据被加载到列表上
            Log.d("http", "onItemAtFrontLoaded....");
            boundaryPageData.postValue(true);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            //新提交的PagedList中最后一条数据被加载到列表上
            Log.d("http", "onItemAtEndLoaded....");
        }
    };

    private DataSource.Factory<Integer, T> factory = new DataSource.Factory<Integer, T>() {
        @NonNull
        @Override
        public DataSource<Integer, T> create() {
            if (dataSource == null || dataSource.isInvalid()) {
                dataSource = createDataSource();
            }
            return dataSource;
        }
    };

    public abstract DataSource<Integer, T> createDataSource();
}
