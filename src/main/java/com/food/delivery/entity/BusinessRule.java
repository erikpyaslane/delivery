package com.food.delivery.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "business_rules")
public class BusinessRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name="vehicle_type")
    private VehicleType vehicleType;

    @Column(name="min_value")
    private double minValueOFRange;
    @Column(name="max_value")
    private double maxValueOfRange;
    @Column(name="extra_fee_value")
    private double extraFeeValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather_condition_type")
    private WeatherConditionType weatherConditionType;

    public BusinessRule() {
    }

    public BusinessRule(VehicleType vehicleType, WeatherConditionType weatherConditionType, double minValueOFRange, double maxValueOfRange, double extraFeeValue) {
        this.vehicleType = vehicleType;
        this.weatherConditionType = weatherConditionType;
        this.minValueOFRange = minValueOFRange;
        this.maxValueOfRange = maxValueOfRange;
        this.extraFeeValue = extraFeeValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMinValueOFRange() {
        return minValueOFRange;
    }

    public void setMinValueOFRange(double minValueOFRange) {
        this.minValueOFRange = minValueOFRange;
    }

    public double getMaxValueOfRange() {
        return maxValueOfRange;
    }

    public void setMaxValueOfRange(double maxValueOfRange) {
        this.maxValueOfRange = maxValueOfRange;
    }

    public double getExtraFeeValue() {
        return extraFeeValue;
    }

    public void setExtraFeeValue(double extraFeeValue) {
        this.extraFeeValue = extraFeeValue;
    }

    public WeatherConditionType getWeatherConditionType() {
        return weatherConditionType;
    }

    public void setWeatherConditionType(WeatherConditionType weatherConditionType) {
        this.weatherConditionType = weatherConditionType;
    }
}
