package com.tianyang.jetpackdemo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public void loginDataChanged(String username, String password) {
        mText.setValue(username + " " + password);
    }

    public LiveData<String> getText() {
        return mText;
    }
}