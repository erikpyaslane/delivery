package com.food.delivery.repository;

import com.food.delivery.entity.WeatherObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

    List<WeatherObservation> findAll();
    List<WeatherObservation> findWeatherObservationsByStationName(String stationName);

    WeatherObservation findTopByStationNameOrderByTimeOfObservationDesc(String stationName);

    List<WeatherObservation> findTop3ByOrderByTimeOfObservationDesc();
}
