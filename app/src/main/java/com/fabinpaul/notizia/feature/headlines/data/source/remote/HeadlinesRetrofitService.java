package com.fabinpaul.notizia.feature.headlines.data.source.remote;

import com.fabinpaul.notizia.feature.headlines.data.TopHeadlinesRes;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadlinesRetrofitService {

    @GET("top-headlines")
    Flowable<TopHeadlinesRes> getTopHeadlines(@Query("apiKey") String apiKey, @Query("country") String country);
}
