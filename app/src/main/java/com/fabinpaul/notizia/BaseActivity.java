package com.fabinpaul.notizia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.fabinpaul.notizia.utils.ActivityUtils;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentMain);
        if (fragment == null) {
            fragment = getFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,
                    R.id.contentMain,
                    getFragmentTag());
        }
    }

    public abstract Fragment getFragment();

    @Nullable
    public abstract String getFragmentTag();
}
