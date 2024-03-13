package com.food.delivery.business_rules;

import com.food.delivery.enums.VehicleType;
import com.food.delivery.enums.WeatherConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessRuleService {

    private final BusinessRuleRepository businessRuleRepository;

    @Autowired
    public BusinessRuleService(BusinessRuleRepository businessRuleRepository) {
        this.businessRuleRepository = businessRuleRepository;
    }

    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleRepository.findAll();
    }

    public List<BusinessRule> getBusinessRulesByVehicleType(String vehicleType) {
        VehicleType convertedVehicleType = null;
        try {
             convertedVehicleType = VehicleType.valueOf(vehicleType);
        } catch (IllegalArgumentException e) {
            System.out.println("This vehicle type does not exist!");
        }
        return getBusinessRulesByVehicleType(convertedVehicleType);
    }

    private List<BusinessRule> getBusinessRulesByVehicleType(VehicleType vehicleType) {
        return businessRuleRepository.findBusinessRulesByVehicleType(vehicleType);
    }

    public List<BusinessRule> getBusinessRulesByWeatherConditionType(String weatherConditionType) {
        WeatherConditionType convertedWeatherConditionType = null;
        try {
            convertedWeatherConditionType = WeatherConditionType.valueOf(weatherConditionType);
        } catch (IllegalArgumentException e) {
            System.out.println("This vehicle type does not exist!");
        }
        return businessRuleRepository
                .findBusinessRulesByWeatherConditionType(convertedWeatherConditionType);
    }

    public List<BusinessRule> getBusinessRulesByVehicleTypeAndWeatherConditionType(
            VehicleType vehicleType, WeatherConditionType weatherConditionType) {
        return businessRuleRepository
                .findBusinessRulesByVehicleTypeAndWeatherConditionType(vehicleType, weatherConditionType);
    }

    public BusinessRule getBusinessRuleByVehicleTypeAndWeatherConditionTypeAndRangeValue(
            VehicleType vehicleType, WeatherConditionType weatherConditionType, double rangeValue) {
        return businessRuleRepository
                .findBusinessRuleByVehicleTypeAndWeatherConditionTypeAndRangeValue(
                        vehicleType, weatherConditionType, rangeValue
                );
    }

    public BusinessRule saveBusinessRule(BusinessRule businessRule) {
        return businessRuleRepository.save(businessRule);
    }
}
