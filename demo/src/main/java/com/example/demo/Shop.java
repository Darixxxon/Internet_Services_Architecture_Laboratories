package com.example.demo;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Shops")
public class Shop implements Serializable, Comparable<Shop>{
    //private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column
    private String name;

    @Column
    private String owner;

    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Bike> bikes = new ArrayList<>();

    @Autowired
    public void addBike(Bike bike) {
        bikes.add(bike);
        bike.setShop(this);
    }

    @Override
    public int compareTo(Shop other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Shop{name='" + name + "', owner='" + owner + "'}";
    }

}
