package soba.weatherapp.utils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import soba.weatherapp.utils.WeatherFontHelper;

/**
 * Created by Levi on 5/31/16.
 */
public class WeatherIconTextView extends TextView {

    public WeatherIconTextView(Context context){
        super(context, null);
        WeatherFontHelper.setWeatherTypeface(context, this);
    }

    public WeatherIconTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        WeatherFontHelper.setWeatherTypeface(context, this);
    }

    public WeatherIconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
       WeatherFontHelper.setWeatherTypeface(context, this);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}