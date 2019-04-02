package com.mateakademy.fruitshop;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TradeShopTest {
    private static final String FIRST_FILE = "1stFile.json";
    private static final String SECOND_FILE = "2ndFile.json";
    private static final String DB_FILE = "DbFile.json";

    private List<Fruit> listFruits1;
    private List<Fruit> listFruits2;
    private TradeShop tradeShop;

    @Before
    public void setUp() {
        tradeShop = new TradeShop();
        listFruits1 = new ArrayList<>();
        listFruits2 = new ArrayList<>();
        LocalDate firstDate = LocalDate.of(2019, 3, 25);
        LocalDate secondDate = LocalDate.of(2019, 4, 20);
        listFruits1.add(new Fruit(TypeFruit.STRAWBERRY, 2, firstDate, 45));
        listFruits1.add(new Fruit(TypeFruit.BANANA, 2, firstDate, 30));
        listFruits1.add(new Fruit(TypeFruit.APRICOT, 4, firstDate, 55));
        listFruits1.add(new Fruit(TypeFruit.PLUM, 4, firstDate, 67));
        listFruits1.add(new Fruit(TypeFruit.PEAR, 4, firstDate, 47));
        listFruits1.add(new Fruit(TypeFruit.LEMON, 10, firstDate, 30));
        FileContent.writeToFile(listFruits1, FIRST_FILE);
        listFruits2.add(new Fruit(TypeFruit.APPLE, 12, secondDate, 25));
        listFruits2.add(new Fruit(TypeFruit.PEACH, 8, secondDate, 20));
        listFruits2.add(new Fruit(TypeFruit.STRAWBERRY, 3, secondDate, 85));
        listFruits2.add(new Fruit(TypeFruit.GRAPES, 4, secondDate, 55));
        listFruits2.add(new Fruit(TypeFruit.CHERRY, 6, secondDate, 60));
        FileContent.writeToFile(listFruits2, SECOND_FILE);
    }

    @Test
    public void testAddFruits() {
        tradeShop.addFruits(FIRST_FILE);
        List<Fruit> fruits = tradeShop.getFruits();
        assertEquals(listFruits1.size(), fruits.size());
        for (int i = 0; i < fruits.size(); i++) {
            assertEquals(listFruits1.get(i), fruits.get(i));
        }
    }

    @Test
    public void testLoad() {
        tradeShop.addFruits(FIRST_FILE);
        tradeShop.load(SECOND_FILE);
        List<Fruit> fruits = tradeShop.getFruits();
        assertEquals(listFruits2.size(), fruits.size());
        for (int i = 0; i < fruits.size(); i++) {
            assertEquals(listFruits2.get(i), fruits.get(i));
        }
    }

    @Test
    public void testGetSpoiledFruits() {
        tradeShop.addFruits(FIRST_FILE);
        LocalDate date = LocalDate.of(2019, 3, 28);
        List<Fruit> spoiledFruits = tradeShop.getSpoiledFruits(date);
        assertEquals(4, spoiledFruits.size());
        assertEquals(listFruits1.get(3), spoiledFruits.get(0));
        assertEquals(listFruits1.get(4), spoiledFruits.get(1));
    }

    @Test
    public void testGetAvailableFruits() {
        tradeShop.addFruits(FIRST_FILE);
        LocalDate date = LocalDate.of(2019, 3, 28);
        List<Fruit> availableFruits = tradeShop.getAvailableFruits(date);
        assertEquals(2, availableFruits.size());
        assertEquals(availableFruits.get(0), listFruits1.get(0));
        assertEquals(availableFruits.get(1), listFruits1.get(1));
    }

    @Test
    public void testGetSpoiledFruitsByType() {
        tradeShop.addFruits(FIRST_FILE);
        LocalDate date = LocalDate.of(2019, 3, 1);
        List<Fruit> spoiledFruits = tradeShop.getSpoiledFruits(date, TypeFruit.LEMON);
        assertEquals(1, spoiledFruits.size());
        assertEquals(spoiledFruits.get(0), listFruits1.get(5));
    }

    @Test
    public void teatGetAvailableFruitsByType() {
        tradeShop.addFruits(FIRST_FILE);
        LocalDate date = LocalDate.of(2019, 4, 1);
        List<Fruit> availableFruits = tradeShop.getAvailableFruits(date, TypeFruit.BANANA);
        assertEquals(1, availableFruits.size());
        assertEquals(availableFruits.get(0), listFruits1.get(1));
    }

    @Test
    public void testGetAddedFruits() {
        tradeShop.addFruits(FIRST_FILE);
        tradeShop.addFruits(SECOND_FILE);
        tradeShop.save(DB_FILE);
        LocalDate date = LocalDate.of(2019, 4, 20);
        List<Fruit> addedFruits = tradeShop.getAddedFruits(date);
        assertEquals(listFruits2.size(), addedFruits.size());
        for (int i = 0; i < addedFruits.size(); i++) {
            assertEquals(listFruits2.get(i), addedFruits.get(i));
        }
    }

    @Test
    public void testGetAddedFruitsByType() {
        tradeShop.addFruits(FIRST_FILE);
        tradeShop.addFruits(SECOND_FILE);
        tradeShop.save(DB_FILE);
        LocalDate date = LocalDate.of(2019, 3, 25);
        List<Fruit> addedFruits = tradeShop.getAddedFruits(date, TypeFruit.BANANA);
        assertEquals(1, addedFruits.size());
        assertEquals(addedFruits.get(0), listFruits1.get(1));
        }
}
