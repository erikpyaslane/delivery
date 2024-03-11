package com.food.delivery.repository;

import com.food.delivery.entity.WeatherObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

}
