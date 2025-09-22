package WEEK6.labproblems;

class Color {
    protected String name;

    Color(String name) {
        this.name = name;
        System.out.println("Color Constructor: Name = " + name);
    }
}

class PrimaryColor extends Color {
    protected String intensity;

    PrimaryColor(String name, String intensity) {
        super(name); // calls Color constructor
        this.intensity = intensity;
        System.out.println("PrimaryColor Constructor: Intensity = " + intensity);
    }
}

class RedColor extends PrimaryColor {
    private String shade;

    RedColor(String name, String intensity, String shade) {
        super(name, intensity); // calls PrimaryColor constructor
        this.shade = shade;
        System.out.println("RedColor Constructor: Shade = " + shade);
    }

    void display() {
        System.out.println("\n--- Color Details ---");
        System.out.println("Name: " + name);
        System.out.println("Intensity: " + intensity);
        System.out.println("Shade: " + shade);
    }
}

public class ColorHeirarchy {
    public static void main(String[] args) {
        RedColor red = new RedColor("Red", "High", "Dark Red");
        red.display();
    }
}

