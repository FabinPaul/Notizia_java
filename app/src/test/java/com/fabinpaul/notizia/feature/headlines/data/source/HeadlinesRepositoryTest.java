package com.fabinpaul.notizia.feature.headlines.data.source;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.utils.Callback;
import com.fabinpaul.notizia.utils.Constants;
import com.fabinpaul.notizia.utils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class HeadlinesRepositoryTest {

    private static final String HEADLINE_CACHE_KEY = "headines_cache";

    private HeadlinesRepository mHeadlinesRepository;
    @Mock
    private HeadlinesDataSource mHeadlinesRemoteDS;
    @Mock
    private Callback<List<ArticlesItem>> mHeadlinesResCallback;
    @Captor
    private ArgumentCaptor<Callback<List<ArticlesItem>>> mRemoteHeadlineCallbackCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mHeadlinesRepository = new HeadlinesRepository(mHeadlinesRemoteDS);
    }

    @Test
    public void getHeadlinesSuccess() {
        mHeadlinesRepository.getHeadlines(mHeadlinesResCallback);

        verify(mHeadlinesRemoteDS).getHeadlines(mRemoteHeadlineCallbackCaptor.capture());

        List<ArticlesItem> articles = TestUtils.createListOfMockResponses(ArticlesItem.class, 3);

        mRemoteHeadlineCallbackCaptor.getValue().onSuccess(articles);

        assertFalse(mHeadlinesRepository.getCache().get(HEADLINE_CACHE_KEY).isEmpty());

        mHeadlinesRepository.getHeadlines(mHeadlinesResCallback);

        verify(mHeadlinesResCallback, times(2)).onSuccess(articles);

        verifyNoMoreInteractions(mHeadlinesRemoteDS);
    }

    @Test
    public void getHeadlinesFailure() {
        mHeadlinesRepository.getHeadlines(mHeadlinesResCallback);

        verify(mHeadlinesRemoteDS).getHeadlines(mRemoteHeadlineCallbackCaptor.capture());

        mRemoteHeadlineCallbackCaptor.getValue().onError(Constants.ErrorCodes.RETROFIT, null);

        verify(mHeadlinesResCallback).onError(Constants.ErrorCodes.RETROFIT, null);
    }

    @Test
    public void nullifyHeadlineCallback() {
        mHeadlinesRepository.nullifyHeadlineCallback();

        verify(mHeadlinesRemoteDS).nullifyHeadlineCallback();
    }
}