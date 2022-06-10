package com.weatherproject.repository;

import com.weatherproject.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findById(Long Id);

    void deleteWeatherById(Long id);
    List<Weather> findWeathersByConditionAndLocation(String condition, String location);



}
