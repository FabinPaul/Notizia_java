package com.fabinpaul.notizia.headlines.data.source;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.data.RemoteDataSource;
import com.fabinpaul.notizia.headlines.data.source.remote.HeadlineRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HeadlinesRepositoryModule {

    @Provides
    @RemoteDataSource
    HeadlinesDataSource provideHeadlinesRemoteDataSource() {
        return new HeadlineRemoteDataSource(getRetrofitClient());
    }

    @Provides
    Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
