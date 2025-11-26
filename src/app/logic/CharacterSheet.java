package app.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.nio.file.*;

abstract class CharacterSheet{

    // public String name;
    private Map<String, String> characterInfo;
    private Map<String, String> description;
    private Map<String, Object> stats;
    private String[][] attacks;
    private Map<String, Object> spells;
    public Inventory inventory;

    // Save path for this character sheet
    protected String savePath = "state/characterSheet/characterSheet.json";
    protected String saveDir = "state/characterSheet";
    // creates a dictionary for all the base stats
    // public Map<String, Integer> baseStats = new HashMap<>() {{
    //     String[] stats = {
    //         "Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma",
    //         "Proficiency Bonus", "Armor Class", "Initiative", "Speed", 
    //         "Max Hit Points", "Hit Points", "Temp Hit Points"
    //     };
        
    //     for(String stat : stats) {
    //         put(stat, null);
    //     }
    // }};
    
    // creates a dictionary for the skills in form <name>, {<base modifier>, <proficiency bonus>, <external bonus>}
    // public Map<String, Integer[]> stats = new HashMap<String, Integer[]>() {{
        
    //     // creates an array of all the saving throws
    //     String[] savingThrows = {
    //         "Strength", "Dexterity", "Constitution", "Intelligence", "Wisedom"
    //     };
    //     // array listing the skills
    //     String[] skills = {
    //         "Acrobats", "Animal Handling", "Arcana", "Athletics", "Deception", 
    //         "History", "History", "Insight", "Intimidation", "Medicine", 
    //         "Nature", "Perception", "Performance", "Persuasion", "Religion", 
    //         "Sleight of Hand", "Stealth", "Survival"
    //     };

    //     // assigns 0 to all saving throw modifiers
    //     for(String savingThrow : savingThrows) {
    //         put(savingThrow, new Integer[] {0, 0, 0});

    //     }
    //     // assigns 0 to all saving throw modifiers
    //     for(String skill : skills) {
    //         put(skill, new Integer[] {0, 0, 0});
    //     }

    // }};
    
    
    
    //full constructor 
    // public CharacterSheet(String name, String description, String dndClass, Map<String, Integer> baseStats, List<String> attributes){
    //     this.name = name;
    //     this.description = description;
    //     this.dndClass = dndClass;
    //     // this.baseStats = baseStats;
    //     this.attributes = attributes;
    // }

    //simpler constructor
    public CharacterSheet(){
        loadFromFile();
    }

    // Constructor with custom path
    public CharacterSheet(String directory, String filename){
        this.saveDir = directory;
        this.savePath = directory + "/" + filename + ".json";
        loadFromFile();
    }

    // Load data from the current savePath
    protected void loadFromFile(){
        try {
            Files.createDirectories(Paths.get(saveDir));
            Path filePath = Paths.get(savePath);

            if (!Files.exists(filePath)) {
                // Initialize with empty data for new characters
                initializeEmpty();
                return;
            }

            // Read JSON file
            String characterJSON = Files.readString(filePath);

            // Parse to Map
            Gson gson = new Gson();
            Map<String, Object> characterMap = gson.fromJson(characterJSON, Map.class);
            Map<String, Object> characterHashMap = (HashMap) convertToNativeTypes(characterMap);

            characterInfo = (HashMap<String, String>) characterHashMap.get("characterInfo");
            description = (HashMap<String, String>) characterHashMap.get("description");
            stats = (HashMap<String, Object>)characterHashMap.get("stats");
            attacks = (String[][]) characterHashMap.get("attacks");
            spells = (HashMap<String, Object>)  characterHashMap.get("spells");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error - Failed to fetch JSON from: " + savePath);
            initializeEmpty();
        }
    }

    // Initialize empty data for new characters
    protected void initializeEmpty(){
        characterInfo = new HashMap<>();
        description = new HashMap<>();
        stats = new HashMap<>();
        attacks = new String[8][3];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 3; j++) {
                attacks[i][j] = "";
            }
        }
        spells = new HashMap<>();
    }

    // Change the save path and reload
    public void switchTo(String filename){
        this.savePath = saveDir + "/" + filename + ".json";
        loadFromFile();
    }

    // Delete this character's save file
    public void deleteFile(){
        try {
            Files.deleteIfExists(Paths.get(savePath));
            System.out.println("Deleted: " + savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateStats() {

    }
    //makes the character proficient in that skill
    // public void proficient(String skill){ 
    //     try{
    //         Integer[] skillMods = stats.get(skill);
    //         Integer profBonus = baseStats.get("Profeciency Bonus");

    //         skillMods[1] = profBonus;

    //         stats.put(skill, skillMods);
    //     } 
        
    //     catch (Exception e) {
    //         e.printStackTrace();
    //     }
            
    // }
    
    public void save(Map<String, Object> stats, Map<String, String> characterInfo, Map<String, String> description, String[][] attacks, Map<String, Object> spells) {
        this.stats = stats;
        this.characterInfo = characterInfo;
        this.description = description;
        this.attacks = attacks;
        this.spells = spells;
        saveJSON();
    }

    // saves the CharacterSheet to a json
    public void saveJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // create dictionary for the charactersheet
        Map<String, Object> characterMap = new HashMap<>();
        characterMap.put("description", description);
        characterMap.put("stats", stats);
        characterMap.put("characterInfo", characterInfo);
        characterMap.put("attacks", attacks);
        characterMap.put("spells", spells);
        characterMap.put("inventory", inventory);

        // convert to json and save
        String json = gson.toJson(characterMap);
        try {
            Files.createDirectories(Paths.get(saveDir));
            Files.write(Paths.get(savePath), json.getBytes());
            System.out.println("Successfully saved Character Sheet to: " + savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object convertToNativeTypes(Object obj) {
        if (obj instanceof LinkedTreeMap) {
            Map<String, Object> map = (Map<String, Object>) obj;
            HashMap<String, Object> hashMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), convertToNativeTypes(entry.getValue()));
            }
            return hashMap;
            
        } else if (obj instanceof List) {
            List<Object> list = (List<Object>) obj;
            
            if (list.isEmpty()) {
                return new Object[0];
            }
            
            Object firstElement = list.get(0);
            
            // String array
            if (firstElement instanceof String) {
                return list.toArray(new String[0]);
            }
            
            // Number array
            if (firstElement instanceof Number) {
                return list.toArray(new Double[0]);
            }
            
            // Boolean array
            if (firstElement instanceof Boolean) {
                return list.toArray(new Boolean[0]);
            }
            
            // List of lists (2D arrays)
            if (firstElement instanceof List) {
                List<Object> innerList = (List<Object>) firstElement;
                if (!innerList.isEmpty() && innerList.get(0) instanceof String) {
                    String[][] result = new String[list.size()][];
                    for (int i = 0; i < list.size(); i++) {
                        List<Object> inner = (List<Object>) list.get(i);
                        result[i] = inner.toArray(new String[0]);
                    }
                    return result;
                }
            }
            
            // Mixed or complex content
            Object[] array = new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = convertToNativeTypes(list.get(i));
            }
            return array;
            
        } else {
            return obj;
        }
    }

    public Map<String, String> getCharInfo(){return characterInfo;}
    public Map<String, String> getDescription(){return description;}
    public Map<String, Object> getStats(){return stats;}
    public String[][] getAttacks(){return attacks;}
    public Map<String, Object> getSpells(){return spells;}
}



