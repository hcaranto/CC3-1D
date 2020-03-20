package rpg;

public class Battle {
    
    public static void main(String[] args) {
        boolean special = false;
           
        Mage mageChar = new Mage("Caleb", 11, 9);
        Thief thiefChar = new Thief("Vax", 19, 8);
        Knight knightChar = new Knight("Pike", 13, 7);

        int roundNum = 1, mageDamage, thiefDamage, knightDamage;
        
        System.out.println("FIGHT!");
        
        while (mageChar.getCurrentLife() > 0 && thiefChar.getCurrentLife() > 0 && knightChar.getCurrentLife() > 0){
            System.out.println("\nRound " + roundNum);
            
            System.out.println(mageChar.getName().toUpperCase() + ": " + mageChar.getCurrentLife());
            System.out.println(knightChar.getName().toUpperCase() + ": " + knightChar.getCurrentLife());
            System.out.println(thiefChar.getName().toUpperCase() + ": " + thiefChar.getCurrentLife() + "\n");
            
            int stamina = thiefChar.getStamina();
            int durability = knightChar.getDurability();
            
            mageDamage = mageAction(mageChar, thiefChar, knightChar);
            knightDamage = knightAction(knightChar, mageChar, thiefChar);
            thiefDamage = thiefAction(thiefChar, mageChar, knightChar, (mageDamage+knightDamage));
            
            mageChar.wound(knightDamage+thiefDamage);
            
            special = specialUsedKnight(knightChar, durability);
            
            if (special != true){
                knightChar.wound(mageDamage+thiefDamage);
            }
            
            special = specialUsedThief(thiefChar, stamina);
            
            if (special != true){
                thiefChar.wound(mageDamage+knightDamage);
            } else {
                thiefChar.wound(thiefChar.evade(mageDamage+knightDamage));
            }

            roundNum++;
        }
        
        if (mageChar.getCurrentLife() > 0 && knightChar.getCurrentLife() > 0 && thiefChar.getCurrentLife() <= 0){
            System.out.println("\n" + thiefChar.getName() + " is knocked out of the fight!");
            
            while(mageChar.getCurrentLife() > 0 && knightChar.getCurrentLife() > 0){
                System.out.println("\nRound " + roundNum);

                System.out.println(mageChar.getName().toUpperCase() + ": " + mageChar.getCurrentLife());
                System.out.println(knightChar.getName().toUpperCase() + ": " + knightChar.getCurrentLife() + "\n");
                
                int durability = knightChar.getDurability();

                mageDamage = mageAction(mageChar, thiefChar, knightChar);
                knightDamage = knightAction(knightChar, mageChar, thiefChar);

                mageChar.wound(knightDamage);

                special = specialUsedKnight(knightChar, durability);

                if (special != true){
                    knightChar.wound(mageDamage);
                }

                roundNum++;
            }
            
        } else if (thiefChar.getCurrentLife() > 0 && mageChar.getCurrentLife() > 0 && knightChar.getCurrentLife() <= 0) {
            System.out.println("\n" + knightChar.getName() + " is knocked out of the fight!");
            
            while (mageChar.getCurrentLife() > 0 && thiefChar.getCurrentLife() > 0){
                System.out.println("\nRound " + roundNum);
                System.out.println(mageChar.getName().toUpperCase() + ": " + mageChar.getCurrentLife());
                System.out.println(thiefChar.getName().toUpperCase() + ": " + thiefChar.getCurrentLife() + "\n");


                int stamina = thiefChar.getStamina();
                int durability = knightChar.getDurability();

                mageDamage = mageAction(mageChar, thiefChar, knightChar);
                thiefDamage = thiefAction(thiefChar, mageChar, knightChar, (mageDamage));

                mageChar.wound(thiefDamage);

                special = specialUsedThief(thiefChar, stamina);

                if (special != true){
                    thiefChar.wound(mageDamage);
                } else {
                    thiefChar.wound(thiefChar.evade(mageDamage));
                }

                roundNum++;

            }
            
        } else if (knightChar.getCurrentLife() > 0 && thiefChar.getCurrentLife() > 0 && mageChar.getCurrentLife() <= 0){
            System.out.println("\n" + mageChar.getName() + " is knocked out of the fight!");
            
            while (thiefChar.getCurrentLife() > 0 && knightChar.getCurrentLife() > 0){
                System.out.println("\nRound " + roundNum);
                System.out.println(knightChar.getName().toUpperCase() + ": " + knightChar.getCurrentLife());
                System.out.println(thiefChar.getName().toUpperCase() + ": " + thiefChar.getCurrentLife() + "\n");


                int stamina = thiefChar.getStamina();
                int durability = knightChar.getDurability();

                knightDamage = knightAction(knightChar, mageChar, thiefChar);
                thiefDamage = thiefAction(thiefChar, mageChar, knightChar, (knightDamage));

                special = specialUsedKnight(knightChar, durability);

                if (special != true){
                    knightChar.wound(thiefDamage);
                }

                special = specialUsedThief(thiefChar, stamina);

                if (special != true){
                    thiefChar.wound(knightDamage);
                } else {
                    thiefChar.wound(thiefChar.evade(knightDamage));
                }

                roundNum++;
            }
        }
        
        if (mageChar.getCurrentLife() > 0 && thiefChar.getCurrentLife() <= 0 && knightChar.getCurrentLife() <= 0){
            System.out.println("\n" + mageChar.getName() + " wins!");
        } else if (thiefChar.getCurrentLife() > 0 && mageChar.getCurrentLife() <= 0 && knightChar.getCurrentLife() <= 0) {
            System.out.println("\n" + thiefChar.getName() + " wins!");
        } else if (knightChar.getCurrentLife() > 0 && mageChar.getCurrentLife() <= 0 && thiefChar.getCurrentLife() <= 0){
            System.out.println("\n" + knightChar.getName() + " wins!");
        } else {
            System.out.println("\n" + mageChar.getName() + " wins!");
        }
        
    }
    static int mageAction(Mage attacker, Character defender1, Character defender2){
        Dice dice = new Dice();
        int number = dice.rolld8();
        int damage = 0;
         
        if (attacker.getMana() >= 5){
            if (number > 4){
                damage = roundattack(attacker, defender1, defender2);
            } else if (number > 2){
                roundheal(attacker);
                damage = 0;
            } else {
                damage = attacker.fireball();
                System.out.println(attacker.getName() + " casts fireball! " + damage + " damage!");
            }
        } else {
            if (number > 4){
                damage = roundattack(attacker, defender1, defender2);
            } else {
                roundheal(attacker);
                damage = 0;
            }
        }
        return damage;
    }
    
    static int thiefAction(Thief attacker, Character defender1, Character defender2, int rounddamage){
        Dice dice = new Dice();
        int number = dice.rolld8();
        int damage = 0;
         
        if (attacker.getStamina() >= 6){
            if (number > 4){
                damage = roundattack(attacker, defender1, defender2);
            } else if (number > 2){
                roundheal(attacker);
                damage = 0;
            } else {
                int damageTaken = attacker.evade(rounddamage);
                System.out.println(attacker.getName() + " evades and reduces the damage!");
                damage = 0;
            }
        } else {
            if (number > 4){
                damage = roundattack(attacker, defender1, defender2);
            } else {
                roundheal(attacker);
                damage = 0;
            }
        }
        return damage;
    }
    
   static int knightAction(Knight attacker, Character defender1, Character defender2){
        Dice dice = new Dice();
        int number = dice.rolld8();
        int damage = 0;
         
        if (attacker.getDurability() >= 10){
            if (number > 4){
                damage = roundattack(attacker, defender1, defender2);
            } else if (number > 2){
                roundheal(attacker);
                damage = 0;
            } else {
                attacker.block();
                System.out.println(attacker.getName() + " blocks and negates all damage!");
                damage = 0;
            }
        } else {
            if (number > 4){
                damage = roundattack(attacker, defender1, defender2);
            } else {
                roundheal(attacker);
                damage = 0;
            }
        }
        return damage;
    }
  
    static int roundattack(Character attacker, Character defender1, Character defender2){
        int damage;
        damage = attacker.attack();
        System.out.println(attacker.getName() + " attacks for " + damage + "!");
        return damage;
    }
    
    static void roundheal(Character attacker){
        int previouslife = attacker.getCurrentLife();
        attacker.heal();
        int damageHealed = attacker.getCurrentLife() - previouslife;
        System.out.println(attacker.getName() + " heals for " + damageHealed + "!");
    }
    
    static boolean specialUsedKnight(Knight knightChar, int durability){
        return knightChar.getDurability() != durability;
    }
    
    static boolean specialUsedThief(Thief thiefChar, int stamina){
        return thiefChar.getStamina() != stamina;
    }
}
