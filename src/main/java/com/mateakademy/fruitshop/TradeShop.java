package com.mateakademy.fruitshop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradeShop {
    private List <Fruit> fruits = new ArrayList <>();

    public List <Fruit> getFruits () {
        return fruits;
    }

    public void addFruits (String pathToJsonFile) {
        fruits.addAll(FileContent.readFromFile(pathToJsonFile));
    }

    public void save (String pathToJsonFile) {
        FileContent.writeToFile(fruits, pathToJsonFile);
    }

    public void load (String pathToJsonFile) {
        fruits.clear();
        addFruits(pathToJsonFile);
    }

    private boolean isSpoiled (Fruit fruit, LocalDate date) {
        return fruit.getDate().plusDays(fruit.getShelfLife()).isAfter(date);
    }

    public List <Fruit> getSpoiledFruits (LocalDate date) {
        return fruits.stream().
                filter(fruit -> isSpoiled(fruit, date)).
                collect(Collectors.toList());
    }

    public List <Fruit> getAvailableFruits (LocalDate date) {
        return fruits.stream().
                filter(fruit -> !isSpoiled(fruit, date)).
                collect(Collectors.toList());
    }

    public List <Fruit> getSpoiledFruits (LocalDate date, TypeFruit type) {
        return  fruits.stream().
                filter(fruit -> isSpoiled(fruit, date)).
                collect(Collectors.toList());
    }

    public List <Fruit> getAvailableFruits (LocalDate date, TypeFruit type) {
        return  fruits.stream().
                filter(fruit -> fruit.getTypeFruit().equals(type)).
                filter(fruit -> !isSpoiled(fruit, date)).
                collect(Collectors.toList());
    }

    public List <Fruit> getAddedFruits (LocalDate date) {
        return  fruits.stream().
                filter(fruit -> fruit.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List <Fruit> getAddedFruits (LocalDate date, TypeFruit type) {
        return  fruits.stream().
                filter(fruit -> fruit.getTypeFruit().equals(type) && fruit.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
