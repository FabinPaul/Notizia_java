package com.fabinpaul.notizia.feature.headlines.data.source;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;

public interface HeadlinesDataSource {

    Flowable<List<ArticlesItem>> getHeadlines(@NonNull String country, boolean refresh);

    void saveHeadlines(@NonNull ArticlesItem article);

    void removeAllHeadlines();
}
