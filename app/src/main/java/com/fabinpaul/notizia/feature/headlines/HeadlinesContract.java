package com.fabinpaul.notizia.feature.headlines;

import com.fabinpaul.notizia.BasePresenter;
import com.fabinpaul.notizia.BaseView;

public interface HeadlinesContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
