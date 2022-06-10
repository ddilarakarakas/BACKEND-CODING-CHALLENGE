package com.weatherproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.weatherproject.domain.Weather;
import com.weatherproject.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WeatherServiceImp implements WeatherService{

    private final WeatherRepository weatherRepository;

    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public Boolean deleteWeather(Long id) {
        /*if (!weatherRepository.findById(weather).getId().equals(weather.getId()))
            return false;*/
        if (!weatherRepository.findById(id).isPresent())
            return false;
        weatherRepository.deleteWeatherById(id);
        return true;
    }


    @Override
    public List<Weather> getWeathers(String filterJSON) throws JSONException {
        JSONObject obj = new JSONObject(filterJSON);
        Map<String, Object> filterObj = new Gson().fromJson(obj.toString(), Map.class);

        return weatherRepository.findWeathersByConditionAndLocation((String) filterObj.get("condition"), (String) filterObj.get("location"));
    }

    @Override
    public List<Weather> getAllWeathers() {
        return weatherRepository.findAll();
    }


    @Override
    public Weather updateWeather(Weather weather) {
        if (weatherRepository.findById(weather.getId()).isPresent())
            return weatherRepository.saveAndFlush(weather);
        return null;
    }
}
