package WEEK7.labproblems;

class Content {
    String title;
    Content(String title) { this.title = title; }
}

class Movie extends Content {
    int rating, duration;
    Movie(String t, int r, int d) { super(t); rating=r; duration=d; }
    void showMovieDetails() { System.out.println("Movie: " + title + " ⭐" + rating + " Duration: " + duration + " mins"); }
}

class TVSeries extends Content {
    int seasons;
    TVSeries(String t, int s) { super(t); seasons=s; }
    void showSeriesDetails() { System.out.println("TV Series: " + title + " Seasons: " + seasons); }
}

class Documentary extends Content {
    String tag;
    Documentary(String t, String tag) { super(t); this.tag=tag; }
    void showDocDetails() { System.out.println("Documentary: " + title + " Tag: " + tag); }
}

public class StreamingService {
    public static void main(String[] args) {
        Content c = new Movie("Inception", 5, 148);  // Upcasting
        if(c instanceof Movie) {
            Movie m = (Movie)c;   // Downcasting
            m.showMovieDetails();
        }
    }
}

