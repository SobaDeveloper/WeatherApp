package soba.weatherapp.domain.schedulers;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Levi on 3/23/18.
 */

public class SchedulerProvider implements BaseSchedulerProvider {

    @NonNull @Override public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull @Override public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull @Override public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull @Override public Scheduler newThread() {
        return Schedulers.newThread();
    }
}
