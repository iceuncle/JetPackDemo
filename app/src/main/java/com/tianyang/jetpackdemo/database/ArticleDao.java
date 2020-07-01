package com.tianyang.jetpackdemo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tianyang.jetpackdemo.database.entity.Article;

import java.util.List;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article article);

    @Query("select * from article where `id`=:id")
    Article getArticle(String id);

    @Delete
    void delete(Article article);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Article article);

    //使用LiveData返回，数据库数据改动时，会自动更新LiveData
    @Query("SELECT * from article ORDER BY id ASC")
    LiveData<List<Article>> getAllArticles();

    @Query("DELETE FROM article")
    void deleteAll();
}
