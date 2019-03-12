package com.fabinpaul.notizia.feature.headlines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabinpaul.notizia.R;
import com.fabinpaul.notizia.di.ActivityScoped;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class HeadlinesFragment extends DaggerFragment implements HeadlinesContract.View {

    @Inject
    private HeadlinesContract.Presenter mPresenter;

    @Inject
    public HeadlinesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        return view;
    }
}
