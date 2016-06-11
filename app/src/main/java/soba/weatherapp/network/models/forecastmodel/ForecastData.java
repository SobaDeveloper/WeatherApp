package soba.weatherapp.network.models.forecastmodel;

import java.util.ArrayList;

/**
 * Created by Levi on 5/16/16.
 */
public class ForecastData {

    private City city;
    private String cod;
    private Double message;
    private Integer cnt;
    private java.util.List<List> list = new ArrayList<>();

    public City getCity() {
        return city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public String getCod() {
        return cod;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public Double getMessage() {
        return message;
    }
}
