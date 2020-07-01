package com.tianyang.jetpackdemo.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
@Entity(tableName = "article")
public class Article {

    @PrimaryKey
    @NonNull
    public int id;

    public String author;

    public String title;

}
