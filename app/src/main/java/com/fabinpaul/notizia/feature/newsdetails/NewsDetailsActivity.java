package com.fabinpaul.notizia.feature.newsdetails;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fabinpaul.notizia.BaseActivity;

import javax.inject.Inject;

import dagger.Lazy;

public class NewsDetailsActivity extends BaseActivity {

    public static final String EXTRA_NEWS_URL = "com.fabinpaul.notizia.News_URL";

    @Inject
    Lazy<NewsDetailsFragment> mNewsDetailsFragmentProvider;

    @Override
    public Fragment getFragment() {
        return mNewsDetailsFragmentProvider.get();
    }

    @Nullable
    @Override
    public String getFragmentTag() {
        return null;
    }

    public static void startActivity(Activity srcActivity, String newsURL) {
        Intent intent = new Intent(srcActivity, NewsDetailsActivity.class);
        intent.putExtra(EXTRA_NEWS_URL, newsURL);
        srcActivity.startActivity(intent);
    }
}
