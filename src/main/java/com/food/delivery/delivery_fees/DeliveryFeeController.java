package com.food.delivery.delivery_fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/delivery_fee")
public class DeliveryFeeController {
    private final DeliveryFeeCalculationService deliveryFeeCalculationService;

    @Autowired
    public DeliveryFeeController(DeliveryFeeCalculationService deliveryFeeCalculationService) {
        this.deliveryFeeCalculationService = deliveryFeeCalculationService;
    }
    @GetMapping("/rbf")
    public double getVehicleRBF(@RequestParam("cityName") String cityName,
                                @RequestParam("vehicleType") String vehicleType) {
        return deliveryFeeCalculationService.getCalculationFee(cityName, vehicleType);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void updateRBFofCity(@Valid @RequestBody RegionalBaseFee regionalBaseFee) {
        deliveryFeeCalculationService.updateRBF(regionalBaseFee);
    }

}
