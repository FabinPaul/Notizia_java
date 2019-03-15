package com.fabinpaul.notizia.network;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public final class TestSchedulers implements SchedulerProvider {

    private TestScheduler scheduler;

    public TestSchedulers() {
        this.scheduler = new TestScheduler();
    }

    @Override
    public Scheduler io() {
        return scheduler;
    }

    @Override
    public Scheduler computation() {
        return scheduler;
    }

    @Override
    public Scheduler ui() {
        return scheduler;
    }

    public TestScheduler getScheduler() {
        return scheduler;
    }
}
