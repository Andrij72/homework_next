package com.mateakademy.fruitshop;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Fruit {
    private TypeFruit typeFruit;
    private int shelfLife;
    private LocalDate date;
    private int price;

    public Fruit(TypeFruit typeFruit, int shelfLife, LocalDate date, int price) {
        this.typeFruit = typeFruit;
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
    }
}
