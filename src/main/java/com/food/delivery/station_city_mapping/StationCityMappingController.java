package com.food.delivery.station_city_mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationCityMappingController {

    private StationCityMappingService stationCityMappingService;

    @Autowired
    public StationCityMappingController(StationCityMappingService stationCityMappingService) {
        this.stationCityMappingService = stationCityMappingService;
    }
}
