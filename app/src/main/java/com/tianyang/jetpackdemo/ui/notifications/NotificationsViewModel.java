package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.LiveData;

import com.tianyang.jetpackdemo.base.BaseViewModel;
import com.tianyang.jetpackdemo.database.entity.Article;
import com.tianyang.jetpackdemo.repository.ArticleRepository;

import java.util.List;

public class NotificationsViewModel extends BaseViewModel {

    private LiveData<List<Article>> mAllArticles;

    private ArticleRepository mRepository = ArticleRepository.getInstance();

    public NotificationsViewModel() {
        mAllArticles = mRepository.getAllArticles();
    }

    public LiveData<List<Article>> getAllArticles() {
        return mAllArticles;
    }

    void insert(Article article) {
        mRepository.insert(article);
    }

}