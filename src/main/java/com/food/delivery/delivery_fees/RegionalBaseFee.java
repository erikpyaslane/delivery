package com.food.delivery.delivery_fees;

import com.food.delivery.enums.VehicleType;
import jakarta.persistence.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regional_base_fees")
public class RegionalBaseFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "city_name", unique = true)
    @NotEmpty
    @NotNull
    private String cityName;
    @Column(name = "vehicle_type")
    @NotEmpty
    @NotNull
    private VehicleType vehicleType;
    @Column
    @NotNull
    @DecimalMin(value = "0.0", message = "Base fee value cannot be negative")
    private double baseFeeValue;


    public RegionalBaseFee() {
    }

    public RegionalBaseFee(String cityName, VehicleType vehicleType, double baseFeeValue) {
        this.cityName = cityName;
        this.vehicleType = vehicleType;
        this.baseFeeValue = baseFeeValue;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBaseFeeValue() {
        return baseFeeValue;
    }

    public void setBaseFeeValue(double baseFeeValue) {
        this.baseFeeValue = baseFeeValue;
    }
}
