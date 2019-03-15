package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesRepository;
import com.fabinpaul.notizia.utils.Callback;

import java.util.List;

import javax.inject.Inject;

@ActivityScoped
public class HeadlinesPresenter implements HeadlinesContract.Presenter {

    private HeadlinesContract.View mView;
    private HeadlinesDataSource mRepository;

    @Inject
    public HeadlinesPresenter(HeadlinesRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void start(HeadlinesContract.View view) {
        this.mView = view;
        getHeadlines();
    }

    private void getHeadlines() {
        mView.showLoadingDialog(true);
        mRepository.getHeadlines(new Callback<List<ArticlesItem>>() {
            @Override
            public void onSuccess(List<ArticlesItem> response) {
                mView.showLoadingDialog(false);
                mView.showNewsHeadlines(response);
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
                mView.showLoadingDialog(false);
                mView.showErrorDialog(errorCode, errorMessage);
            }
        });
    }

    @Override
    public void stop() {
        this.mView = null;
    }

    @Override
    public void onNewsArticleClick(ArticlesItem articlesItem) {
        mView.showNewsDetails(articlesItem.getUrl());
    }

    @Override
    public void updateNewsHeadlines() {
        getHeadlines();
    }
}
