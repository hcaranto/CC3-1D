package rpg;

import java.util.Scanner;

public class TestCharacter {

    public static void main(String[] args) {
        
        Character player = new Character("Jack", 3, 5, 3);
        Character computer = new Character("Bruno", 4, 4, 3);
        
        System.out.println("FIGHT!");
        
        int roundNum = 1;
        
        while (player.getCurrentLife() > 0 && computer.getCurrentLife() > 0){
            System.out.println("\nRound " + roundNum);
            
            System.out.println(player.getName().toUpperCase() + ": " + player.getCurrentLife());
            System.out.println(computer.getName().toUpperCase() + ": " + computer.getCurrentLife());
            
            playeraction(player,computer);
            computeraction(computer,player);
            
            roundNum++;
        }
        
        if (player.getCurrentLife() <= 0 && computer.getCurrentLife() > 0){
            System.out.println("\n" + computer.getName() + " wins!");
        } else if (computer.getCurrentLife() <= 0 && player.getCurrentLife() > 0) {
            System.out.println("\n" + player.getName() + " wins!");
        } else {
            System.out.println("\n" + player.getName() + "wins!");
        }
        
    }
    public static void roundattack(Character attacker, Character defender){
        int roundDamage;
        roundDamage = attacker.attack();
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + roundDamage);
        defender.wound(roundDamage);
    }
    public static void roundheal(Character attacker){
        int previouslife = attacker.getCurrentLife();
        attacker.heal();
        int damageHealed = attacker.getCurrentLife() - previouslife;
        System.out.println(attacker.getName() + " heals for " + damageHealed);
    }
    public static void computeraction(Character attacker, Character defender){
        Dice dice = new Dice();
        int number = dice.roll();
        
        if (number%2 == 0){
            roundattack(attacker, defender);
        } else {
            roundheal(attacker);
        }
    }
    public static void playeraction(Character attacker, Character defender){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nATTACK or HEAL? ");
            String playeraction = sc.next();
            
        if (playeraction.equalsIgnoreCase("attack")){
            roundattack(attacker, defender);
        } else if (playeraction.equalsIgnoreCase("heal")){
            roundheal(attacker);
        } else {
            System.out.print("Invalid input. Try again.");
            playeraction(attacker,defender);
        }  
    }
    
}
