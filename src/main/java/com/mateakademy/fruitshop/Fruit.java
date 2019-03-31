package com.mateakademy.fruitshop;
import java.time.LocalDate;

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

    public TypeFruit getTypeFruit() {
        return typeFruit;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Fruit fruit = (Fruit) object;
        return typeFruit == fruit.typeFruit && (shelfLife == fruit.shelfLife)
                && (price == fruit.price) && (date.equals(this.date));
    }
}
