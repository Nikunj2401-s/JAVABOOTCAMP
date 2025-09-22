package WEEK6.assignmentproblems;

import java.util.Objects;

class Game {
    String name;

    Game(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Game)) return false;
        Game g = (Game) obj;
        return Objects.equals(this.name, g.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class CardGame extends Game {
    int players;

    CardGame(String name, int players) {
        super(name);
        this.players = players;
    }

    @Override
    public String toString() {
        return super.toString() + ", CardGame with " + players + " players";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof CardGame)) return false;
        CardGame cg = (CardGame) obj;
        return this.players == cg.players;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), players);
    }
}

public class GameDemo {
    public static void main(String[] args) {
        CardGame c1 = new CardGame("Poker", 4);
        CardGame c2 = new CardGame("Poker", 4);
        CardGame c3 = new CardGame("Rummy", 2);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println("c1 equals c2? " + c1.equals(c2));
        System.out.println("c1 equals c3? " + c1.equals(c3));
    }
}

