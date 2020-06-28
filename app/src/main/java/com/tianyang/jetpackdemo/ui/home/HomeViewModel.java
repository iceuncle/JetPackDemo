package com.tianyang.jetpackdemo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    MutableLiveData<String> email = new MutableLiveData<>("aa");
    MutableLiveData<String> password = new MutableLiveData<>();
    MutableLiveData<String> text = new MutableLiveData<>();

    public LiveData<String> getText() {
        return text;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public HomeViewModel() {
        text.setValue("");
    }

    public void loginDataChanged() {
        text.setValue(email.getValue() + " " + password.getValue());
    }

}