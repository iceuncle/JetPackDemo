package com.tianyang.jetpackdemo.repository;

import androidx.lifecycle.LiveData;

import com.tianyang.jetpackdemo.database.AppDataBase;
import com.tianyang.jetpackdemo.database.ArticleDao;
import com.tianyang.jetpackdemo.database.entity.Article;

import java.util.List;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
public class ArticleRepository {
    private ArticleDao articleDao;
    private LiveData<List<Article>> mAllArticles;

    private static volatile ArticleRepository instance;

    private ArticleRepository() {
        articleDao = AppDataBase.getDatabase().articleDao();
        mAllArticles = articleDao.getAllArticles();
    }

    public static ArticleRepository getInstance() {
        if (instance == null) {
            instance = new ArticleRepository();
        }
        return instance;
    }

    public LiveData<List<Article>> getAllArticles() {
        return mAllArticles;
    }

    public void insert(Article article) {
        AppDataBase.databaseWriteExecutor.execute(() -> articleDao.insert(article));
    }

}
