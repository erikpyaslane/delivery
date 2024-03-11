package com.food.delivery.controller;

import com.food.delivery.entity.WeatherObservation;
import com.food.delivery.service.WeatherObservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/weather_observations")
public class WeatherObservationController {

    private final WeatherObservationService weatherObservationService;


    public WeatherObservationController(WeatherObservationService weatherObservationService) {
        this.weatherObservationService = weatherObservationService;
    }
    @GetMapping
    public List<WeatherObservation> getWeatherObservations(){
        //weatherObservationService.processWeatherData();
        return weatherObservationService.getAllObservations();
    }
}
