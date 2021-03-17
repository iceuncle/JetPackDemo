package com.tianyang.jetpackdemo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tianyang.jetpackdemo.databinding.ItemArticleBinding;
import com.tianyang.jetpackdemo.model.ArticleBean;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/6/30.
 */
public class ArticleAdapter extends PagedListAdapter<ArticleBean, ArticleAdapter.ViewHolder> {

    protected ArticleAdapter() {
        super(new DiffUtil.ItemCallback<ArticleBean>() {
            @Override
            public boolean areItemsTheSame(@NonNull ArticleBean oldItem, @NonNull ArticleBean newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull ArticleBean oldItem, @NonNull ArticleBean newItem) {
                return oldItem.equals(newItem);
            }

            @Nullable
            @Override
            public Object getChangePayload(@NonNull ArticleBean oldItem, @NonNull ArticleBean newItem) {
                Bundle payload = new Bundle();
                if (!oldItem.author.equals(newItem.author)) {
                    payload.putString(ArticleBean.KEY_AUTHOR, newItem.author);
                }
                if (!oldItem.title.equals(newItem.title)) {
                    payload.putString(ArticleBean.KEY_TITLE, newItem.title);
                }
                return payload;
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
        final ArticleBean article = getItem(position);
        holder.bindData(article);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemArticleBinding mBinding;

        public ViewHolder(@NonNull View itemView, ItemArticleBinding binding) {
            super(itemView);
            this.mBinding = binding;
        }

        public void bindData(ArticleBean item) {
            mBinding.setArticle(item);
        }
    }

}
