package com.fabinpaul.notizia.headlines.data.source.remote;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.headlines.data.TopHeadlinesRes;
import com.fabinpaul.notizia.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.utils.Callback;
import com.fabinpaul.notizia.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeadlineRemoteDataSource implements HeadlinesDataSource {

    private static final String COUNTRY_US = "us";

    private Retrofit mRetrofitClient;

//    @Inject
//    public HeadlineRemoteDataSource() {
//        mRetrofitClient = new Retrofit.Builder()
//                .baseUrl(Constants.NEWS_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }

    @Inject
    public HeadlineRemoteDataSource(Retrofit retrofitClient) {
        this.mRetrofitClient = retrofitClient;
    }

    @Override
    public void getHeadlines(final Callback<TopHeadlinesRes> responseCallback) {
        HeadlinesRetrofitService service = mRetrofitClient.create(HeadlinesRetrofitService.class);
        service.getTopHeadlines(BuildConfig.NEWS_API_KEY,COUNTRY_US)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopHeadlinesRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("","");
                    }

                    @Override
                    public void onNext(TopHeadlinesRes topHeadlinesRes) {
                        responseCallback.onSuccess(topHeadlinesRes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("","");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("","");
                    }
                });
    }
}
