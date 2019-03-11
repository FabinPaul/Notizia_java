package com.fabinpaul.notizia.headlines.data.source.remote;

import android.util.Log;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.headlines.data.TopHeadlinesRes;
import com.fabinpaul.notizia.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.utils.Callback;
import com.fabinpaul.notizia.utils.Constants;
import com.fabinpaul.notizia.rules.RxImmediateSchedulerRule;
import com.fabinpaul.notizia.utils.StubbedInterceptor;
import com.fabinpaul.notizia.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeadlineRemoteDataSourceTest {

    @Rule
    public RxImmediateSchedulerRule immediateSchedulerRule = new RxImmediateSchedulerRule();
    private HeadlinesDataSource dataSource;
    private StubbedInterceptor interceptor;
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        interceptor = new StubbedInterceptor();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(TestUtils.getOkHttpClient(interceptor))
                .build();
        server = new MockWebServer();
        dataSource = new HeadlineRemoteDataSource(retrofit);
        server.start();
    }

    @Test
    public void getHeadlines() throws IOException, URISyntaxException {
        String response = TestUtils.getStringFromFile(getClass().getResource("/responses/top_headlines.json").toURI().getPath());
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(response));
        dataSource.getHeadlines(new Callback<TopHeadlinesRes>() {
            @Override
            public void onSuccess(TopHeadlinesRes response) {
                Log.d("","");
            }

            @Override
            public void onError(String errorCode, String errorMessage) {

            }
        });
    }
}