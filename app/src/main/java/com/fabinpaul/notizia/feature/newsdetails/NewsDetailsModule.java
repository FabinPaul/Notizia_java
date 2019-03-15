package com.fabinpaul.notizia.feature.newsdetails;

import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NewsDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract NewsDetailsFragment newsDetailsFragment();

    @ActivityScoped
    @Binds
    abstract NewsDetailsContract.Presenter newsDetailsPresenter(NewsDetailsPresenter presenter);

    @Provides
    @ActivityScoped
    static String provideNewsUrl(NewsDetailsActivity activity) {
        return activity.getIntent().getStringExtra(NewsDetailsActivity.EXTRA_NEWS_URL);
    }
}
