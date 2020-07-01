package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.BaseFragment;
import com.tianyang.jetpackdemo.database.entity.Article;
import com.tianyang.jetpackdemo.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends BaseFragment<NotificationsViewModel, FragmentNotificationsBinding> {

    private int mId = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected void initView() {
        mViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        mBinding.setViewModel(mViewModel);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final ArticleListAdapter adapter = new ArticleListAdapter();
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setItemAnimator(null);

        mViewModel.getAllArticles().observe(this, adapter::setArticles);

        mBinding.fab.setOnClickListener(v -> {
            Article article = new Article();
            article.id = mId++;
            article.title = "title " + mId;
            mViewModel.insert(article);
        });
    }
}