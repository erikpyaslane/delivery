package com.food.delivery.service;


import com.food.delivery.entity.*;
import com.food.delivery.exception.NoSuchVehicleTypeException;
import com.food.delivery.repository.DeliveryFeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryFeeCalculationService {

    private final WeatherObservationService weatherObservationService;
    private final BusinessRuleService businessRuleService;
    private final DeliveryFeeRepository deliveryFeeRepository;

    public DeliveryFeeCalculationService(WeatherObservationService weatherObservationService,
                                         BusinessRuleService businessRuleService,
                                         DeliveryFeeRepository deliveryFeeRepository) {
        this.weatherObservationService = weatherObservationService;
        this.businessRuleService = businessRuleService;
        this.deliveryFeeRepository = deliveryFeeRepository;
    }

    public void saveRGB(String cityName, String scooterRGB, String bikeRGB, String carRGB) {

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

        //WeatherObservation weatherObservation = weatherObservationService.getLatestObservationByCityName(city);

        //double extraFee = calculateExtraFee(weatherObservation, vehicleType);
        return calculateRBF(city, vehicleType);
    }

    private double calculateRBF (String cityName, VehicleType vehicleType) {
        RegionalBaseFee city = deliveryFeeRepository.findRegionalBaseFeeByCityName(cityName);

        return switch (vehicleType) {
            case BIKE -> city.getBikeRBF();
            case CAR -> city.getCarRbf();
            case SCOOTER -> city.getScooterRBF();
        };
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


}
