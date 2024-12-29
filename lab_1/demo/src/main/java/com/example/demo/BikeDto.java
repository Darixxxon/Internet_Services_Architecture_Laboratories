package com.example.demo;

import lombok.*;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class BikeDto implements Comparable<BikeDto>{
    private String brand;
    private String model;
    private String type;
    private double price;
    private String shopName;


    public BikeDto(String brand, String model,String type, double price, String shopName) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.price = price;
        this.shopName = shopName;
    }

    @Override
    public int compareTo(BikeDto other) {
        return this.model.compareTo(other.model);
    }


    @Override
    public String toString() {
        return "BikeDto{brand='" + brand + "'model='" + model + "'type='" + type + "', price=" + price + ", shopName='" + shopName + "'}";
    }
}
