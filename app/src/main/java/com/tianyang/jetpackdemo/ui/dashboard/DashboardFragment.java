package com.tianyang.jetpackdemo.ui.dashboard;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.PageFragment;
import com.tianyang.jetpackdemo.databinding.FragmentDashboardBinding;
import com.tianyang.jetpackdemo.model.Article;

public class DashboardFragment extends PageFragment<Article, DashboardViewModel, FragmentDashboardBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initDetailView() {
    }

    @Override
    protected RecyclerView provideRecyclerView() {
        return mBinding.recyclerView;
    }

    @Override
    protected SmartRefreshLayout provideRefreshLayout() {
        return mBinding.refreshLayout;
    }

    @Override
    protected View provideEmptyView() {
        return mBinding.emptyView;
    }

    @Override
    public PagedListAdapter getAdapter() {
        return new ArticleAdapter();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mViewModel.getDataSource().invalidate();
    }

}