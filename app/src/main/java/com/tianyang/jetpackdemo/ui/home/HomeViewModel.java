package com.tianyang.jetpackdemo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tianyang.jetpackdemo.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    MutableLiveData<String> email = new MutableLiveData<>("aa");
    MutableLiveData<String> password = new MutableLiveData<>();
    MutableLiveData<String> text = new MutableLiveData<>("");

    public HomeViewModel() {

    }

    public LiveData<String> getText() {
        return text;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }


    public void loginDataChanged() {
        text.setValue(email.getValue() + " " + password.getValue());
    }

}