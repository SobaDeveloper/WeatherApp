package soba.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import soba.weatherapp.R;
import soba.weatherapp.network.models.forecastmodel.ForecastData;
import soba.weatherapp.network.models.forecastmodel.List;
import soba.weatherapp.utils.SharedPreferenceManager;
import soba.weatherapp.utils.WeatherUtil;

/**
 * Created by Levi on 6/2/16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private Context mContext;
    private ForecastData mForecastData = new ForecastData();
    private SharedPreferenceManager prefs;

    public ForecastAdapter(Context context) {
        mContext = context;
        prefs = SharedPreferenceManager.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mForecastData.getList().size();
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_forecast, parent, false));
    }

    @Override
    public void onBindViewHolder(ForecastAdapter.ViewHolder holder, int position) {
        if(mForecastData.getList() != null){
            List forecastItem = mForecastData.getList().get(position);
            holder.tvIcon.setText(WeatherUtil.getWeatherIcon(forecastItem.getWeather().get(0).getId()));
            holder.tvWeekday.setText(WeatherUtil.getWeekDay(forecastItem.getDt()));
            holder.tvCondition.setText(forecastItem.getWeather().get(0).getDescription());
            holder.tvTempMin.setText(WeatherUtil.getTempString(forecastItem.getTemp().getMin(), prefs.isUnitMetric()));
            holder.tvTempMax.setText(WeatherUtil.getTempString(forecastItem.getTemp().getMax(), prefs.isUnitMetric()));
        }
    }

    public void setForecastData(ForecastData forecastData) {
        mForecastData = forecastData;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIcon;
        private TextView tvWeekday;
        private TextView tvCondition;
        private TextView tvTempMin;
        private TextView tvTempMax;

        public ViewHolder(View v) {
            super(v);
            tvIcon = (TextView) v.findViewById(R.id.tv_icon);
            tvWeekday = (TextView) v.findViewById(R.id.tv_weekday);
            tvCondition = (TextView) v.findViewById(R.id.tv_condition);
            tvTempMin = (TextView) v.findViewById(R.id.tv_temp_min);
            tvTempMax = (TextView) v.findViewById(R.id.tv_temp_max);
        }
    }
}
