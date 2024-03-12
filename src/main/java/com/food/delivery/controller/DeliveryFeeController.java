package com.food.delivery.controller;

import com.food.delivery.service.DeliveryFeeCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryFeeController {
    private final DeliveryFeeCalculationService deliveryFeeCalculationService;

    public DeliveryFeeController(DeliveryFeeCalculationService deliveryFeeCalculationService) {
        this.deliveryFeeCalculationService = deliveryFeeCalculationService;
    }
    @GetMapping("/api/delivery_fee/rbf")
    public double getVehicleRBF(@RequestParam("cityName") String cityName,
                                @RequestParam("vehicleType") String vehicleType) {
        return deliveryFeeCalculationService.getCalculationFee(cityName, vehicleType);
    }

}
