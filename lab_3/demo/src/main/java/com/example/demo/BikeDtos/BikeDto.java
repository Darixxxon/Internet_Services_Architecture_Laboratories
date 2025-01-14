package com.example.demo.BikeDtos;

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

    @Override
    public int compareTo(BikeDto other) {
        return this.model.compareTo(other.model);
    }

    @Override
    public String toString() {
        return "BikeDto{brand='" + brand + "'model='" + model + "'type='" + type + "', price=" + price + ", shopName='" + shopName + "'}";
    }
}
