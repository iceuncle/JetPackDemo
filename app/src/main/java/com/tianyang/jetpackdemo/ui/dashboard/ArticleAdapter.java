package com.tianyang.jetpackdemo.ui.dashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tianyang.jetpackdemo.model.Article;
import com.tianyang.jetpackdemo.databinding.ItemArticleBinding;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/30.
 */
public class ArticleAdapter extends PagedListAdapter<Article, ArticleAdapter.ViewHolder> {

    protected ArticleAdapter() {
        super(new DiffUtil.ItemCallback<Article>() {
            @Override
            public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                Log.d("http", "areItemsTheSame..." + (oldItem.id == newItem.id));
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                Log.d("http", "areContentsTheSame..." + (oldItem.equals(newItem)));
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleBinding binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article article = getItem(position);
        holder.bindData(article);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemArticleBinding mBinding;

        public ViewHolder(@NonNull View itemView, ItemArticleBinding binding) {
            super(itemView);
            this.mBinding = binding;
        }

        public void bindData(Article item) {
            mBinding.setArticle(item);
        }
    }

}
