package soba.weatherapp.presentation.view.main;

import soba.weatherapp.domain.models.CombinedData;

/**
 * Created by Levi on 3/24/18.
 */

public interface MainContract {

    interface View {

        void onDataByCityRetrieved(CombinedData data);

        void onDataByCityFailed();

        void onDataByLocationRetrieved(CombinedData data);

        void onDataByLocationFailed();
    }

    interface Presenter {

        void getDataByCity(String cityName, String unit);

        void getDataByLocation(String lat, String lon, String unit);

        void unsubscribe();
    }
}
