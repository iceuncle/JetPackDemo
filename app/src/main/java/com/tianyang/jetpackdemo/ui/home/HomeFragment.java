package com.tianyang.jetpackdemo.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final FragmentHomeBinding binding =
                DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_home, null, false);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setViewmodel(homeViewModel);
        binding.setLifecycleOwner(this);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                homeViewModel.loginDataChanged();
            }
        };

        binding.username.addTextChangedListener(afterTextChangedListener);
        binding.password.addTextChangedListener(afterTextChangedListener);
        return binding.getRoot();
    }
}