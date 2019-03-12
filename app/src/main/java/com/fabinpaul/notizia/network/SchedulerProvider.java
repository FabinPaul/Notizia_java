package com.fabinpaul.notizia.network;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler io();

    Scheduler computation();

    Scheduler ui();
}
