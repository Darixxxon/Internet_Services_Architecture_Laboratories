package com.example.demo.ShopDtos;

import lombok.*;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopReadDto {
    private UUID uuid;
    private String name;
    private String owner;
}