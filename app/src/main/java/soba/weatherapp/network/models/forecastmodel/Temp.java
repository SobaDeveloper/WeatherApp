package soba.weatherapp.network.models.forecastmodel;

/**
 * Created by Levi on 5/16/16.
 */
public class Temp {
    private Double day;
    private Double min;
    private Double max;
    private Double night;
    private Double eve;
    private Double morn;

    public Double getDay() {
        return day;
    }

    public Double getEve() {
        return eve;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMorn() {
        return morn;
    }

    public Double getNight() {
        return night;
    }
}
