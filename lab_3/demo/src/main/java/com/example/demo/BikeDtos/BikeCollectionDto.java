package com.example.demo.BikeDtos;

import lombok.*;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BikeCollectionDto {
    private UUID uuid;
    private String description;
}