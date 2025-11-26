package app.logic;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class NPCCharacterSheet extends CharacterSheet {

    private static final String NPC_DIR = "state/npcs";
    private static final String NPC_LIST_FILE = "state/npcs/npc_list.json";

    // Default constructor - creates empty NPC
    public NPCCharacterSheet() {
        this.saveDir = NPC_DIR;
        this.savePath = NPC_DIR + "/default.json";
        initializeEmpty();
    }

    // Constructor with NPC name
    public NPCCharacterSheet(String npcName) {
        this.saveDir = NPC_DIR;
        this.savePath = NPC_DIR + "/" + sanitizeFilename(npcName) + ".json";
        loadFromFile();
    }

    // Switch to a different NPC
    public void switchToNPC(String npcName) {
        this.savePath = NPC_DIR + "/" + sanitizeFilename(npcName) + ".json";
        loadFromFile();
    }

    // Delete an NPC's save file
    public void deleteNPC(String npcName) {
        try {
            Path path = Paths.get(NPC_DIR + "/" + sanitizeFilename(npcName) + ".json");
            Files.deleteIfExists(path);
            System.out.println("Deleted NPC: " + npcName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sanitize filename to avoid illegal characters
    private String sanitizeFilename(String name) {
        return name.replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    // ==================== Static methods for NPC list management ====================

    // Load the list of NPC names from file
    public static List<String> loadNPCList() {
        try {
            Files.createDirectories(Paths.get(NPC_DIR));
            Path listPath = Paths.get(NPC_LIST_FILE);

            if (!Files.exists(listPath)) {
                return new ArrayList<>();
            }

            String json = Files.readString(listPath);
            Gson gson = new Gson();
            List<String> list = gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Save the list of NPC names to file
    public static void saveNPCList(List<String> npcNames) {
        try {
            Files.createDirectories(Paths.get(NPC_DIR));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(npcNames);
            Files.write(Paths.get(NPC_LIST_FILE), json.getBytes());
            System.out.println("Saved NPC list: " + npcNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add an NPC to the list
    public static void addToNPCList(String npcName) {
        List<String> list = loadNPCList();
        if (!list.contains(npcName)) {
            list.add(npcName);
            saveNPCList(list);
        }
    }

    // Remove an NPC from the list
    public static void removeFromNPCList(String npcName) {
        List<String> list = loadNPCList();
        list.remove(npcName);
        saveNPCList(list);
    }
}
