package app.logic;
// import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;
import java.util.Map;

public class PlayerCharacterSheet extends CharacterSheet{
    
    //full constructor
    public PlayerCharacterSheet(String name, String description, String dndClass, Map<String, Integer> baseStats, List<String> attributes){
        super(name, description, dndClass, baseStats, attributes);
    }
    
    //simpler constructor
    public PlayerCharacterSheet(String name){
        super(name);
    }

    //function to view stat breakdown (still needs to be coded)
    public Map<String, Integer> viewStatBreakdown(){
        return this.baseStats;
    }
}
