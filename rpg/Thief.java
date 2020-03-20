package rpg;

public class Thief extends Character{
    
    private int stamina;
    
    public Thief(String n, int s, int i) {
        super(n, s, 25, i, 2);
        stamina = 20;
    }
    
    public int getStamina(){
        return stamina;
    }
    
    public int evade(int damage){
        int damageTaken = damage - (dexterity - dice.roll());
        if (damageTaken < 0){
            damageTaken = 0;
        }
        stamina -= 6;
        return damageTaken;
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
