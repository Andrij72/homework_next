package com.mateakademy.fruitshop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.txw2.output.XmlSerializer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileContent {

    private static final Logger LOGGER = Logger.getLogger(XmlSerializer.class.getName());
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy").create();

    public static void writeToFile(List list, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static List<Fruit> readFromFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            Type fruitListType = new TypeToken<ArrayList<Fruit>>() {
            }.getType();
            return gson.fromJson(bufferedReader, fruitListType);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
}
