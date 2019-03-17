package com.fabinpaul.notizia.di;

import android.app.Application;

import com.fabinpaul.notizia.NotiziaApplication;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {HeadlinesRepositoryModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface ApplicationComponent extends AndroidInjector<NotiziaApplication> {

    //This help us to instantiate any modules in the application
    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}
