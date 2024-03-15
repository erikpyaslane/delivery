package com.food.delivery.weather_observations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

    List<WeatherObservation> findAll();
    List<WeatherObservation> findWeatherObservationsByStationName(String stationName);
    @Query(nativeQuery = true, value = "SELECT TOP 1 * FROM WeatherObservation wb " +
            "WHERE wb.stationName = :stationName ORDER BY wb.timeOfObservation DESC")
    Optional<WeatherObservation> findTopByStationNameOrderByTimeOfObservationDesc(String stationName);

    List<WeatherObservation> findTop3ByOrderByTimeOfObservationDesc();
}
