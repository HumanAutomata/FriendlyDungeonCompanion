package character;
import java.util.List;
import java.util.Map;

public class NPCCharacterSheet extends CharacterSheet{
   
    //full constructor
    public NPCCharacterSheet(String name, String description, String dndClass, Map<String, Integer> stats, Map<String, List<Integer>> skills, List<String> attributes){
        super(name, description, dndClass, stats, skills, attributes);
    }
    
    //simpler constructor example example
    public NPCCharacterSheet(String name){
        super(name);
    }


}
