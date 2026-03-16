package WEEK7.labproblems;
abstract class Character {
    abstract void attack();
}

class Warrior extends Character {
    void attack() {
        System.out.println("Warrior attacks with sword! Strong defense!");
    }
}

class Mage extends Character {
    void attack() {
        System.out.println("Mage casts a powerful spell using mana!");
    }
}

class Archer extends Character {
    void attack() {
        System.out.println("Archer shoots an arrow with long range!");
    }
}

public class GameBattle {
    public static void main(String[] args) {
        Character[] army = {new Warrior(), new Mage(), new Archer()};
        for(Character c : army) {
            c.attack();
        }
    }
}

