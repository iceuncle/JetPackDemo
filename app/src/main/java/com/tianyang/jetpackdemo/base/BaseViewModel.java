package com.tianyang.jetpackdemo.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
public class BaseViewModel extends ViewModel {

    public MutableLiveData<Throwable> failData = new MutableLiveData<>();

}
