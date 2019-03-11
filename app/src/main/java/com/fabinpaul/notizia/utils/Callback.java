package com.fabinpaul.notizia.utils;

public interface Callback<T> {

    void onSuccess(T response);

    void onError(String errorCode, String errorMessage);
}
