package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.BaseFragment;
import com.tianyang.jetpackdemo.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends BaseFragment<FragmentNotificationsBinding> {

    private NotificationsViewModel notificationsViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected void initView() {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        mBinding.setViewModel(notificationsViewModel);
    }
}