package com.food.delivery.business_rules;

import com.food.delivery.business_rules.BusinessRule;
import com.food.delivery.enums.PhenomenonType;
import com.food.delivery.enums.VehicleType;
import com.food.delivery.enums.WeatherConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessRuleRepository extends JpaRepository<BusinessRule, Long> {
    List<BusinessRule> findBusinessRulesByVehicleType(VehicleType vehicleType);
    List<BusinessRule> findBusinessRulesByWeatherConditionType(WeatherConditionType weatherConditionType);
    List<BusinessRule> findBusinessRulesByVehicleTypeAndWeatherConditionType(
            VehicleType vehicleType , WeatherConditionType weatherConditionType);
    @Query("SELECT br FROM BusinessRule br WHERE " +
            "br.vehicleType=:vehicleType AND br.weatherConditionType = :weatherConditionType " +
            "AND br.minValueOfRange >= :rangeValue AND br.maxValueOfRange <= :rangeValue")
    BusinessRule findBusinessRuleByVehicleTypeAndWeatherConditionTypeAndRangeValue(
            VehicleType vehicleType , WeatherConditionType weatherConditionType, double rangeValue
    );
    @Query("SELECT br FROM BusinessRule br WHERE " +
            "br.vehicleType=:vehicleType AND br.weatherConditionType = 'WPEF' " +
            "AND br.phenomenonType = :phenomenon_type")
    BusinessRule findBusinessRuleByVehicleTypeAndPhenomenonType(
            VehicleType vehicleType, PhenomenonType phenomenonType
    );
}
