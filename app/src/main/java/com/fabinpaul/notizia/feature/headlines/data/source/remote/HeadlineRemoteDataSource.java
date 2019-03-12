package com.fabinpaul.notizia.feature.headlines.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.TopHeadlinesRes;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.network.SchedulerProvider;
import com.fabinpaul.notizia.utils.Callback;
import com.fabinpaul.notizia.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;

public class HeadlineRemoteDataSource implements HeadlinesDataSource {

    private static final String TAG = "HeadlineRemoteDS";
    private static final String COUNTRY_US = "us";

    private Retrofit mRetrofitClient;
    private SchedulerProvider mScheduler;
    private Callback<List<ArticlesItem>> mResponseCallback;

    @Inject
    public HeadlineRemoteDataSource(Retrofit retrofitClient, SchedulerProvider scheduler) {
        this.mRetrofitClient = retrofitClient;
        this.mScheduler = scheduler;
    }

    @Override
    public Disposable getHeadlines(@NonNull final Callback<List<ArticlesItem>> responseCallback) {
        mResponseCallback = responseCallback;
        HeadlinesRetrofitService service = mRetrofitClient.create(HeadlinesRetrofitService.class);
        return service.getTopHeadlines(BuildConfig.NEWS_API_KEY, COUNTRY_US)
                .subscribeOn(mScheduler.io())
                .observeOn(mScheduler.ui())
                .subscribeWith(new DisposableObserver<TopHeadlinesRes>() {

                    @Override
                    public void onNext(TopHeadlinesRes topHeadlinesRes) {
                        if (mResponseCallback != null && topHeadlinesRes != null) {
                            mResponseCallback.onSuccess(topHeadlinesRes.getArticles());
                        } else {
                            Log.d(TAG, "Response or Response callback is null");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mResponseCallback != null) {
                            mResponseCallback.onError(Constants.ErrorCodes.RETROFIT, e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Top Headline API Complete");
                    }
                });
    }

    @Override
    public void nullifyHeadlineCallback() {
        mResponseCallback = null;
    }
}
