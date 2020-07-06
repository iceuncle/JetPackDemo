package com.tianyang.jetpackdemo.ui.home;

import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.BaseFragment;
import com.tianyang.jetpackdemo.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mBinding.setViewmodel(mViewModel);
        mBinding.setLifecycleOwner(this);

//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                mViewModel.loginDataChanged();
//            }
//        };
//
//        mBinding.username.addTextChangedListener(afterTextChangedListener);
//        mBinding.password.addTextChangedListener(afterTextChangedListener);


//        mViewModel.email.observe(this, s -> mViewModel.loginDataChanged());
        mViewModel.mapEmail.observe(this, s -> mViewModel.loginDataChanged());
        mViewModel.password.observe(this, s -> mViewModel.loginDataChanged());

        mViewModel.enableLogin.observe(this, bol -> Log.d("HomeFragment", "enable " + bol));
    }
}