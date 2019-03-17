package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesRepository;
import com.fabinpaul.notizia.network.SchedulerProvider;
import com.fabinpaul.notizia.network.TestSchedulers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HeadlinesPresenterTest {

    private static final String IMAGE_URL = "https://www.img.url";

    @Mock
    private HeadlinesContract.View mView;
    @Mock
    private HeadlinesRepository mRepository;
//    @Mock
    private SchedulerProvider mSchedulers;

    private HeadlinesPresenter mPresenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mSchedulers = new TestSchedulers();
        mPresenter = new HeadlinesPresenter(mView, mRepository, mSchedulers);
    }

    @Test
    public void getHeadlinesTest() {
        when(mRepository.getHeadlines(anyString(), anyBoolean())).thenReturn(Flowable.empty());
//        when(mSchedulers.io()).thenReturn(Schedulers.trampoline());
//        when(mSchedulers.ui()).thenReturn(Schedulers.trampoline());
        mPresenter.start(mView);

        verify(mView).showLoadingDialog(true);
        verify(mRepository).getHeadlines(anyString(), anyBoolean());
        //verify(mSchedulers).io();
        //verify(mSchedulers).ui();
        verify(mView).showLoadingDialog(false);
        //verify(mView).showNewsHeadlines();
    }

    @Test
    public void onNewsArticleClick() {
        ArticlesItem mockItem = mock(ArticlesItem.class);
        when(mockItem.getUrl()).thenReturn(IMAGE_URL);
        mPresenter.onNewsArticleClick(mockItem);
        verify(mView).showNewsDetails(IMAGE_URL);
    }
}