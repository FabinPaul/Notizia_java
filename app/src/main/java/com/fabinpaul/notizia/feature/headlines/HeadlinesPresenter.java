package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;

import javax.inject.Inject;

@ActivityScoped
public class HeadlinesPresenter implements HeadlinesContract.Presenter {

    private HeadlinesContract.View mView;
    private HeadlinesDataSource mRepository;

    @Inject
    public HeadlinesPresenter(HeadlinesDataSource mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void start(HeadlinesContract.View view) {
        this.mView = view;
    }

    @Override
    public void stop() {
        this.mView = null;
    }
}
