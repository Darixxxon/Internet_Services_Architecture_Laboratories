package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
//@EntityScan(basePackages = "com.example.demo")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args).close();
		/*
		Shop shop1 = Shop.builder()
				.name("Bike World")
				.owner("Mark")
				.bikes(new ArrayList<>())
				.build();


		Shop shop2 = Shop.builder()
				.name("Bikly")
				.owner("David")
				.bikes(new ArrayList<>())
				.build();

		Bike bike1 = Bike.builder()
				.brand("Trek")
				.model("FX 3")
				.type("Road")
				.price(799.99)
				.build();
		shop1.addBike(bike1);

		Bike bike2 = Bike.builder()
				.brand("Giant")
				.model("Escape 2")
				.type("Road")
				.price(649.99)
				.build();
		shop1.addBike(bike2);

		Bike bike3 = Bike.builder()
				.brand("Specialized")
				.model("Sirrus X 5.0")
				.type("MTB")
				.price(1299.99)
				.build();
		shop2.addBike(bike3);

		Bike bike4 = Bike.builder()
				.brand("Cannondale")
				.model("Quick")
				.type("Road")
				.price(899.99)
				.build();
		shop2.addBike(bike4);
		*/

		//TASK 2
		/*
		List<Shop> shops = new ArrayList<>();
		shops.add(shop1);
		shops.add(shop2);

		shops.forEach(shop -> {
			System.out.println(shop);
			shop.getBikes().forEach(bike -> System.out.println("  " + bike));
		});
		*/

		//TASK 3
		/*
		List<Shop> shops = Arrays.asList(shop1, shop2);

		Set<Bike> bikeSet = shops.stream()
				.flatMap(shop -> shop.getBikes().stream())
				.collect(Collectors.toSet());

		bikeSet.stream()
				.forEach(System.out::println);
		*/

		//TASK 4
		/*
		List<Shop> shops = Arrays.asList(shop1, shop2);

		Set<Bike> bikeSet = shops.stream()
				.flatMap(shop -> shop.getBikes().stream())
				.collect(Collectors.toSet());

		bikeSet.stream()
				.filter(bike -> bike.getPrice() < 800)
				.sorted(Comparator.comparing(Bike::getPrice))
				.forEach(System.out::println);
		*/


		//TASK 5
		/*
		List<Shop> shops = Arrays.asList(shop1, shop2);

		Set<Bike> bikeSet = shops.stream()
				.flatMap(shop -> shop.getBikes().stream())
				.collect(Collectors.toSet());
		List<BikeDto> bikeDTOList = bikeSet.stream()
				.map(bike -> new BikeDto(bike.getBrand(), bike.getModel(), bike.getType(), bike.getPrice(), bike.getShop().getName()))
				.sorted()
				.toList();

		bikeDTOList.forEach(System.out::println);
		*/

		//TASK 6
		/*
		List<Shop> shops = Arrays.asList(shop1, shop2);
		try {
			FileOutputStream fos = new FileOutputStream("shops.bin");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(shops);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<Shop> serializedShopList = Collections.emptyList();
		try{
			FileInputStream fis = new FileInputStream("shops.bin");
			ObjectInputStream in = new ObjectInputStream(fis);
			serializedShopList = (List<Shop>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException();
		}

		serializedShopList.forEach(entity -> {
			System.out.println(entity);
			System.out.println("contains");
			entity.getBikes().forEach(bike -> {
				System.out.println(bike);
			});
		});
		*/

		//TASK 7
		/*
		List<Shop> shops = Arrays.asList(shop1, shop2);
		int poolSize = 2;
		ForkJoinPool customPool = new ForkJoinPool(poolSize);

		try {
			customPool.submit(() -> shops.parallelStream().forEach(shop -> {
				System.out.println("Processing shop: " + shop.getName() + " on thread: " + Thread.currentThread().getName());
				shop.getBikes().forEach(bike -> {
					try {
						System.out.println("    Processing bike: " + bike.getModel() + " on thread: " + Thread.currentThread().getName());
						Thread.sleep((int)bike.getPrice()*5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			})).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			customPool.shutdown();
		}

		System.out.println("Processing completed.");
		*/

	}

}
