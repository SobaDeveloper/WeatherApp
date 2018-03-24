package soba.weatherapp.presentation.common;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import soba.weatherapp.domain.schedulers.BaseSchedulerProvider;

/**
 * Created by Levi on 3/24/18.
 */

public abstract class RxPresenter<V> {

    protected final V view;
    private CompositeDisposable disposables;

    protected RxPresenter(V view) {
        this.view = view;
        disposables = new CompositeDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    protected void dispose() {
        disposables.clear();
    }

    protected <T> ObservableTransformer<T, T> applySchedulers(BaseSchedulerProvider schedulerProvider) {
        return observable -> observable.subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.mainThread());
    }
}
