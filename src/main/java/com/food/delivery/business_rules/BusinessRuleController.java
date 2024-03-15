package com.food.delivery.business_rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business_rules")
public class BusinessRuleController {

    private final BusinessRuleService businessRuleService;
    @Autowired
    public BusinessRuleController(BusinessRuleService businessRuleService) {
        this.businessRuleService = businessRuleService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleService.getAllBusinessRules();
    }
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BusinessRule> getBusinessRulesByVehicleType(@RequestParam String vehicleType) {
        return businessRuleService.getBusinessRulesByVehicleType(vehicleType);
    }

    @PostMapping
    public ResponseEntity<BusinessRule> createBusinessRule(@RequestBody BusinessRule businessRule) {
        BusinessRule createdBusinessRule = businessRuleService.saveBusinessRule(businessRule);
        return new ResponseEntity<>(createdBusinessRule, HttpStatus.CREATED);
    }
}
