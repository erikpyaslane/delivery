package com.food.delivery.delivery_fees;

import com.food.delivery.delivery_fees.RegionalBaseFee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RegionalBaseFeeRepository extends JpaRepository<RegionalBaseFee, Long> {

    RegionalBaseFee findRegionalBaseFeeByCityName(String cityName);
    @Modifying
    @Transactional
    @Query("UPDATE RegionalBaseFee r SET " +
            "r.scooterRBF = COALESCE(:scooterRbf, r.scooterRBF), " +
            "r.bikeRBF = COALESCE(:bikeRbf, r.bikeRBF), " +
            "r.carRBF = COALESCE(:carRbf, r.carRBF) " +
            "WHERE r.cityName=:name")
    void updateRBFOfCity(String name, double carRbf, double scooterRbf, double bikeRbf);

    boolean existsRegionalBaseFeeByCityName(String cityName);

}
