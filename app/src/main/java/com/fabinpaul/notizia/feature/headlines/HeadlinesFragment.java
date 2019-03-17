package com.fabinpaul.notizia.feature.headlines;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabinpaul.notizia.R;
import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.feature.headlines.components.ArticlesAdapter;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.newsdetails.NewsDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class HeadlinesFragment extends DaggerFragment implements HeadlinesContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "HeadlinesFragment";

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
        Toolbar toolbar = view.findViewById(R.id.headlinesToolbar);

        mRefreshHeadlines.setOnRefreshListener(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    @Override
    public void showNewsHeadlines(List<ArticlesItem> articles) {
        ArticlesAdapter articlesAdapter = new ArticlesAdapter(articles, mPresenter);
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
    public void showNewsDetails(String newsURL) {
        NewsDetailsActivity.startActivity(getActivity(), newsURL);
    }

    @Override
    public void onRefresh() {
        mPresenter.updateNewsHeadlines(true);
    }
}
