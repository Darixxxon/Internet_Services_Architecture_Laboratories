package com.example.demo.BikeDtos;

import lombok.*;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BikeCreateUpdateDto {
    private String brand;
    private String model;
    private String type;
    private double price;
    private UUID shopUuid;
}
