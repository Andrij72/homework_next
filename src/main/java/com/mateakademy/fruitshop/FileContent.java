package com.mateakademy.fruitshop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.txw2.output.XmlSerializer;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class FileContent {

    private static final Logger LOGGER = Logger.getLogger(XmlSerializer.class.getName());
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy").create();

    public static void writeToFile (List list, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            GSON.toJson(list, writer);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public static List <Fruit> readFromFile (String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            Type fruitListType = new TypeToken <ArrayList <Fruit>>() {
            }.getType();
            return GSON.fromJson(bufferedReader, fruitListType);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        //return new ArrayList (0);
        return Collections.EMPTY_LIST;
    }
}
