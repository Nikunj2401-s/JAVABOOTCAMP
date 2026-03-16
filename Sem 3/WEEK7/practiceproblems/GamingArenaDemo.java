package WEEK7.practiceproblems;

class GamingArena {
    // Attack with only power
    void attack(int power) {
        System.out.println("Attack with power: " + power);
    }

    // Attack with power and weapon
    void attack(int power, String weapon) {
        System.out.println("Attack with power: " + power + " using weapon: " + weapon);
    }

    // Attack with power, weapon, and distance
    void attack(int power, String weapon, double distance) {
        System.out.println("Attack with power: " + power + 
                           ", weapon: " + weapon + 
                           ", from distance: " + distance + " meters");
    }
}

public class GamingArenaDemo {
    public static void main(String[] args) {
        GamingArena player = new GamingArena();

        player.attack(50);                          // Method 1
        player.attack(70, "Sword");                 // Method 2
        player.attack(90, "Bow", 30.5);             // Method 3
    }
}

