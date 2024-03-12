package com.food.delivery.entity;

public enum VehicleType {
    BIKE, SCOOTER, CAR;

    public static boolean contains(String value) {
        for (VehicleType type : values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
