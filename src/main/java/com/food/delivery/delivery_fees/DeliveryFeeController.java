package com.food.delivery.delivery_fees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    @PutMapping("/api/delivery_fee")
    @ResponseStatus(HttpStatus.OK)
    void updateRBFofCity(@Valid @RequestBody RegionalBaseFee regionalBaseFee) {
        deliveryFeeCalculationService.updateRBF(regionalBaseFee);
    }

}
