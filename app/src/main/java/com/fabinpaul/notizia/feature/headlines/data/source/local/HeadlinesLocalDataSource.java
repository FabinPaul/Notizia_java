package com.fabinpaul.notizia.feature.headlines.data.source.local;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;

public class HeadlinesLocalDataSource implements HeadlinesDataSource {

    private final HeadlinesDao mHeadlinesDao;

    @Inject
    public HeadlinesLocalDataSource(@NonNull HeadlinesDao mHeadlinesDao) {
        this.mHeadlinesDao = mHeadlinesDao;
    }

    @Override
    public Flowable<List<ArticlesItem>> getHeadlines(@NonNull String country, boolean refresh) {
        return mHeadlinesDao.getHeadlines();
    }

    @Override
    public void saveHeadlines(@NonNull ArticlesItem article) {
        long sourceId = mHeadlinesDao.insertNewsSource(article.getNewsSource());
        article.setNewsSourceID(sourceId);
        mHeadlinesDao.insertHeadline(article);
    }

    @Override
    public void removeAllHeadlines() {
        mHeadlinesDao.deleteAllHeadlines();
    }
}
