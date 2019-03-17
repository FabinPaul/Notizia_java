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

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class HeadlinesRepositoryTest {

    private static final String COUNTRY = "us";

    private HeadlinesRepository mHeadlinesRepository;
    @Mock
    private HeadlinesDataSource mHeadlinesRemoteDS;
    @Mock
    private HeadlinesDataSource mHeadlinesLocalDS;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mHeadlinesRepository = new HeadlinesRepository(mHeadlinesRemoteDS, mHeadlinesLocalDS);
    }

    @Test
    public void getHeadlinesSuccess() {
        TestObserver<List<ArticlesItem>> testObserver = new TestObserver<>();
//        when(mHeadlinesRemoteDS.getHeadlines(anyString(), anyBoolean())).then(testObserver);

        mHeadlinesRepository.getHeadlines(COUNTRY, true);

        verify(mHeadlinesRemoteDS).getHeadlines(COUNTRY, true);
    }
}