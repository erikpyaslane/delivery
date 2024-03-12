package com.food.delivery.controller;

import com.food.delivery.entity.BusinessRule;
import com.food.delivery.service.BusinessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessRuleController {

    private final BusinessRuleService businessRuleService;
    @Autowired
    public BusinessRuleController(BusinessRuleService businessRuleService) {
        this.businessRuleService = businessRuleService;
    }

    @GetMapping("/api/business_rules")
    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleService.getAllBusinessRules();
    }

    @PostMapping("/api/business_rules")
    public ResponseEntity<BusinessRule> createBusinessRule(@RequestBody BusinessRule businessRule) {
        BusinessRule createdBusinessRule = businessRuleService.saveBusinessRule(businessRule);
        return new ResponseEntity<>(createdBusinessRule, HttpStatus.CREATED);
    }
}
