package soba.weatherapp.network.models;

/**
 * Created by Levi on 5/15/16.
 */
public class Weather {
    private Integer id;
    private String main;
    private String description;
    private String icon;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}
