package soba.weatherapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by SobaDeveloper on 5/31/16.
 * Singleton for applying font to avoid creating typeface from assets each time
 */
public class WeatherFontHelper {

    private static Typeface typeface = null;

    public static void setWeatherTypeface(Context context, TextView textview) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");
        }
        textview.setTypeface(typeface);
    }
}
