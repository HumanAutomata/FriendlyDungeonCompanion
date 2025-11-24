package app.logic;

import java.util.HashMap;
// import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.*;

abstract class CharacterSheet{

    // public String name;
    private Map<String, String> description;
    // public String dndClass;
    private Map<String, Object> stats;
    private Map<String, String> characterInfo;
    private String[][] attacks;
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
    
    public List<String> attributes;
    public Inventory inventory;
    
    //full constructor 
    // public CharacterSheet(String name, String description, String dndClass, Map<String, Integer> baseStats, List<String> attributes){
    //     this.name = name;
    //     this.description = description;
    //     this.dndClass = dndClass;
    //     // this.baseStats = baseStats;
    //     this.attributes = attributes;
    // }

    //simpler constructor
    public CharacterSheet(){}

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
    
    public void save(Map<String, Object> stats, Map<String, String> characterInfo, Map<String, String> description, String[][] attacks) {
        this.stats = stats;
        this.characterInfo = characterInfo;
        this.description = description;
        this.attacks = attacks;
        saveJSON();
    }

    // saves the CharacterSheet to a json
    public void saveJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // create dictionary for the charactersheet
        Map<String, Object> characterMap = new HashMap<>();
        // characterMap.put("name", name);
        characterMap.put("description", description);
        // characterMap.put("class", dndClass);
        // characterMap.put("baseStats", baseStats);
        characterMap.put("stats", stats);
        characterMap.put("characterInfo", characterInfo);
        characterMap.put("attacks", attacks);
        characterMap.put("inventory", inventory);

        // convert to json and save
        String json = gson.toJson(characterMap);
        try {
            Files.createDirectories(Paths.get("state/characterSheet"));
            Files.write(Paths.get("state/characterSheet/stats.json"), json.getBytes());
            System.out.println("Successfully saved Character Sheet");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateStat(String stat) {
        
    }
}



