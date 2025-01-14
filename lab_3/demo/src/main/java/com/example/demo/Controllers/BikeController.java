package com.example.demo.Controllers;

import com.example.demo.BaseClasses.Bike;
import com.example.demo.BaseClasses.Shop;
import com.example.demo.BikeDtos.BikeCollectionDto;
import com.example.demo.BikeDtos.BikeCreateUpdateDto;
import com.example.demo.BikeDtos.BikeReadDto;
import com.example.demo.Services.BikeService;
import com.example.demo.Services.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;
    private final ShopService shopService;

    public BikeController(BikeService bikeService, ShopService shopService) {
        this.bikeService = bikeService;
        this.shopService = shopService;
    }

    // Create a new bike
    @PostMapping
    public ResponseEntity<?> createBike(@RequestBody BikeCreateUpdateDto bikeDto) {
        Optional<Shop> shopOpt = shopService.findById(bikeDto.getShopUuid());
        if (shopOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Shop not found");
        }

        Bike bike = Bike.builder()
                .uuid(UUID.randomUUID())
                .brand(bikeDto.getBrand())
                .model(bikeDto.getModel())
                .type(bikeDto.getType())
                .price(bikeDto.getPrice())
                .shop(shopOpt.get())
                .build();

        bikeService.addBike(bike);
        return ResponseEntity.status(201).body("Bike created successfully");
    }

    // Get all bikes
    @GetMapping
    public ResponseEntity<List<BikeCollectionDto>> getAllBikes() {
        List<BikeCollectionDto> bikes = bikeService.listAllBikes().stream()
                .map(b -> new BikeCollectionDto(b.getUuid(), b.getBrand() + " - " + b.getModel() + " - " + b.getType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bikes);
    }

    // Get bikes by shop
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<?> getBikesByShop(@PathVariable UUID shopId) {
        Optional<Shop> shopOpt = shopService.findById(shopId);
        if (shopOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Shop not found");
        }

        List<BikeCollectionDto> bikes = shopOpt.get().getBikes().stream()
                .map(b -> new BikeCollectionDto(b.getUuid(), b.getBrand() + " - " + b.getModel() + " - " + b.getType()))
                .collect(Collectors.toList());
        return bikes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(bikes);
    }

    // Get a single bike by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBikeById(@PathVariable UUID id) {
        return bikeService.findById(id)
                .map(b -> ResponseEntity.ok(new BikeReadDto(
                        b.getUuid(),
                        b.getBrand(),
                        b.getModel(),
                        b.getType(),
                        b.getPrice(),
                        b.getShop().getName())))
                .orElseGet(() -> ResponseEntity.status(404).body(new BikeReadDto(null, "N/A", "N/A", "N/A", 0, "N/A")));
    }


    // Update a bike
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBike(@PathVariable UUID id, @RequestBody BikeCreateUpdateDto bikeDto) {
        Optional<Bike> bikeOpt = bikeService.findById(id);
        if (bikeOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Bike not found");
        }

        Bike bike = bikeOpt.get();
        bike.setBrand(bikeDto.getBrand());
        bike.setModel(bikeDto.getModel());
        bike.setType(bikeDto.getType());
        bike.setPrice(bikeDto.getPrice());

        Optional<Shop> shopOpt = shopService.findById(bikeDto.getShopUuid());
        if (shopOpt.isPresent()) {
            bike.setShop(shopOpt.get());
        }

        bikeService.addBike(bike);
        return ResponseEntity.ok("Bike updated successfully");
    }

    // Delete a bike by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBikeById(@PathVariable UUID id) {
        Optional<Bike> bikeOpt = bikeService.findById(id);
        if (bikeOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Bike not found");
        }

        bikeService.deleteById(id);
        return ResponseEntity.ok("Bike deleted successfully");
    }
}
