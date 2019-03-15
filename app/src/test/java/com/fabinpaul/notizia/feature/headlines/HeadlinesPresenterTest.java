package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class HeadlinesPresenterTest {

    @Mock
    private HeadlinesContract.View mView;

    @Mock
    private HeadlinesRepository mRepository;

    private HeadlinesPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new HeadlinesPresenter(mRepository);
    }

    @Test
    public void getHeadlinesTest() {
        mPresenter.start(mView);

        verify(mView).showLoadingDialog(true);
    }

    @After
    public void tearDown() throws Exception {
    }
}