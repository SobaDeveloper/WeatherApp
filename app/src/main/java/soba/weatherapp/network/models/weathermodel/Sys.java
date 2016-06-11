package soba.weatherapp.network.models.weathermodel;

/**
 * Created by Levi on 5/15/16.
 */
public class Sys {
    private Double message;
    private String country;
    private Integer sunrise;
    private Integer sunset;

    public String getCountry() {
        return country;
    }

    public Double getMessage() {
        return message;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }
}
