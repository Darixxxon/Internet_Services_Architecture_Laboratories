package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Optional<Bike> findById(UUID id) {
        return bikeRepository.findById(id);
    }

    public Optional<Bike> findByShop(String shop) {
        return bikeRepository.findByShop(shop);
    }

    public Optional<Bike> findByType(String type) {
        return bikeRepository.findByType(type);
    }

    public void deleteById(UUID id) {
        bikeRepository.deleteById(id);
    }

    @Transactional
    public void deleteByType(String type) {
        bikeRepository.deleteByModel(type);
    }

    public void addBike(Bike bike) {
        bikeRepository.save(bike);
    }

    public List<Bike> listAllBikes() {
        return bikeRepository.findAll();
    }
}
