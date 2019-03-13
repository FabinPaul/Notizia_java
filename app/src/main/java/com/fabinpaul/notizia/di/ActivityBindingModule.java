package com.fabinpaul.notizia.di;

import com.fabinpaul.notizia.HomeActivity;
import com.fabinpaul.notizia.feature.headlines.HeadlinesModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = HeadlinesModule.class)
    abstract HomeActivity homeActivity();
}
