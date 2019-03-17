package com.fabinpaul.notizia.feature.headlines.data.source;

import com.fabinpaul.notizia.data.LocalDataSource;
import com.fabinpaul.notizia.data.RemoteDataSource;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

@Singleton
public class HeadlinesRepository implements HeadlinesDataSource {

    private final HeadlinesDataSource mHeadlinesRemoteDataSource;
    private final HeadlinesDataSource mHeadlinesLocalDataSource;

    private List<ArticlesItem> mCachedHeadlines;

    @Inject
    HeadlinesRepository(@RemoteDataSource HeadlinesDataSource headlinesRemoteDataSource,
                        @LocalDataSource HeadlinesDataSource headlinesLocalDataSource) {
        this.mHeadlinesRemoteDataSource = headlinesRemoteDataSource;
        this.mHeadlinesLocalDataSource = headlinesLocalDataSource;
    }

    @Override
    public Flowable<List<ArticlesItem>> getHeadlines(@NonNull String country, boolean refresh) {
        if (mCachedHeadlines != null && !refresh) {
            return Flowable.fromIterable(mCachedHeadlines).toList().toFlowable();
        }

        Flowable<List<ArticlesItem>> remoteFlowable = mHeadlinesRemoteDataSource
                .getHeadlines(country, refresh)
                .map(articlesItems -> mCachedHeadlines = articlesItems)
                .flatMap((Function<List<ArticlesItem>, Publisher<List<ArticlesItem>>>) articlesItems -> {
                    mHeadlinesLocalDataSource.removeAllHeadlines();
                    return Flowable.fromIterable(articlesItems)
                            .doOnNext(mHeadlinesLocalDataSource::saveHeadlines)
                            .toList()
                            .toFlowable();
                });
        if (refresh) {
            return remoteFlowable;
        } else {
            Flowable<List<ArticlesItem>> localFlowable = mHeadlinesLocalDataSource
                    .getHeadlines(country, refresh)
                    .map(articlesItems -> mCachedHeadlines = articlesItems);
            return localFlowable.flatMap((Function<List<ArticlesItem>, Publisher<List<ArticlesItem>>>) articlesItems -> {
                if (articlesItems.isEmpty()) {
                    return remoteFlowable;
                } else {
                    return Flowable.fromIterable(articlesItems)
                            .toList()
                            .toFlowable();
                }
            });
        }
    }

    @Override
    public void saveHeadlines(@NonNull ArticlesItem article) {
        mHeadlinesLocalDataSource.saveHeadlines(article);
    }

    @Override
    public void removeAllHeadlines() {
        mHeadlinesLocalDataSource.removeAllHeadlines();
    }

    @VisibleForTesting
    List<ArticlesItem> getCache() {
        return mCachedHeadlines;
    }
}
