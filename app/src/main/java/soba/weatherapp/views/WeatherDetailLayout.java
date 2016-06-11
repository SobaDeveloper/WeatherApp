package soba.weatherapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import soba.weatherapp.R;

/**
 * Created by Levi on 5/31/16.
 */
public class WeatherDetailLayout extends LinearLayout {

    private WeatherIconTextView mIcon;
    private TextView mLabel;
    private TextView mValue;

    public WeatherDetailLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public WeatherDetailLayout(Context context){
        this(context, null);

        setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,16);
        setLayoutParams(params);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_weather_detail, this, true);

        mIcon = (WeatherIconTextView) findViewById(R.id.tv_icon);
        mLabel = (TextView) findViewById(R.id.tv_label);
        mValue = (TextView) findViewById(R.id.tv_value);
    }

    public void setIcon(String icon){
        mIcon.setText(icon);
    }

    public void setLabel(String label){
        mLabel.setText(label);
    }

    public void setValue(String value){
        mValue.setText(value);
    }
}
