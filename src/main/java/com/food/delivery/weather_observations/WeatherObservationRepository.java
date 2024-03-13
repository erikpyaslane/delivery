package com.food.delivery.weather_observations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

    List<WeatherObservation> findAll();
    List<WeatherObservation> findWeatherObservationsByStationName(String stationName);

    WeatherObservation findTopByStationNameOrderByTimeOfObservationDesc(String stationName);

    List<WeatherObservation> findTop3ByOrderByTimeOfObservationDesc();
}
