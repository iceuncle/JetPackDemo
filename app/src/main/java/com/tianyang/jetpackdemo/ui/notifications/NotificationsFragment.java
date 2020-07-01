package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.BaseFragment;
import com.tianyang.jetpackdemo.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends BaseFragment<NotificationsViewModel, FragmentNotificationsBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected void initView() {
        mViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        mBinding.setViewModel(mViewModel);
    }
}