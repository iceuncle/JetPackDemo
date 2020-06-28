package com.tianyang.jetpackdemo.ui.dashboard;

import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.BaseFragment;
import com.tianyang.jetpackdemo.databinding.FragmentDashboardBinding;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> {

    private DashboardViewModel dashboardViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initView() {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        mBinding.setViewModel(dashboardViewModel);
    }
}