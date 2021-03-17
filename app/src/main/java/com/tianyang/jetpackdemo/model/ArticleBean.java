package com.tianyang.jetpackdemo.model;

import androidx.databinding.BaseObservable;

import java.io.Serializable;
import java.util.Objects;

public class ArticleBean extends BaseObservable implements Serializable {

    public static final String KEY_AUTHOR = "author";
    public static final String KEY_TITLE = "title";

    public int id;
    public String author;
    public String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleBean article = (ArticleBean) o;
        return id == article.id &&
                Objects.equals(author, article.author) &&
                Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title);
    }
}
