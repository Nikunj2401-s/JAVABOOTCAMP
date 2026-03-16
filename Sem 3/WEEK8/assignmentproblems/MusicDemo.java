package WEEK8.assignmentproblems;

// Interface Playable
interface Playable {
    void play();
    void pause();
}

// Class MusicPlayer implementing Playable
class MusicPlayer implements Playable {
    public void play() {
        System.out.println("Playing music track...");
    }

    public void pause() {
        System.out.println("Music paused.");
    }
}

// Class VideoPlayer implementing Playable
class VideoPlayer implements Playable {
    public void play() {
        System.out.println("Playing video file...");
    }

    public void pause() {
        System.out.println("Video paused.");
    }
}

// Main class to demonstrate polymorphism
public class MusicDemo {
    public static void main(String[] args) {
        // Using Playable reference for MusicPlayer
        Playable ref = new MusicPlayer();
        ref.play();
        ref.pause();

        System.out.println();

        // Using Playable reference for VideoPlayer
        ref = new VideoPlayer();
        ref.play();
        ref.pause();
    }
}
