package com.example.demo.BikeDtos;

import lombok.*;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BikeReadDto {
    private UUID uuid;
    private String brand;
    private String model;
    private String type;
    private double price;
    private String shopName;
}
