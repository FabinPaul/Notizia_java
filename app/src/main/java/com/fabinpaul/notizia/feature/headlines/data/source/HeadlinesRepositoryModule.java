package com.fabinpaul.notizia.feature.headlines.data.source;

import android.app.Application;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.data.LocalDataSource;
import com.fabinpaul.notizia.data.NewsDatabase;
import com.fabinpaul.notizia.data.RemoteDataSource;
import com.fabinpaul.notizia.feature.headlines.data.source.local.HeadlinesDao;
import com.fabinpaul.notizia.feature.headlines.data.source.local.HeadlinesLocalDataSource;
import com.fabinpaul.notizia.feature.headlines.data.source.remote.HeadlineRemoteDataSource;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HeadlinesRepositoryModule {

    @Provides
    @RemoteDataSource
    HeadlinesDataSource provideHeadlinesRemoteDataSource(Retrofit retrofitClient) {
        return new HeadlineRemoteDataSource(retrofitClient);
    }

    @Provides
    @LocalDataSource
    HeadlinesDataSource provideHeadlinesLocalDataSource(HeadlinesDao headlinesDao) {
        return new HeadlinesLocalDataSource(headlinesDao);
    }

    @Provides
    Retrofit getRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    NewsDatabase newsDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                NewsDatabase.class, "News.db")
                .build();
    }

    @Singleton
    @Provides
    HeadlinesDao provideTasksDao(NewsDatabase db) {
        return db.headlinesDao();
    }
}
