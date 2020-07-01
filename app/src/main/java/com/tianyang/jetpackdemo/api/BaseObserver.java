package com.tianyang.jetpackdemo.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e == null) return;
        String msg = e.getMessage();
        if (isHttpError(msg)) {
            onFailure(new Throwable("network error"));
        } else if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                default:
                    onFailure(e);
            }
        } else {
            onFailure(e);
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable e);

    private boolean isHttpError(String msg) {
        String[] errorArray = new String[]{
                "timeout",
                "java.net.ConnectException",
                "java.net.SocketTimeoutException",
                "failed",
                "Failed to connect to",
                "stream was reset",
                "Unable to resolve host",
                "SSL handshake time out",
                "time out"
        };
        for (String error : errorArray) {
            if (msg.contains(error))
                return true;
        }
        return false;
    }
}
