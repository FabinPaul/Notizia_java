package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.BasePresenter;
import com.fabinpaul.notizia.BaseView;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;

import java.util.List;

public interface HeadlinesContract {

    interface View extends BaseView<Presenter> {

        void showNewsHeadlines(List<ArticlesItem> articlesItems);

        void showLoadingDialog(boolean visible);

        void showErrorDialog(String title, String message);

        void showNewsDetails(String newsURL);
    }

    interface Presenter extends BasePresenter<View> {

        void onNewsArticleClick(ArticlesItem articlesItem);

        void updateNewsHeadlines();
    }
}
