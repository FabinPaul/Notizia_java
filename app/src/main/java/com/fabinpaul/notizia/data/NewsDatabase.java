package com.fabinpaul.notizia.data;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.NewsSource;
import com.fabinpaul.notizia.feature.headlines.data.source.local.HeadlinesDao;

import javax.inject.Singleton;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Singleton
@Database(entities = {ArticlesItem.class, NewsSource.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract HeadlinesDao headlinesDao();

}
