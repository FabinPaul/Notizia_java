package com.fabinpaul.notizia.di;

import com.fabinpaul.notizia.feature.headlines.HeadlinesActivity;
import com.fabinpaul.notizia.feature.headlines.HeadlinesModule;
import com.fabinpaul.notizia.feature.newsdetails.NewsDetailsActivity;
import com.fabinpaul.notizia.feature.newsdetails.NewsDetailsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = HeadlinesModule.class)
    abstract HeadlinesActivity headlinesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = NewsDetailsModule.class)
    abstract NewsDetailsActivity newsDetailsActivity();
}
