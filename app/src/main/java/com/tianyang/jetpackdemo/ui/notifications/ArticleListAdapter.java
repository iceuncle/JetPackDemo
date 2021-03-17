package com.tianyang.jetpackdemo.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tianyang.jetpackdemo.database.entity.Article;
import com.tianyang.jetpackdemo.databinding.ItemArticleListBinding;
import com.tianyang.jetpackdemo.model.ArticleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    public List<Article> mArticles = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleListBinding binding = ItemArticleListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ArticleListAdapter.ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article article = mArticles.get(position);
        holder.bindData(article);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            for (String key : payload.keySet()) {
                switch (key) {
                    case ArticleBean.KEY_AUTHOR:
                        holder.mBinding.authorTv.setText(payload.getString(key));
                        break;
                    case ArticleBean.KEY_TITLE:
                        holder.mBinding.titleTv.setText(payload.getString(key));
                        break;
                }
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return mArticles.get(position).id;
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void refresh(List<Article> articles) {
        swapData(articles, true);
    }

    public void swapData(List<Article> newList, boolean diff) {
        if (diff) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ArticleCallback(mArticles, newList), false);
            mArticles = newList;
            diffResult.dispatchUpdatesTo(this);
        } else {
            mArticles = newList;
            notifyDataSetChanged();
        }
    }

    public void addData(List<Article> data) {
        int position = mArticles.size();
        mArticles.addAll(data);
        notifyItemRangeInserted(position, data.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ItemArticleListBinding mBinding;

        public ViewHolder(@NonNull View itemView, ItemArticleListBinding binding) {
            super(itemView);
            this.mBinding = binding;
        }

        public void bindData(Article item) {
            mBinding.setArticle(item);
        }
    }

    public static class ArticleCallback extends DiffUtil.Callback {
        private List<Article> oldList;
        private List<Article> newList;

        public ArticleCallback(List<Article> oldList, List<Article> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Article oldItem = oldList.get(oldItemPosition);
            Article newItem = newList.get(newItemPosition);
            return oldItem.equals(newItem);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            Article oldItem = oldList.get(oldItemPosition);
            Article newItem = newList.get(newItemPosition);
            Bundle payload = new Bundle();
            if (!oldItem.author.equals(newItem.author)) {
                payload.putString(ArticleBean.KEY_AUTHOR, newItem.author);
            }
            if (!oldItem.title.equals(newItem.title)) {
                payload.putString(ArticleBean.KEY_TITLE, newItem.title);
            }
            return payload;
        }
    }
}
