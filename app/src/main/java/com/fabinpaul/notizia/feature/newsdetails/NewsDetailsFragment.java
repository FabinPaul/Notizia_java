package com.fabinpaul.notizia.feature.newsdetails;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.fabinpaul.notizia.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class NewsDetailsFragment extends DaggerFragment implements NewsDetailsContract.View {

    public static final String TAG = "NewsDetailsFragment";

    @Inject
    NewsDetailsContract.Presenter mPresenter;
    @Inject
    String mNewsURL;

    private WebView mWebView;

    @Inject
    public NewsDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        mWebView = view.findViewById(R.id.newsDetailWebView);
        Toolbar toolbar = view.findViewById(R.id.newsDetailsToolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mPresenter.start(this);
        return view;
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
