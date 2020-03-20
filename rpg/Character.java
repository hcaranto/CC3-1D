package rpg;

import static rpg.Character.dice;

public class Character {
    static Dice dice = new Dice();
    
    private final String name;
    private final int strength;
    final int dexterity;
    final int intelligence;
    private final int maxLife;
    int currentLife;
    int armor;

    Character (String n, int s, int d, int i){
        this.name = n;
        this.strength = s;
        this.dexterity = d;
        this.intelligence = i;
        this.maxLife = dice.roll() + 80;
        this.currentLife = maxLife;
    }
    
    Character (String n, int s, int d, int i, int a){
        this.name = n;
        this.strength = s;
        this.dexterity = d;
        this.intelligence = i;
        this.maxLife = dice.roll() + 80;
        this.currentLife = maxLife;
        this.armor = a;
    }
    
    public int attack(){
        int damage = dice.roll() + strength;
        return damage;
    }
    
    public void wound(int damage){
        currentLife -= damage;
    }
    
    public void heal(){
        int heal = dice.roll() + 10;
        currentLife += heal;
        if (currentLife > maxLife){
            currentLife = maxLife;
        }
    }
    
    public String getName(){
        return name;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public int getDexterity(){
        return dexterity;
    }
    
    public int getIntelligence(){
        return intelligence;
    }
    
    public int getMaxLife(){
        return maxLife;
    }
    
    public int getCurrentLife(){
        return currentLife;
    }
    
    
}


