import java.util.List;
import java.util.Map;

public class NPCCharacterSheet extends CharacterSheet{
   
    //full constructor
    public NPCCharacterSheet(String name, String desc, String dndClass, Map<String, Integer> stats, Map<String, List<Integer>> skills, List<String> attributes){
        super(name, desc, dndClass, stats, skills, attributes);
    }
    
    //simpler constructor example example
    public NPCCharacterSheet(String name){
        super(name);
    }


}
