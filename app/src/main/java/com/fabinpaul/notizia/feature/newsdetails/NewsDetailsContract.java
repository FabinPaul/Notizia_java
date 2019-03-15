package com.fabinpaul.notizia.feature.newsdetails;

import com.fabinpaul.notizia.BasePresenter;
import com.fabinpaul.notizia.BaseView;

public interface NewsDetailsContract {

    interface View extends BaseView<Presenter> {

        void loadNewsUrlInView(String newsURL);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
