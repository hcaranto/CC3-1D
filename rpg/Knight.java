package rpg;

public class Knight extends Character{
    
    private int durability;
    
    public Knight(String n, int d, int i) {
        super(n, 25, d, i, 5);
        durability = 20;
    }
    
    public int getDurability(){
        return durability;
    }
    
    public void block(){
        int damageTaken = 0;
        durability -= 10;
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