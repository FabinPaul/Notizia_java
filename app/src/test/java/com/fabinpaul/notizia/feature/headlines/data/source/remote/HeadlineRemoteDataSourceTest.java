package com.fabinpaul.notizia.feature.headlines.data.source.remote;

import com.fabinpaul.notizia.BuildConfig;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.network.TestSchedulers;
import com.fabinpaul.notizia.utils.Callback;
import com.fabinpaul.notizia.utils.Constants;
import com.fabinpaul.notizia.utils.StubbedInterceptor;
import com.fabinpaul.notizia.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class HeadlineRemoteDataSourceTest {

    private HeadlinesDataSource mDataSource;
    private StubbedInterceptor mInterceptor;
    private TestSchedulers mTestScheduler;
    private CompositeDisposable mCompositeDisposable;
    @Mock
    private Callback<List<ArticlesItem>> mHeadlinesResCallback;
    @Captor
    private ArgumentCaptor<List<ArticlesItem>> mHeadlinesResCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mInterceptor = new StubbedInterceptor("/responses/top_headlines.json");
        mTestScheduler = new TestSchedulers();
        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(TestUtils.getOkHttpClient(mInterceptor))
                .build();
        mDataSource = new HeadlineRemoteDataSource(retrofit, mTestScheduler);
    }

    @Test
    public void getHeadlinesSucess() {
        mCompositeDisposable.add(mDataSource.getHeadlines(mHeadlinesResCallback));
        mTestScheduler.getScheduler().triggerActions();

        assertTrue(mInterceptor.getURL().contains("https://newsapi.org/v2/top-headlines"));
        assertEquals("GET", mInterceptor.getRequestMethod());

        verify(mHeadlinesResCallback).onSuccess(mHeadlinesResCaptor.capture());

        assertFalse(mHeadlinesResCaptor.getValue().isEmpty());
    }

    @Test
    public void getHeadlinesFailure() {
        mInterceptor.setBody(null);
        mCompositeDisposable.add(mDataSource.getHeadlines(mHeadlinesResCallback));
        mTestScheduler.getScheduler().triggerActions();

        assertTrue(mInterceptor.getURL().contains("https://newsapi.org/v2/top-headlines"));
        assertEquals("GET", mInterceptor.getRequestMethod());

        verify(mHeadlinesResCallback).onError(Constants.ErrorCodes.RETROFIT, null);;
    }

    @After
    public void tearDown() {
        mCompositeDisposable.clear();
    }
}