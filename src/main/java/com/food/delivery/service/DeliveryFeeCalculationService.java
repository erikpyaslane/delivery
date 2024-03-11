package com.food.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeCalculationService {

    private final WeatherObservationService weatherObservationService;

    public DeliveryFeeCalculationService(WeatherObservationService weatherObservationService) {
        this.weatherObservationService = weatherObservationService;
    }



    public double calculateExtraFee (double air, double wind, String phenomenon, String vehicle) throws Exception {
        double totalExtraFee = 0;

        totalExtraFee += calculateATEF(air);
        totalExtraFee += calculateWSEF(wind, vehicle);
        totalExtraFee += calculateWPEF(phenomenon);

        return totalExtraFee;
    }



    public double calculateRBF (String city, String vehicleType) {
        return 0.0;
    }

    private double calculateATEF(double air) {
        if (air < -10.0) return 1.0;
        if (air <= 0.0) return 0.5;
        return 0.0;
    }
    private double calculateWSEF(double wind, String vehicleType) throws Exception {
        if (!vehicleType.equals("Bike"))
            return 0.0;
        if (wind <= 20 && wind >= 10)
            return 0.5;
        if (wind > 20)
            throw new Exception("Usage of selected vehicle type is forbidden");
        return 0;
    }
    private double calculateWPEF(String phenomenon) {
        if (phenomenon.equals("Car"))
            return 0.0;

        return 0;
    }
}
