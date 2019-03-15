package com.fabinpaul.notizia.feature.headlines.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.fabinpaul.notizia.data.RemoteDataSource;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.utils.Callback;
import com.fabinpaul.notizia.utils.Constants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

@Singleton
public class HeadlinesRepository implements HeadlinesDataSource {

    private static final String TAG = "HeadlinesRepository";
    private static final String HEADLINE_CACHE_KEY = "headines_cache";

    private final HeadlinesDataSource mHeadlinesDataSource;

    private Map<String, List<ArticlesItem>> mCachedHeadlines;

    @Inject
    HeadlinesRepository(@RemoteDataSource HeadlinesDataSource mHeadlinesDataSource) {
        this.mHeadlinesDataSource = mHeadlinesDataSource;
    }

    @Override
    public Disposable getHeadlines(@NonNull final Callback<List<ArticlesItem>> responseCallback) {
        if (mCachedHeadlines != null && mCachedHeadlines.get(HEADLINE_CACHE_KEY) != null) {
            //noinspection ConstantConditions
            return Observable.just(mCachedHeadlines.get(HEADLINE_CACHE_KEY)).subscribeWith(new DisposableObserver<List<ArticlesItem>>() {
                @Override
                public void onNext(List<ArticlesItem> articlesItems) {
                    responseCallback.onSuccess(articlesItems);
                }

                @Override
                public void onError(Throwable e) {
                    responseCallback.onError(Constants.ErrorCodes.CACHE, e.getMessage());
                }

                @Override
                public void onComplete() {
                    Log.i(TAG, "Cache retrieval complete");
                }
            });
        } else {
            return mHeadlinesDataSource.getHeadlines(new Callback<List<ArticlesItem>>() {
                @Override
                public void onSuccess(List<ArticlesItem> response) {
                    if (mCachedHeadlines == null) {
                        mCachedHeadlines = new LinkedHashMap<>();
                    }
                    mCachedHeadlines.put(HEADLINE_CACHE_KEY, response);
                    responseCallback.onSuccess(response);
                }

                @Override
                public void onError(String errorCode, String errorMessage) {
                    responseCallback.onError(errorCode, errorMessage);
                }
            });
        }
    }

    @Override
    public void nullifyHeadlineCallback() {
        mHeadlinesDataSource.nullifyHeadlineCallback();
    }

    @VisibleForTesting
    Map<String, List<ArticlesItem>> getCache() {
        return mCachedHeadlines;
    }
}
