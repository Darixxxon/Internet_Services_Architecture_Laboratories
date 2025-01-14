package com.example.demo.ShopDtos;

import lombok.*;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopCollectionDto {
    private UUID uuid;
    private String description;
}