package com.fabinpaul.notizia.network;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler ui();
}
