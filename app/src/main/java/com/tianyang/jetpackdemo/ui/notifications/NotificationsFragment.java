package com.tianyang.jetpackdemo.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.databinding.FragmentDashboardBinding;
import com.tianyang.jetpackdemo.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final FragmentNotificationsBinding binding =
                DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_notifications, null, false);
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        binding.setViewModel(notificationsViewModel);
        return binding.getRoot();
    }
}