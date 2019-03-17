package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.di.ActivityScoped;
import com.fabinpaul.notizia.di.FragmentScoped;
import com.fabinpaul.notizia.network.AppSchedulers;
import com.fabinpaul.notizia.network.SchedulerProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HeadlinesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HeadlinesFragment headlinesFragment();

    @ActivityScoped
    @Binds
    abstract HeadlinesContract.Presenter headlinePresenter(HeadlinesPresenter presenter);

    @Provides
    static SchedulerProvider getSchedulers() {
        return new AppSchedulers();
    }
}
