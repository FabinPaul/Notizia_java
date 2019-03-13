package com.fabinpaul.notizia;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.fabinpaul.notizia.feature.headlines.HeadlinesFragment;
import com.fabinpaul.notizia.utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<HeadlinesFragment> mHeadlineFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment homeFragment = getSupportFragmentManager().findFragmentById(R.id.contentMain);
        if (homeFragment == null) {
            homeFragment = mHeadlineFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homeFragment, R.id.contentMain);
        }
    }
}
