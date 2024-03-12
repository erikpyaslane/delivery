package com.food.delivery.controller;

import com.food.delivery.entity.WeatherObservation;
import com.food.delivery.service.WeatherObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather_observations")
public class WeatherObservationController {

    private final WeatherObservationService weatherObservationService;

    @Autowired
    public WeatherObservationController(WeatherObservationService weatherObservationService) {
        this.weatherObservationService = weatherObservationService;
    }

    @GetMapping
    public List<WeatherObservation> getAllWeatherObservations() {
        return weatherObservationService.getAllWeatherObservations();
    }

    @GetMapping("/current")
    public List<WeatherObservation> getCurrentWeatherObservations(){
        weatherObservationService.updateWeatherData();
        return weatherObservationService.getLatestWeatherObservations();
    }

    @GetMapping("/{cityName}")
    public List<WeatherObservation> getWeatherObservationsByCityName(@PathVariable String cityName) {
        System.out.println(cityName);
        return weatherObservationService.getObservationsByCityName(cityName);
    }
    @GetMapping("/{cityName}_latest")
    public WeatherObservation getWeatherObservationByCityName(@PathVariable String cityName) {
        System.out.println(cityName);
        return weatherObservationService.getLatestObservationByCityName(cityName);
    }
}
