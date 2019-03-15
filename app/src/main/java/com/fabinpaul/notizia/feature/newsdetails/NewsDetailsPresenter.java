package com.fabinpaul.notizia.feature.newsdetails;

import com.fabinpaul.notizia.BasePresenter;

import javax.inject.Inject;

public class NewsDetailsPresenter implements NewsDetailsContract.Presenter {

    String mNewsURL;

    @Inject
    public NewsDetailsPresenter(String newsURL) {
        this.mNewsURL = newsURL;
    }

    @Override
    public void start(NewsDetailsContract.View view) {

    }

    @Override
    public void stop() {

    }
}
