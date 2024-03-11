package com.food.delivery.controller;

import com.food.delivery.service.DeliveryFeeCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryFeeController {
    private final DeliveryFeeCalculationService deliveryFeeCalculationService;

    public DeliveryFeeController(DeliveryFeeCalculationService deliveryFeeCalculationService) {
        this.deliveryFeeCalculationService = deliveryFeeCalculationService;
    }

}
