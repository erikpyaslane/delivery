package com.food.delivery.repository;

import com.food.delivery.entity.RegionalBaseFee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryFeeRepository extends JpaRepository<RegionalBaseFee, Long> {

    RegionalBaseFee findRegionalBaseFeeByCityName(String cityName);
    @Modifying
    @Transactional
    @Query("UPDATE RegionalBaseFee r SET " +
            "r.scooterRBF = COALESCE(:scooterRbf, r.scooterRBF), " +
            "r.bikeRBF = COALESCE(:bikeRbf, r.bikeRBF), " +
            "r.carRbf = COALESCE(:carRbf, r.carRbf) " +
            "WHERE r.cityName=:name")
    void updateRBFOfCity(String name, double carRbf, double scooterRbf, double bikeRbf);

}
