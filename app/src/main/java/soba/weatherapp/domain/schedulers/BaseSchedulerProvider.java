package soba.weatherapp.domain.schedulers;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;

/**
 * Created by Levi on 3/23/18.
 */

public interface BaseSchedulerProvider {

    @NonNull Scheduler computation();

    @NonNull Scheduler io();

    @NonNull Scheduler mainThread();

    @NonNull Scheduler newThread();
}
