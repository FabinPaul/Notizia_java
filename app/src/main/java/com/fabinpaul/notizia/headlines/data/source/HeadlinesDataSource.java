package com.fabinpaul.notizia.headlines.data.source;

import com.fabinpaul.notizia.headlines.data.TopHeadlinesRes;
import com.fabinpaul.notizia.utils.Callback;

public interface HeadlinesDataSource {

    void getHeadlines(Callback<TopHeadlinesRes> responseCallback);
}
