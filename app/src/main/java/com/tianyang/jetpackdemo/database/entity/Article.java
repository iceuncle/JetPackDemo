package com.tianyang.jetpackdemo.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                Objects.equals(author, article.author) &&
                Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title);
    }
}
