package com.food.delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ConstantFee {
    @Id
    private long id;
    private String city;
    private String vehicle;
    private double RBF;
}
