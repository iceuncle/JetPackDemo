package com.tianyang.jetpackdemo.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final FragmentDashboardBinding binding =
                DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_dashboard, null, false);
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        binding.setViewModel(dashboardViewModel);
        return binding.getRoot();
    }
}