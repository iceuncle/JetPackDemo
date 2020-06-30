package com.tianyang.jetpackdemo.model;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/30.
 */
public class BaseResponse<T> {
    public T data;
    public int errorCode;
    public String errorMsg;
}
