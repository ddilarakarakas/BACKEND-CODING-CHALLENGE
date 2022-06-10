package com.weatherproject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weatherproject.domain.ApiUser;
import com.weatherproject.domain.Weather;
import com.weatherproject.service.ApiUserService;
import com.weatherproject.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@Slf4j
public class WeatherProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherProjectApplication.class, args);
    }
    @Bean
    CommandLineRunner run(ApiUserService apiUserService, WeatherService weatherService){
        return args -> {

            apiUserService.saveUser(new ApiUser(null, "admin", "password", "USER_ADMIN"));
            //weatherService.saveWeather(new Weather(null, "cond",null, "Istanbul", "22"));


            /*for (long i = 1; i < 100; i++) {
                weatherService.deleteWeather(i);
            }*/
            //weatherService.saveWeather(new Weather(null, "rainy", dateTime, "Istanbul", "22"));
            // apiUserService.saveUser(new ApiUser(null, "User2", "123", "USER"));
            // apiUserService.saveUser(new ApiUser(null, "User3", "123", "CONTROLLER"));


        };

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
