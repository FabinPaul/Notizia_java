package com.fabinpaul.notizia.feature.headlines.data.source.local;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.NewsSource;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface HeadlinesDao {

    @Query("SELECT * FROM article INNER JOIN source ON source.source_id = article.news_source")
    Flowable<List<ArticlesItem>> getHeadlines();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHeadline(ArticlesItem headline);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNewsSource(NewsSource source);

    @Query("DELETE FROM article")
    void deleteAllHeadlines();
}
