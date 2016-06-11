package soba.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import soba.weatherapp.R;
import soba.weatherapp.network.models.weathermodel.WeatherData;
import soba.weatherapp.utils.Constants;
import soba.weatherapp.utils.SharedPreferenceManager;
import soba.weatherapp.utils.WeatherUtil;
import soba.weatherapp.views.WeatherDetailLayout;

/**
 * Created by SobaDeveloper on 5/19/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private static final String TAG = WeatherAdapter.class.getSimpleName();
    private static int WEATHERVIEW = 0;
    private static int DETAILVIEW = 1;
    private Context mContext;
    private WeatherData mWeatherData = new WeatherData();
    private SharedPreferenceManager prefs;

    public WeatherAdapter(Context context) {
        mContext = context;
        prefs = SharedPreferenceManager.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? WEATHERVIEW : DETAILVIEW;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == WEATHERVIEW) {
            return new WeatherViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_weather, parent, false));
        }
        else if (viewType == DETAILVIEW) {
            return new DetailViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_details, parent, false));
        }
        else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        if (viewHolder.getItemViewType() == WEATHERVIEW) {
            WeatherViewHolder holder = (WeatherViewHolder) viewHolder;
            setWeatherIcon(holder);
            holder.tvCity.setText(mWeatherData.getName());
            if (mWeatherData.getWeather().size() != 0) {
                holder.tvCondition.setText(mWeatherData.getWeather().get(0).getDescription());
            }
            if (mWeatherData.getMain() != null) {
                holder.tvTemp.setText(WeatherUtil.getTempString(mWeatherData.getMain().getTemp(),
                        prefs.isUnitMetric()));
            }
        }
        else if (viewHolder.getItemViewType() == DETAILVIEW) {
            DetailViewHolder holder = (DetailViewHolder) viewHolder;
            holder.llDetails.removeAllViews();
            populateDetails(holder);
        }
    }

    private void populateDetails(DetailViewHolder holder) {

        holder.llDetails.removeAllViewsInLayout();

        if (mWeatherData.getMain().getHumidity() != null) {
            WeatherDetailLayout humidityDetail = createDetailLayout(WeatherUtil.getHumidityIcon(),
                    "Humidity", String.valueOf(mWeatherData.getMain().getHumidity()) + Constants.DETAIL_LABEL_HUMIDITY);
            holder.llDetails.addView(humidityDetail);
        }

        if (mWeatherData.getMain().getPressure() != null) {
            WeatherDetailLayout pressureDetail = createDetailLayout(WeatherUtil.getPressureIcon(),
                    "Pressure", String.valueOf(mWeatherData.getMain().getPressure())
                            + Constants.DETAIL_LABEL_PRESSURE);
            holder.llDetails.addView(pressureDetail);
        }

        if (mWeatherData.getWind().getSpeed() != null) {
            WeatherDetailLayout windDetail = createDetailLayout(WeatherUtil.getWindIcon(),
                    "Wind", String.valueOf(mWeatherData.getWind().getSpeed()) + prefs.getWindSpeedUnit());
            holder.llDetails.addView(windDetail);
        }

        if (mWeatherData.getSys().getSunrise() != null) {
            WeatherDetailLayout sunriseDetail = createDetailLayout(WeatherUtil.getSunriseIcon(),
                    "Sunrise", WeatherUtil.getTime(mWeatherData.getSys().getSunrise()));
            holder.llDetails.addView(sunriseDetail);
        }

        if (mWeatherData.getSys().getSunset() != null) {
            WeatherDetailLayout sunsetDetail = createDetailLayout(WeatherUtil.getSunsetIcon(),
                    "Sunset", WeatherUtil.getTime(mWeatherData.getSys().getSunset()));
            holder.llDetails.addView(sunsetDetail);
        }
    }

    private WeatherDetailLayout createDetailLayout(String icon, String label, String value) {
        WeatherDetailLayout weatherDetailLayout = new WeatherDetailLayout(mContext);
        weatherDetailLayout.setIcon(icon);
        weatherDetailLayout.setLabel(label);
        weatherDetailLayout.setValue(value);
        return weatherDetailLayout;
    }

    private void setWeatherIcon(WeatherViewHolder holder) {
        if (mWeatherData.getWeather().size() > 0) {
            int id = (mWeatherData.getWeather().get(0).getId());
            holder.tvIcon.setText(WeatherUtil.getWeatherIcon(id));
        }
    }

    public void setWeatherData(WeatherData weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    class WeatherViewHolder extends ViewHolder {
        private TextView tvIcon;
        private TextView tvCity;
        private TextView tvCondition;
        private TextView tvTemp;

        public WeatherViewHolder(View v) {
            super(v);
            tvIcon = (TextView) v.findViewById(R.id.tv_icon);
            tvCity = (TextView) v.findViewById(R.id.tv_city);
            tvCondition = (TextView) v.findViewById(R.id.tv_condition);
            tvTemp = (TextView) v.findViewById(R.id.tv_temp);
        }
    }

    class DetailViewHolder extends ViewHolder {
        private LinearLayout llDetails;

        public DetailViewHolder(View v) {
            super(v);
            llDetails = (LinearLayout) v.findViewById(R.id.ll_details);
        }
    }
}
