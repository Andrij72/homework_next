package com.mateakademy.fruitshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Fruit {
    private TypeFruit typeFruit;
    private int shelfLife;
    private LocalDate date;
    private int price;
}
