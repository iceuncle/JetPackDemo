package com.tianyang.jetpackdemo.ui.home;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.tianyang.jetpackdemo.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    MutableLiveData<String> email = new MutableLiveData<>("aa");
    MutableLiveData<String> password = new MutableLiveData<>();

    LiveData<String> mapEmail = Transformations.map(email, input -> {
        if (input.equals("aaa"))
            return "bbb";
        return input;
    });

    LiveData<String> switchMapEmail = Transformations.switchMap(email, this::getEmai);

    MediatorLiveData<Boolean> enableLogin = new MediatorLiveData<>();
    Observer<String> observer;

    public HomeViewModel() {
        observer = s -> {
            if (TextUtils.isEmpty(email.getValue()) || TextUtils.isEmpty(password.getValue())) {
                enableLogin.postValue(false);
            } else {
                enableLogin.postValue(true);
            }
        };
        enableLogin.addSource(email, observer);
        enableLogin.addSource(password, observer);
    }


    private LiveData<String> getEmai(String input) {
        if (input.equals("aaa"))
            return new MutableLiveData<>("bbb");
        return new MutableLiveData<>(input);
    }

    MutableLiveData<String> text = new MutableLiveData<>("");

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
//        text.setValue(email.getValue() + " " + password.getValue());
        text.setValue(mapEmail.getValue() + " " + password.getValue());
    }

}