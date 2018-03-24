package soba.weatherapp.domain.models.weathermodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Levi on 5/15/16.
 */
public class Main {
    private Double temp;
    private Double pressure;
    private Integer humidity;

    @SerializedName("temp_min")
    private Double tempMin;

    @SerializedName("temp_max")
    private Double tempMax;

    private Double seaLevel;
    private Double grndLevel;

    public Double getGrndLevel() {
        return grndLevel;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getTempMax() {
        return tempMax;
    }
    public Double getTempMin() {
        return tempMin;
    }
}
