package com.food.delivery.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "regional_base_fees")
public class RegionalBaseFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "scooter_rbf")
    private double scooterRBF;
    @Column(name = "bike_rbf")
    private double bikeRBF;
    @Column(name = "car_rbf")
    private double carRbf;


    public RegionalBaseFee() {
    }

    public RegionalBaseFee(String cityName, double scooterRBF, double bikeRBF, double carRbf) {
        this.cityName = cityName;
        this.scooterRBF = scooterRBF;
        this.bikeRBF = bikeRBF;
        this.carRbf = carRbf;
    }

    public String getCityName() {
        return cityName;
    }

    public double getScooterRBF() {
        return scooterRBF;
    }

    public void setScooterRBF(double scooterRBF) {
        this.scooterRBF = scooterRBF;
    }

    public double getBikeRBF() {
        return bikeRBF;
    }

    public void setBikeRBF(double bikeRBF) {
        this.bikeRBF = bikeRBF;
    }

    public double getCarRbf() {
        return carRbf;
    }

    public void setCarRbf(double carRbf) {
        this.carRbf = carRbf;
    }
}
