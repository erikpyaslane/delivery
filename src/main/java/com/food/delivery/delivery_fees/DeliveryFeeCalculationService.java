package com.food.delivery.delivery_fees;


import com.food.delivery.business_rules.BusinessRule;
import com.food.delivery.business_rules.BusinessRuleService;
import com.food.delivery.enums.VehicleType;
import com.food.delivery.enums.WeatherConditionType;
import com.food.delivery.exceptions.NoSuchVehicleTypeException;
import com.food.delivery.weather_observations.WeatherObservation;
import com.food.delivery.weather_observations.WeatherObservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryFeeCalculationService {

    private final WeatherObservationService weatherObservationService;
    private final BusinessRuleService businessRuleService;
    private final RegionalBaseFeeRepository regionalBaseFeeRepository;

    public DeliveryFeeCalculationService(WeatherObservationService weatherObservationService,
                                         BusinessRuleService businessRuleService,
                                         RegionalBaseFeeRepository regionalBaseFeeRepository) {
        this.weatherObservationService = weatherObservationService;
        this.businessRuleService = businessRuleService;
        this.regionalBaseFeeRepository = regionalBaseFeeRepository;
    }

    public void saveRGB(String cityName, String scooterRGB, String bikeRGB, String carRGB) {
        System.out.println("Hi!");
    }

    public double getCalculationFee(String cityName, String vehicleType) {

        VehicleType convertedVehicleType = null;
        double totalFee = 0.0;

        try {
            convertedVehicleType = VehicleType.valueOf(vehicleType);
            totalFee = calculateTotalFee(cityName, convertedVehicleType);
        } catch (IllegalArgumentException e) {
            throw new NoSuchVehicleTypeException("No such vehicle type");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(convertedVehicleType);
        return totalFee;
    }

    public double calculateTotalFee(String city, VehicleType vehicleType) throws Exception {
        return calculateRBF(city, vehicleType);
    }

    private double calculateRBF (String cityName, VehicleType vehicleType) {
        RegionalBaseFee city = regionalBaseFeeRepository.findRegionalBaseFeeByCityName(cityName);

        return 0.0;
    }

    private double calculateExtraFee (WeatherObservation weatherObservation, VehicleType vehicleType) throws Exception {
        double totalExtraFee = 0;

        totalExtraFee += calculateATEF(weatherObservation.getAirTemperature());
        totalExtraFee += calculateWSEF(weatherObservation.getWindSpeed(), vehicleType);
        totalExtraFee += calculateWPEF(weatherObservation.getWeatherPhenomenon());

        return totalExtraFee;
    }

    private double calculateATEF(double air) {
        if (air < -10.0) return 1.0;
        if (air <= 0.0) return 0.5;
        return 0.0;
    }
    private double calculateWSEF(double wind, VehicleType vehicleType) throws Exception {
        double fee = 0.0;
        List<BusinessRule> businessRules = null;
        businessRules = businessRuleService
                .getBusinessRulesByVehicleTypeAndWeatherConditionType(vehicleType, WeatherConditionType.WSEF);

        return 0;
    }
    private double calculateWPEF(String phenomenon) {
        if (phenomenon.equals("Car"))
            return 0.0;

        return 0;
    }


    public void updateRBF(RegionalBaseFee updatedDetails) {
        System.out.println("In progress " + updatedDetails);

    }
}
