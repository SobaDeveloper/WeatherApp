package soba.weatherapp.presentation.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soba.weatherapp.R;
import soba.weatherapp.presentation.adapters.ForecastAdapter;
import soba.weatherapp.domain.models.forecastmodel.ForecastData;

/**
 * Created by SobaDeveloper on 6/1/16.
 */
public class ForecastFragment extends Fragment {

    private ForecastAdapter mForecastAdapter;

    public static ForecastFragment newInstance() {
        return new ForecastFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecastAdapter = new ForecastAdapter(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mForecastAdapter);
    }

    public void updateData(ForecastData forecastData) {
        mForecastAdapter.setForecastData(forecastData);
    }
}
