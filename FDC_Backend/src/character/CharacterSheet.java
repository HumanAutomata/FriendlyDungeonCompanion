package character;
// import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;
import java.util.Map;

abstract class CharacterSheet{

    public String name;
    public String description;
    public String dndClass;
    public Map<String, Integer> stats;
    public Map<String, List<Integer>> skills;
    public List<String> attributes;
    public Inventory inventory;
    
    //full constructor 
    public CharacterSheet(String name, String description, String dndClass, Map<String, Integer> stats, Map<String, List<Integer>> skills, List<String> attributes){
        this.name = name;
        this.description = description;
        this.dndClass = dndClass;
        this.stats = stats;
        this.attributes = attributes;
    }

    //simpler constructor
    public CharacterSheet(String name){
        this.name = name;
    }

    public void calculateStats() {

    }
}



