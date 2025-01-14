package com.example.demo.ShopDtos;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopCreateUpdateDto {
    private String name;
    private String owner;
}