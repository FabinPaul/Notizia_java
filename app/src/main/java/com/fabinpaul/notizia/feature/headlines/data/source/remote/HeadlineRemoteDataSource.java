package com.fabinpaul.notizia.feature.headlines.data.source.remote;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.TopHeadlinesRes;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;
import retrofit2.Retrofit;

public class HeadlineRemoteDataSource implements HeadlinesDataSource {

    private final Retrofit mRetrofitClient;

    @Inject
    public HeadlineRemoteDataSource(@NonNull Retrofit retrofitClient) {
        this.mRetrofitClient = retrofitClient;
    }

    @Override
    public Flowable<List<ArticlesItem>> getHeadlines(@NonNull String country, boolean refresh) {
        HeadlinesRetrofitService service = mRetrofitClient.create(HeadlinesRetrofitService.class);
        return service.getTopHeadlines(BuildConfig.NEWS_API_KEY, country)
                .map(TopHeadlinesRes::getArticles);
    }

    @Override
    public void saveHeadlines(@NonNull ArticlesItem article) {
        throw new UnsupportedOperationException("News can't be saved at newsapi.org");
    }

    @Override
    public void removeAllHeadlines() {
        throw new UnsupportedOperationException("News can't be deleted at newsapi.org");
    }
}
