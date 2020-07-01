package com.tianyang.jetpackdemo.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tianyang.jetpackdemo.database.entity.Article;
import com.tianyang.jetpackdemo.databinding.ItemArticleListBinding;

import java.util.List;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private List<Article> mArticles;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleListBinding binding = ItemArticleListBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ArticleListAdapter.ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article article = mArticles.get(position);
        holder.bindData(article);
    }

    @Override
    public int getItemCount() {
        if (mArticles != null)
            return mArticles.size();
        else return 0;
    }

    public void setArticles(List<Article> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemArticleListBinding mBinding;

        public ViewHolder(@NonNull View itemView, ItemArticleListBinding binding) {
            super(itemView);
            this.mBinding = binding;
        }

        public void bindData(Article item) {
            mBinding.setArticle(item);
        }
    }
}
