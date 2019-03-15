package com.fabinpaul.notizia.feature.newsdetails;

import com.fabinpaul.notizia.HomeActivity;
import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.di.FragmentScoped;
import com.fabinpaul.notizia.feature.headlines.HeadlinesContract;
import com.fabinpaul.notizia.feature.headlines.HeadlinesFragment;
import com.fabinpaul.notizia.feature.headlines.HeadlinesPresenter;

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
    static String provideNewsUrl(NewsDetailsFragment fragment) {
        if (fragment.getArguments() != null) {
            return fragment.getArguments().getString(HomeActivity.EXTRA_NEWS_URL);
        } else {
            return "";
        }
    }
}
