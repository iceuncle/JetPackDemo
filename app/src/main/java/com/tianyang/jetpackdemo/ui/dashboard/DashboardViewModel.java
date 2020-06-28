package com.tianyang.jetpackdemo.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>("This is dashboard fragment");

    public LiveData<String> getText() {
        return mText;
    }
}