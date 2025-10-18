package WEEK7.assignmentproblems;

class Artwork {
    String title;
    Artwork(String t) { title = t; }
}

class Painting extends Artwork {
    String technique;
    Painting(String t, String tech) { super(t); technique = tech; }
    void showDetails() { System.out.println("Painting: " + title + " | Technique: " + technique); }
}

class Sculpture extends Artwork {
    String material;
    Sculpture(String t, String m) { super(t); material = m; }
    void showDetails() { System.out.println("Sculpture: " + title + " | Material: " + material); }
}

class DigitalArt extends Artwork {
    String format;
    DigitalArt(String t, String f) { super(t); format = f; }
    void showDetails() { System.out.println("Digital Art: " + title + " | Format: " + format); }
}

class Photography extends Artwork {
    String camera;
    Photography(String t, String c) { super(t); camera = c; }
    void showDetails() { System.out.println("Photography: " + title + " | Camera: " + camera); }
}

public class ArtGallery {
    public static void main(String[] args) {
        Artwork a = new Painting("Starry Night", "Oil on Canvas");  // upcast
        if(a instanceof Painting) {
            Painting p = (Painting)a;  // downcast
            p.showDetails();
        }
    }
}

