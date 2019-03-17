package com.fabinpaul.notizia.feature.headlines;

import androidx.annotation.NonNull;

import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesRepository;
import com.fabinpaul.notizia.network.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@ActivityScoped
public class HeadlinesPresenter implements HeadlinesContract.Presenter {

    @NonNull
    private final HeadlinesDataSource mRepository;
    @NonNull
    private final SchedulerProvider mScheduler;
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private HeadlinesContract.View mView;

    @Inject
    public HeadlinesPresenter(@NonNull HeadlinesRepository mRepository,
                              @NonNull SchedulerProvider scheduler) {
        this.mRepository = mRepository;
        this.mScheduler = scheduler;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public HeadlinesPresenter(@NonNull HeadlinesContract.View view,
                              @NonNull HeadlinesRepository mRepository,
                              @NonNull SchedulerProvider scheduler) {
        this.mRepository = mRepository;
        this.mScheduler = scheduler;
        this.mView = view;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start(@NonNull HeadlinesContract.View view) {
        this.mView = view;
        getHeadlines(false);
    }

    private void getHeadlines(boolean refresh) {
        mView.showLoadingDialog(true);
        mCompositeDisposable.add(
                mRepository.getHeadlines("us", refresh)
                        .subscribeOn(mScheduler.io())
                        .observeOn(mScheduler.ui())
                        .subscribe(articlesItems -> {
                            mView.showLoadingDialog(false);
                            mView.showNewsHeadlines(articlesItems);
                        }));

    }

    @Override
    public void stop() {
        this.mView = null;
        mCompositeDisposable.clear();
    }

    @Override
    public void onNewsArticleClick(ArticlesItem articlesItem) {
        mView.showNewsDetails(articlesItem.getUrl());
    }

    @Override
    public void updateNewsHeadlines(boolean refresh) {
        getHeadlines(refresh);
    }
}
