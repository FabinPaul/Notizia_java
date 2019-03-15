package com.fabinpaul.notizia.feature.headlines.data.source;

import android.support.annotation.NonNull;

import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.utils.Callback;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface HeadlinesDataSource {

    Disposable getHeadlines(@NonNull Callback<List<ArticlesItem>> responseCallback);

    void nullifyHeadlineCallback();
}
