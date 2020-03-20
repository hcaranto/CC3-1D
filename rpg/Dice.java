package rpg;

import java.util.Random;

public class Dice {
    
    private final Random r;
    
    public Dice(){
        this.r = new Random();
    }
    
    public int roll(){
        int diceRoll = r.nextInt(6)+1;
        return diceRoll;
    }
    
    public int rolld8(){
        int diceRoll = r.nextInt(7)+1;
        return diceRoll;
    }
    
}
