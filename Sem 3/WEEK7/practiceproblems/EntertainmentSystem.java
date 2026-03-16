package WEEK7.practiceproblems;

// Parent class
class Media {
    void play() {
        System.out.println("Playing some media...");
    }
}

// Child class 1
class Movie extends Media {
    @Override
    void play() {
        System.out.println("Playing a movie with visuals.");
    }
    void showSubtitles() {
        System.out.println("Showing subtitles for the movie.");
    }
}

// Child class 2
class Song extends Media {
    @Override
    void play() {
        System.out.println("Playing a song with audio.");
    }
    void displayLyrics() {
        System.out.println("Displaying song lyrics.");
    }
}

public class EntertainmentSystem {
    public static void main(String[] args) {
        Media m1 = new Movie();   // Upcasting
        m1.play();                // Calls Movie’s version

        // Downcasting to Movie
        Movie mv = (Movie) m1;    
        mv.showSubtitles();       // Now we can access Movie-specific method

        Media m2 = new Song();    // Upcasting
        m2.play();                // Calls Song’s version

        // Downcasting to Song
        Song sg = (Song) m2;
        sg.displayLyrics();       // Now we can access Song-specific method

        // ❌ Unsafe downcasting example (causes ClassCastException at runtime)
        // Movie wrong = (Movie) m2; // m2 actually refers to a Song, not Movie
        // wrong.showSubtitles();
    }
}
