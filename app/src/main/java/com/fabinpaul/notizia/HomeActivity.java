package com.fabinpaul.notizia;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.fabinpaul.notizia.feature.headlines.HeadlinesFragment;
import com.fabinpaul.notizia.feature.newsdetails.NewsDetailsFragment;
import com.fabinpaul.notizia.utils.ActivityUtils;
import com.fabinpaul.notizia.utils.FragmentCommunicator;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity implements FragmentCommunicator {

    public static final String EXTRA_NEWS_URL = "com.fabinpaul.notizia.News_URL";

    @Inject
    Lazy<HeadlinesFragment> mHeadlineFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment homeFragment = getSupportFragmentManager().findFragmentById(R.id.contentMain);
        if (homeFragment == null) {
            homeFragment = mHeadlineFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    homeFragment,
                    R.id.contentMain,
                    HeadlinesFragment.TAG);
        }
    }

    @Override
    public void onHandleMessage(int id, Bundle bundle) {
        switch (id) {
            case R.id.headlinesList:
                Fragment fragment = NewsDetailsFragment.newInstance(bundle);
                ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),
                        fragment, R.id.contentMain);
                break;
            default:
                break;
        }
    }
}
