package com.fabinpaul.notizia.headlines.data.source.remote;

import com.fabinpaul.notizia.headlines.data.TopHeadlinesRes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadlinesRetrofitService {

    @GET("top-headlines")
    Observable<TopHeadlinesRes> getTopHeadlines(@Query("apiKey") String apiKey, @Query("country") String country);
}
