package com.tianyang.jetpackdemo.model;

import java.util.List;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/30.
 */
public class PageData<T> {
    public int curPage;
    public List<T> datas;
    public int offset;
    public boolean over;
    public int size;
    public int total;
}
