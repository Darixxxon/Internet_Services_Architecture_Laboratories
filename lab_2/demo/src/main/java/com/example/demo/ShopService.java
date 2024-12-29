package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Optional<Shop> findById(UUID id) {
        return shopRepository.findById(id);
    }


    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }


    public List<Shop> listAllShops() {
        return shopRepository.findAll();
    }
}
