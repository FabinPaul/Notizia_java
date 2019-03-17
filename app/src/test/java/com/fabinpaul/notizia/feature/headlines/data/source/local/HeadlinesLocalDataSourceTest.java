package com.fabinpaul.notizia.feature.headlines.data.source.local;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.feature.headlines.data.source.HeadlinesDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HeadlinesLocalDataSourceTest {

    private HeadlinesDataSource mDataSource;
    @Mock
    HeadlinesDao mHeadlinesDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mDataSource = new HeadlinesLocalDataSource(mHeadlinesDao);
    }

    @Test
    public void getHeadlines() {
        mDataSource.getHeadlines("us", true);

        verify(mHeadlinesDao).getHeadlines();
    }

    @Test
    public void saveHeadlines() {
        mDataSource.saveHeadlines(mock(ArticlesItem.class));

        verify(mHeadlinesDao).insertNewsSource(any());
        verify(mHeadlinesDao).insertHeadline(any());
    }

    @Test
    public void removeAllHeadlines() {
        mDataSource.removeAllHeadlines();

        verify(mHeadlinesDao).deleteAllHeadlines();
    }
}