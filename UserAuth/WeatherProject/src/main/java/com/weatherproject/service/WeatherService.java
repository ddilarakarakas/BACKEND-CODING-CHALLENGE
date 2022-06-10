package com.weatherproject.service;

import com.weatherproject.domain.Weather;
import org.json.JSONException;

import java.util.List;

public interface WeatherService {

    Weather saveWeather(Weather weather);

    Boolean deleteWeather(Long id);
    List<Weather> getWeathers(String filterJSON) throws JSONException;
    List<Weather> getAllWeathers();
    Weather updateWeather(Weather weather);

}
