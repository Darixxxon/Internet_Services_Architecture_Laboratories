package com.example.demo.Controllers;

import com.example.demo.BaseClasses.Shop;
import com.example.demo.Services.ShopService;
import com.example.demo.ShopDtos.ShopCollectionDto;
import com.example.demo.ShopDtos.ShopCreateUpdateDto;
import com.example.demo.ShopDtos.ShopReadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // Create a new shop
    @PostMapping
    public ResponseEntity<?> createShop(@RequestBody ShopCreateUpdateDto shopDto) {
        Shop shop = Shop.builder()
                .uuid(UUID.randomUUID())
                .name(shopDto.getName())
                .owner(shopDto.getOwner())
                .build();

        shopService.addShop(shop);
        return ResponseEntity.status(201).body("Shop created successfully");
    }

    // Get all shops
    @GetMapping
    public ResponseEntity<List<ShopCollectionDto>> getAllShops() {
        List<ShopCollectionDto> shops = shopService.listAllShops().stream()
                .map(s -> new ShopCollectionDto(s.getUuid(), s.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shops);
    }

    // Get a single shop by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getShopById(@PathVariable UUID id) {
        return shopService.findById(id)
                .map(shop -> ResponseEntity.ok(new ShopReadDto(
                        shop.getUuid(),
                        shop.getName(),
                        shop.getOwner())))
                .orElse(ResponseEntity.status(404).body(new ShopReadDto(null, "Not Found", "N/A")));
    }

    // Update a shop
    @PutMapping("/{id}")
    public ResponseEntity<?> updateShop(@PathVariable UUID id, @RequestBody ShopCreateUpdateDto shopDto) {
        Optional<Shop> shopOpt = shopService.findById(id);
        if (shopOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Shop not found");
        }

        Shop shop = shopOpt.get();
        shop.setName(shopDto.getName());
        shop.setOwner(shopDto.getOwner());

        shopService.addShop(shop);
        return ResponseEntity.ok("Shop updated successfully");
    }

    // Delete a shop and its bikes
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShopById(@PathVariable UUID id) {
        Optional<Shop> shopOpt = shopService.findById(id);
        if (shopOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Shop not found");
        }

        shopService.listAllShops().removeIf(shop -> shop.getUuid().equals(id));
        return ResponseEntity.ok("Shop and its bikes deleted successfully");
    }
}
