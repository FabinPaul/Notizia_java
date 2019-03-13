package com.fabinpaul.notizia.feature.headlines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabinpaul.notizia.R;
import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.feature.headlines.components.ArticlesAdapter;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class HeadlinesFragment extends DaggerFragment implements HeadlinesContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    HeadlinesContract.Presenter mPresenter;

    private RecyclerView mHeadlineList;
    private SwipeRefreshLayout mRefreshHeadlines;

    @Inject
    public HeadlinesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        mHeadlineList = view.findViewById(R.id.headlinesList);
        mRefreshHeadlines = view.findViewById(R.id.refreshHeadlines);

        mRefreshHeadlines.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    @Override
    public void showNewsHeadlines(List<ArticlesItem> articles) {
        ArticlesAdapter articlesAdapter = new ArticlesAdapter(articles);
        mHeadlineList.setAdapter(articlesAdapter);
    }

    @Override
    public void showLoadingDialog(boolean visible) {
        mRefreshHeadlines.setRefreshing(visible);
    }

    @Override
    public void showErrorDialog(String title, String message) {

    }

    @Override
    public void onRefresh() {
        mPresenter.updateNewsHeadlines();
    }
}
