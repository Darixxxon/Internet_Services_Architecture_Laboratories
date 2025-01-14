package com.example.demo.BaseClasses;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.UUID;


@Builder
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Bikes")
public class Bike implements Serializable, Comparable<Bike> {
    @Id
    private UUID uuid;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String type;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "shop", referencedColumnName = "id")
    @ToString.Exclude
    private Shop shop;

    @Override
    public int compareTo(Bike other) {
        return this.type.compareTo(other.type);
    }

    @Override
    public String toString() {
        return "Bike{UUID-'" + uuid + "'brand-'" + brand + "'model='" + model + "'type='" + type + "', price=" + price + "}";
    }
}