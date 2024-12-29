package com.example.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MainInitializer implements InitializingBean{
    private final ShopService shopService;
    private final BikeService bikeService;

    @Autowired
    public MainInitializer(ShopService shopService, BikeService bikeService) {
        this.shopService = shopService;
        this.bikeService = bikeService;
    }

    @Override
    public void afterPropertiesSet() {

        Shop shop1 = Shop.builder()
                .uuid(UUID.randomUUID())
                .name("Bike World")
                .owner("Mark")
                .build();

        Bike bike1 = Bike.builder()
                .uuid(UUID.randomUUID())
                .brand("Trek")
                .model("FX 3")
                .type("Road")
                .price(799.99)
                .build();

        Bike bike2 = Bike.builder()
                .uuid(UUID.randomUUID())
                .brand("Giant")
                .model("Escape 2")
                .type("Road")
                .price(649.99)
                .build();

        shop1.addBike(bike1);
        shop1.addBike(bike2);

        shopService.addShop(shop1);

        Shop shop2 = Shop.builder()
                .uuid(UUID.randomUUID())
                .name("Bikly")
                .owner("David")
                .build();

        Bike bike3 = Bike.builder()
                .uuid(UUID.randomUUID())
                .brand("Specialized")
                .model("Sirrus X 5.0")
                .type("MTB")
                .price(1299.99)
                .build();

        Bike bike4 = Bike.builder()
                .uuid(UUID.randomUUID())
                .brand("Cannondale")
                .model("Quick")
                .type("Road")
                .price(899.99)
                .build();

        shop2.addBike(bike3);
        shop2.addBike(bike4);
        shopService.addShop(shop2);

        System.out.println("Data initialized successfully!");
    }
}
