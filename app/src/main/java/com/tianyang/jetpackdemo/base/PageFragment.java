package com.tianyang.jetpackdemo.base;

import android.util.Log;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/30.
 */
public abstract class PageFragment<T, M extends PageViewModel<T>, VB extends ViewDataBinding>
        extends BaseFragment<VB> implements OnRefreshListener {

    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;
    protected View mEmptyView;
    protected M mViewModel;
    protected PagedListAdapter<T, RecyclerView.ViewHolder> adapter;

    @Override
    protected void initView() {
        mRecyclerView = provideRecyclerView();
        mRefreshLayout = provideRefreshLayout();
        mEmptyView = provideEmptyView();

        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(this);

        adapter = getAdapter();
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(null);

        genericViewModel();
        initDetailView();
    }

    protected abstract void initDetailView();

    protected abstract RecyclerView provideRecyclerView();

    protected abstract SmartRefreshLayout provideRefreshLayout();

    protected abstract View provideEmptyView();

    private void genericViewModel() {
        //利用 子类传递的 泛型参数实例化出absViewModel 对象。
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] arguments = new Type[0];
        if (type != null) {
            arguments = type.getActualTypeArguments();
        }
        if (arguments.length > 1) {
            Type argument = arguments[1];
            Class modelClaz = ((Class) argument).asSubclass(PageViewModel.class);
            mViewModel = (M) ViewModelProviders.of(this).get(modelClaz);

            //触发页面初始化数据加载的逻辑
            mViewModel.getPageData().observe(this, this::submitList);

            //监听分页时有无更多数据,以决定是否关闭上拉加载的动画
            mViewModel.getBoundaryPageData().observe(this, this::finishRefresh);
        }
    }


    public void submitList(PagedList<T> result) {
        Log.d("http", "result...size" + result.size());
        adapter.submitList(result);
    }

    public void finishRefresh(boolean hasData) {
        RefreshState state = mRefreshLayout.getState();
        if (state.isFooter && state.isOpening) {
            mRefreshLayout.finishLoadMore();
        } else if (state.isHeader && state.isOpening) {
            mRefreshLayout.finishRefresh();
        }

        PagedList<T> currentList = adapter.getCurrentList();
        hasData = hasData || currentList != null && currentList.size() > 0;
        Log.d("http", "hasData..." + hasData);
        if (hasData) {
            mEmptyView.setVisibility(View.GONE);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }


    public abstract PagedListAdapter<T, RecyclerView.ViewHolder> getAdapter();
}
