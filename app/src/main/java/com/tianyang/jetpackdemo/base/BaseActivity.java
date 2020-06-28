package com.tianyang.jetpackdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/28.
 */
public abstract class BaseActivity<VB extends ViewDataBinding> extends AppCompatActivity {
    protected VB mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initView();
    }

    abstract protected int getLayoutId();

    abstract protected void initView();

}
