class SongNode {
    String songName;
    SongNode next;

    SongNode(String songName) {
        this.songName = songName;
        this.next = null;
    }
}

public class PlaylistManager {
    SongNode head;

    // Add song at end
    void addSong(String name) {
        SongNode newSong = new SongNode(name);
        if (head == null) {
            head = newSong;
            System.out.println(name + " added to playlist.");
            return;
        }
        SongNode temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newSong;
        System.out.println(name + " added to playlist.");
    }

    // Remove first song
    void removeFirstSong() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        System.out.println(head.songName + " removed from playlist.");
        head = head.next;
    }

    // Display playlist
    void showPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        System.out.print("Playlist: ");
        SongNode temp = head;
        while (temp != null) {
            System.out.print(temp.songName + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    public static void main(String[] args) {
        PlaylistManager pm = new PlaylistManager();
        pm.addSong("Shape of You");
        pm.addSong("Believer");
        pm.addSong("Closer");
        pm.showPlaylist();
        pm.removeFirstSong();
        pm.showPlaylist();
    }
}
