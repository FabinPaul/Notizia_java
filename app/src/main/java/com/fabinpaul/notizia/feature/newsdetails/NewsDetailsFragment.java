package com.fabinpaul.notizia.feature.newsdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.fabinpaul.notizia.HomeActivity;
import com.fabinpaul.notizia.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class NewsDetailsFragment extends DaggerFragment implements NewsDetailsContract.View {

    @Inject
    NewsDetailsContract.Presenter mPresenter;
    @Inject
    String mNewsURL;

    private WebView mWebView;

    @Inject
    public NewsDetailsFragment() {
    }

    public static NewsDetailsFragment newInstance(Bundle args) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        mWebView = view.findViewById(R.id.newsDetailWebView);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stop();
    }

    @Override
    public void loadNewsUrlInView(String newsURL) {
        mWebView.loadUrl(newsURL);
    }
}
