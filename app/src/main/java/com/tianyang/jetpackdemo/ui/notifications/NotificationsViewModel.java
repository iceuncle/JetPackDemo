package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>("This is notifications fragment");

    public LiveData<String> getText() {
        return mText;
    }
}