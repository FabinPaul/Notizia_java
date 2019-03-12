package com.fabinpaul.notizia;

public interface BasePresenter<T> {

    void start(T view);

    void stop();
}
