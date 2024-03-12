package com.food.delivery.repository;

import com.food.delivery.entity.BusinessRule;
import com.food.delivery.entity.VehicleType;
import com.food.delivery.entity.WeatherConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessRuleRepository extends JpaRepository<BusinessRule, Long> {
    List<BusinessRule> findBusinessRulesByVehicleType(VehicleType vehicleType);
    List<BusinessRule> findBusinessRulesByVehicleTypeAndWeatherConditionType(
            VehicleType vehicleType , WeatherConditionType weatherConditionType);
}
