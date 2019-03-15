package com.fabinpaul.notizia.di;

import com.fabinpaul.notizia.HomeActivity;
import com.fabinpaul.notizia.feature.headlines.HeadlinesModule;
import com.fabinpaul.notizia.feature.newsdetails.NewsDetailsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {HeadlinesModule.class, NewsDetailsModule.class})
    abstract HomeActivity homeActivity();
}
