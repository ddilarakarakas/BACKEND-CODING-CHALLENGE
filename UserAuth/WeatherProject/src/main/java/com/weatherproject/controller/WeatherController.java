package com.weatherproject.controller;

import com.weatherproject.domain.Weather;
import com.weatherproject.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping(value = "weather/")
    public ResponseEntity<List<Weather>> getWeather(@RequestBody String filter) throws JSONException {
        return ResponseEntity.ok().body(weatherService.getWeathers(filter));
    }

    @PostMapping(value = "weather/save")
    public ResponseEntity<Weather> saveWeather(@RequestBody Weather weather){
        return ResponseEntity.ok().body(weatherService.saveWeather(weather));
    }

    @DeleteMapping(value = "weather/delete")
    public ResponseEntity<Boolean> deleteWeather(@RequestParam Long id){
        Boolean result = weatherService.deleteWeather(id);
        if (result)
            return ResponseEntity.ok().body(true);
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "weather/update")
    public ResponseEntity<Weather> updateWeather(@RequestBody Weather weather){
        Weather weather1 = weatherService.updateWeather(weather);
        if (weather1 == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok().body(weather1);
    }

    @PostMapping(value = "weather/bulk")
    public ResponseEntity bulkWeatherCreate(@RequestParam Long size){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        //LocalDateTime dateTime = LocalDateTime.parse("1800-09-22T12:30:10", formatter);

        for (long i = 1; i < size; i++) {

            log.info(String.valueOf(i));
            Random choice = new Random();
            String location = null;
            String temperature = null;
            String condition = null;
            Integer month = choice.nextInt(11) + 1;
            String m = null;
            if (month < 10)
                m = "0" + month.toString();
            else
                m = month.toString();
            String y = Integer.toString(choice.nextInt(1022) + 1000);

            String d = Integer.toString(choice.nextInt(17) + 10);
            String h = Integer.toString(choice.nextInt(14) + 10);
            String min = Integer.toString(choice.nextInt(50) + 10);
            String s = Integer.toString(choice.nextInt(50) + 10);
            String time = y + "-" + m + "-" + d + "T" + h + ":" + min + ":" + s;
            switch (choice.nextInt(3)){
                case 0: location = "Istanbul";break;
                case 1: location = "Kocaeli";break;
                case 2: location = "Sakarya";break;
                case 3: location = "Edirne";break;
            }
            temperature = Integer.toString(choice.nextInt(25));
            Integer temp = Integer.parseInt(temperature);
            if (temp < 5)
                condition = "snowy";
            else if (temp < 10) {
                condition = "rainy";
            }
            else if (temp < 15) {
                condition = "windy";
            }
            else if (temp < 20) {
                condition = "cloudy";
            }
            else condition = "sunny";

            weatherService.saveWeather(new Weather(null, condition, LocalDateTime.parse(time, formatter), location, temperature));


        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "weather/get")
    public ResponseEntity<List<Weather>> getAllWeathers(){
        return ResponseEntity.ok().body(weatherService.getAllWeathers());
    }

}
