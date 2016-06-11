package soba.weatherapp.network.models.forecastmodel;

import soba.weatherapp.network.models.Coord;

/**
 * Created by Levi on 5/16/16.
 */
public class City {

    private Integer id;
    private String name;
    private Coord coord;
    private String country;
    private Integer population;

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }
}
