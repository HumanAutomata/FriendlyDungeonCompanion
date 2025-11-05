import java.util.List;
import java.util.Map;

public class PlayerCharacterSheet extends CharacterSheet{
    
    //full constructor
    public PlayerCharacterSheet(String name, String desc, String dndClass, Map<String, Integer> stats, Map<String, List<Integer>> skills, List<String> attributes){
        super(name, desc, dndClass, stats, skills, attributes);
    }
    
    //simpler constructor
    public PlayerCharacterSheet(String name){
        super(name);
    }

    //function to view stat breakdown (still needs to be coded)
    public Map<String, Integer> viewStatBreakdown(){
        return this.stats;
    }
}
