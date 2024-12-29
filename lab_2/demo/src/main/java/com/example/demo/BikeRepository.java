package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BikeRepository extends JpaRepository<Bike, UUID> {
    Optional<Bike> findByShop(String shop);
    Optional<Bike> findByType(String type);
    void deleteByModel(String model);
}