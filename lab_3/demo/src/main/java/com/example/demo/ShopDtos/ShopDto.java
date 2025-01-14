package com.example.demo.ShopDtos;

import com.example.demo.BikeDtos.BikeDto;
import lombok.*;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class ShopDto implements Comparable<ShopDto>{
    private String name;
    private String owner;

    @Override
    public int compareTo(ShopDto other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "ShopDto{name='" + name + "'owner='" + owner +"'}";
    }
}
