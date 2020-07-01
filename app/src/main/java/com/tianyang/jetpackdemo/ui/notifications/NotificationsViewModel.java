package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tianyang.jetpackdemo.base.BaseViewModel;

public class NotificationsViewModel extends BaseViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>("This is notifications fragment");

    public LiveData<String> getText() {
        return mText;
    }
}