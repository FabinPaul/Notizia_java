package com.fabinpaul.notizia.feature.newsdetails;

import javax.inject.Inject;

public class NewsDetailsPresenter implements NewsDetailsContract.Presenter {

    private NewsDetailsContract.View mView;
    String mNewsURL;

    @Inject
    public NewsDetailsPresenter(String newsURL) {
        this.mNewsURL = newsURL;
    }

    @Override
    public void start(NewsDetailsContract.View view) {
        this.mView = view;
        mView.loadNewsUrlInView(mNewsURL);
    }

    @Override
    public void stop() {
        mView = null;
    }
}
