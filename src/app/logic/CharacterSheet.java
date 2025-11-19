package app.logic;
import java.util.HashMap;
// import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.nio.file.*;

abstract class CharacterSheet{

    public String name;
    public String description;
    public String dndClass;

    // creates a dictionary for all the base stats
    public Map<String, Integer> baseStats = new HashMap<>(Map.of(
        "Strength", null,
        "Dexterity", null,
        "Constitution", null,
        "Intelligence", null,
        "Wisdom", null,
        "Charisma", null,
        "Proficiency Bonus", null
        ));
    
    // creates a dictionary for the skills in form <name>, {<base modifier>, <proficiency bonus>, <external bonus>}
    public Map<String, Integer[]> stats = new HashMap<String, Integer[]>() {{
        
        // creates an array of all the saving throws
        String[] savingThrows = {
            "Strength", "Dexterity", "Constitution", "Intelligence", "Wisedom"
        };
        // array listing the skills
        String[] skills = {
            "Acrobats", "Animal Handling", "Arcana", "Athletics", "Deception", 
            "History", "History", "Insight", "Intimidation", "Medicine", 
            "Nature", "Perception", "Performance", "Persuasion", "Religion", 
            "Sleight of Hand", "Stealth", "Survival"
        };

        // assigns 0 to all saving throw modifiers
        for(String savingThrow : savingThrows) {
            put(savingThrow, new Integer[] {0, 0, 0});
        }
        // assigns 0 to all saving throw modifiers
        for(String skill : skills) {
            put(skill, new Integer[] {0, 0, 0});
        }

    }};
    
    public List<String> attributes;
    public Inventory inventory;
    
    //full constructor 
    public CharacterSheet(String name, String description, String dndClass, Map<String, Integer> baseStats, List<String> attributes){
        this.name = name;
        this.description = description;
        this.dndClass = dndClass;
        this.baseStats = baseStats;
        this.attributes = attributes;
    }

    //simpler constructor
    public CharacterSheet(String name){
        this.name = name;
    }

    public void calculateStats() {
        
    }

    //makes the character proficient in that skill
    public void proficient(String skill){ 
        Integer[] skillMods = stats.get(skill);
        Integer profBonus = baseStats.get("Profeciency Bonus");

        skillMods[1] = profBonus;

        stats.put(skill, skillMods);
    }
}



