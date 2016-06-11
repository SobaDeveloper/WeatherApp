package soba.weatherapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soba.weatherapp.R;
import soba.weatherapp.adapters.WeatherAdapter;
import soba.weatherapp.network.models.weathermodel.WeatherData;

/**
 * Created by SobaDeveloper on 6/6/16.
 */
public class WeatherFragment extends Fragment {

    private WeatherAdapter mWeatherAdapter;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherAdapter = new WeatherAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mWeatherAdapter);
    }

    public void updateData(WeatherData weatherData) {
        mWeatherAdapter.setWeatherData(weatherData);
    }
}
