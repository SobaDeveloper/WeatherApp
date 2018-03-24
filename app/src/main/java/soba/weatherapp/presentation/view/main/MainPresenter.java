package soba.weatherapp.presentation.view.main;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import soba.weatherapp.data.remote.repository.WeatherRepository;
import soba.weatherapp.domain.schedulers.SchedulerProvider;
import soba.weatherapp.domain.models.CombinedData;
import soba.weatherapp.domain.models.forecastmodel.ForecastData;
import soba.weatherapp.domain.models.weathermodel.WeatherData;
import soba.weatherapp.presentation.common.RxPresenter;

/**
 * Created by Levi on 3/24/18.
 */

public class MainPresenter extends RxPresenter<MainContract.View>
    implements MainContract.Presenter {

    private final WeatherRepository weatherRepository;
    private final SchedulerProvider schedulerProvider;

    public MainPresenter(MainContract.View view, WeatherRepository repository,
        SchedulerProvider provider) {
        super(view);
        this.weatherRepository = repository;
        this.schedulerProvider = provider;
    }

    @Override public void getDataByCity(String city, String unit) {
        Disposable combined =
            Observable.zip(getWeatherByCity(city, unit), getForecastByCity(city, unit),
                CombinedData::new)
                .compose(applySchedulers(schedulerProvider))
                .subscribeWith(new DisposableObserver<CombinedData>() {
                    @Override public void onNext(CombinedData combinedData) {
                        view.onDataByCityRetrieved(combinedData);
                    }

                    @Override public void onError(Throwable e) {
                        view.onDataByCityFailed();
                    }

                    @Override public void onComplete() {
                    }
                });
        addDisposable(combined);
    }

    @Override public void getDataByLocation(String lat, String lon, String unit) {
        Disposable combined = Observable.zip(getWeatherByLocation(lat, lon, unit),
            getForecastByLocation(lat, lon, unit), CombinedData::new)
            .compose(applySchedulers(schedulerProvider))
            .subscribeWith(new DisposableObserver<CombinedData>() {
                @Override public void onNext(CombinedData combinedData) {
                    view.onDataByLocationRetrieved(combinedData);
                }

                @Override public void onError(Throwable e) {
                    view.onDataByLocationFailed();
                }

                @Override public void onComplete() {
                }
            });
        addDisposable(combined);
    }

    private Observable<WeatherData> getWeatherByCity(String selectedCity, String unit) {
        return weatherRepository.getWeatherByCity(selectedCity, unit);
    }

    private Observable<ForecastData> getForecastByCity(String selectedCity, String unit) {
        return weatherRepository.getForecastByCity(selectedCity, unit);
    }

    private Observable<WeatherData> getWeatherByLocation(String lat, String lon, String unit) {
        return weatherRepository.getWeatherByLocation(lat, lon, unit);
    }

    private Observable<ForecastData> getForecastByLocation(String lat, String lon, String unit) {
        return weatherRepository.getForecastByLocation(lat, lon, unit);
    }

    @Override public void unsubscribe() {
        dispose();
    }
}
