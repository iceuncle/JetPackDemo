package com.tianyang.jetpackdemo.ui.notifications;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tianyang.jetpackdemo.R;
import com.tianyang.jetpackdemo.base.BaseFragment;
import com.tianyang.jetpackdemo.database.entity.Article;
import com.tianyang.jetpackdemo.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        adapter.setHasStableIds(true);
        mBinding.recyclerView.setAdapter(adapter);
//        mBinding.recyclerView.setItemAnimator(null);
        mBinding.recyclerView.setHasFixedSize(true);

        mViewModel.getAllArticles().observe(this, articles -> {
            adapter.swapData(articles, false);
        });


        mBinding.fab.setOnClickListener(v -> {
//            Article article = new Article();
//            article.id = mId++;
//            article.title = "title " + mId;
//            article.author = "author" + mId;
//            mViewModel.insert(article);
            adapter.addData(getData());

//            adapter.mArticles.remove(1);
//            adapter.notifyItemRemoved(1);
        });


        mBinding.refresh.setOnClickListener(v -> {
            adapter.refresh(getData());
//            adapter.swapData(getData(), false);
        });
    }

    public List<Article> getData() {
        List<Article> data = new ArrayList<>();
        data.add(new Article() {{
            id = 11;
            title = "嘿嘿嘿" + new Random().nextInt(100);
            author = "啦啦啦";
        }} );
        data.add(new Article() {{
            id = 12;
            title = "title1";
            author = "author1";
        }});
        data.add(new Article() {{
            id = new Random().nextInt(10);
            title = "title2";
            author = "author2";
        }});
        data.add(new Article() {{
            id = 13;
            title = "title3";
            author = "author3";
        }});
        return data;
    }


}