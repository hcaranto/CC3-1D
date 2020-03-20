package rpg;

public class Mage extends Character{
    
    private int manaPoints;
    
    public Mage(String n, int s, int d) {
        super(n, s, d, 25, 2);
        manaPoints = 20;
    }
    
    public int getMana(){
        return manaPoints;
    }
    
    public int fireball(){
        int damage = dice.roll() + intelligence;
        manaPoints -= 5;
        return damage;
    }
    
    @Override
    public void wound(int damage){
        if (damage >= armor){
            currentLife -= (damage - armor);
        } else {
            damage = 0;
            currentLife -= damage;
        }
            
    }
}
