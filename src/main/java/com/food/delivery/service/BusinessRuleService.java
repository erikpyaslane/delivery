package com.food.delivery.service;

import com.food.delivery.entity.BusinessRule;
import com.food.delivery.entity.VehicleType;
import com.food.delivery.entity.WeatherConditionType;
import com.food.delivery.repository.BusinessRuleRepository;
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
        return businessRuleRepository.findBusinessRulesByVehicleType(convertedVehicleType);
    }

    private List<BusinessRule> getBusinessRulesByVehicleType(VehicleType vehicleType) {
        return businessRuleRepository.findBusinessRulesByVehicleType(vehicleType);
    }

    public BusinessRule saveBusinessRule(BusinessRule businessRule) {
        return businessRuleRepository.save(businessRule);
    }


    public List<BusinessRule> getBusinessRulesByVehicleTypeAndWeatherConditionType(
            VehicleType vehicleType, WeatherConditionType weatherConditionType) {
        return businessRuleRepository.findBusinessRulesByVehicleTypeAndWeatherConditionType(vehicleType, weatherConditionType);
    }
}
