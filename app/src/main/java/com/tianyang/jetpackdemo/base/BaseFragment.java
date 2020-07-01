package com.tianyang.jetpackdemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/28.
 */
public abstract class BaseFragment<M extends BaseViewModel, VB extends ViewDataBinding> extends Fragment {
    protected VB mBinding;
    protected M mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), null, false);

        //initView需在使用mViewModel之前调用
        initView();

        mViewModel.failData.observe(this, throwable -> {
            if (throwable != null && throwable.getMessage() != null)
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
        return mBinding.getRoot();
    }

    abstract protected int getLayoutId();

    abstract protected void initView();
}
