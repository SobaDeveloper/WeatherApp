package soba.weatherapp.domain.models.forecastmodel;

import java.util.ArrayList;

import soba.weatherapp.domain.models.Weather;

/**
 * Created by Levi on 5/16/16.
 */
public class List {

    private Integer dt;
    private Temp temp;
    private Double pressure;
    private Integer humidity;
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    private Double speed;
    private Integer deg;
    private Integer clouds;
    private Double rain;

    public Integer getClouds() {
        return clouds;
    }

    public Integer getDeg() {
        return deg;
    }

    public Integer getDt() {
        return dt;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getRain() {
        return rain;
    }

    public Double getSpeed() {
        return speed;
    }

    public Temp getTemp() {
        return temp;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }
}
