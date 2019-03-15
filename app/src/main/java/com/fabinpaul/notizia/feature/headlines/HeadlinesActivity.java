package com.fabinpaul.notizia.feature.headlines;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.fabinpaul.notizia.BaseActivity;

import javax.inject.Inject;

import dagger.Lazy;

public class HeadlinesActivity extends BaseActivity {

    @Inject
    Lazy<HeadlinesFragment> mHeadlineFragmentProvider;

    @Override
    public Fragment getFragment() {
        return mHeadlineFragmentProvider.get();
    }

    @Nullable
    @Override
    public String getFragmentTag() {
        return null;
    }
}
