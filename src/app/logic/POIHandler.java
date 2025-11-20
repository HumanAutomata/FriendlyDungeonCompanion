package app.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import app.logic.POI;

import java.io.FileWriter;
import java.io.FileReader;

public class POIHandler {

    private static final Gson gsonSaver = new GsonBuilder().setPrettyPrinting().create();

    public static void save(POI root, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gsonSaver.toJson(root, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Gson gsonLoader = new Gson();

    public static POI load(String filePath) {
        try (FileReader r = new FileReader(filePath)) {
            return gsonLoader.fromJson(r, POI.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}