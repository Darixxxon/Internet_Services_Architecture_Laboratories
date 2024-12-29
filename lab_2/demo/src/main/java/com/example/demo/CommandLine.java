package com.example.demo;


import ch.qos.logback.core.net.ObjectWriter;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommandLine implements CommandLineRunner {
    private final BikeService bikeService;
    private final ShopService shopService;

    @Autowired
    public CommandLine(BikeService bikeService, ShopService shopService) {
        this.bikeService = bikeService;
        this.shopService = shopService;
    }

    public void ls_shops() {
        for (Shop shop : shopService.listAllShops()) {
            System.out.println(shop);
        }
    }

    public void ls_bikes() {
        for (Bike bike : bikeService.listAllBikes()) {
            System.out.print(bike);
            System.out.print("  shop_uuid=");
            System.out.println(bike.getShop().getUuid());
        }
    }

    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while (true) {
            System.out.print("s&c>");
            command = scanner.nextLine();
            switch (command) {
                case "ls bikes" -> {
                    ls_bikes();
                }
                case "ls shops" -> {
                    ls_shops();
                }


                case "add bike" -> {
                    Bike newBike = new Bike();
                    newBike.setUuid(UUID.randomUUID());
                    System.out.print("producer=");
                    newBike.setBrand(scanner.nextLine());
                    System.out.print("model=");
                    newBike.setModel(scanner.nextLine());
                    System.out.print("macNumber=");
                    newBike.setType(scanner.nextLine());
                    System.out.print("memory=");
                    newBike.setPrice(Double.parseDouble(scanner.nextLine()));
                    System.out.println("s&c>ls shops");

                    List<Shop> shops = shopService.listAllShops();
                    ListIterator<Shop> shopIterator = shops.listIterator();

                    while (shopIterator.hasNext()) {
                        System.out.print(shopIterator.nextIndex());
                        System.out.print(")  ");
                        System.out.println(shopIterator.next());
                    }
                    System.out.print("shop=");
                    int pickedShop = Integer.parseInt(scanner.nextLine());
                    newBike.setShop(shops.get(pickedShop));

                    bikeService.addBike(newBike);
                }
                case "rm bike" -> {
                    System.out.print("type=");
                    String type = scanner.nextLine();
                    Optional<Bike> bike = bikeService.findByType(type);
                    if (bike.isPresent()) {
                        bikeService.deleteByType(type);
                        System.out.println("Bike removed.");
                    } else {
                        System.out.print("Bike ");
                        System.out.print(type);
                        System.out.print(" not found");
                    }
                }

                case "exit" -> {
                    System.out.println("shutting down...");
                    break main_loop;
                }
                default -> {
                    System.out.print("Unknown command");
                    System.out.print(command);
                    System.out.println("Available commands are:");
                    System.out.println("    ls bikes");
                    System.out.println("    ls shops");
                    System.out.println("    add bike");
                    System.out.println("    delete bike");
                    System.out.println("    ls shops");

                    System.out.println("exit");

                }
            }
        }
    }
}
