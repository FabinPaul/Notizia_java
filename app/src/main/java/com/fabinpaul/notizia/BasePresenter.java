package com.fabinpaul.notizia;

import androidx.annotation.NonNull;

public interface BasePresenter<T> {

    void start(@NonNull T view);

    void stop();
}
