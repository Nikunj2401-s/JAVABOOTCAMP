package WEEK7.labproblems;

class Post {
    String author, content, time;
    Post(String author, String content, String time) {
        this.author = author; this.content = content; this.time = time;
    }
    void display() {
        System.out.println(author + " posted: " + content + " at " + time);
    }
}

class InstagramPost extends Post {
    int likes; String hashtags;
    InstagramPost(String a, String c, String t, int likes, String hashtags) {
        super(a,c,t); this.likes = likes; this.hashtags = hashtags;
    }
    @Override
    void display() {
        System.out.println("📸 Instagram Post by " + author + ": " + content + " " + hashtags + " ❤️ Likes: " + likes);
    }
}

class TwitterPost extends Post {
    int retweets;
    TwitterPost(String a, String c, String t, int retweets) {
        super(a,c,t); this.retweets = retweets;
    }
    @Override
    void display() {
        System.out.println("🐦 Twitter Post by " + author + ": " + content + " (" + content.length() + " chars) 🔁 Retweets: " + retweets);
    }
}

class LinkedInPost extends Post {
    int connections;
    LinkedInPost(String a, String c, String t, int connections) {
        super(a,c,t); this.connections = connections;
    }
    @Override
    void display() {
        System.out.println("💼 LinkedIn Post by " + author + ": " + content + "\nConnections Engaged: " + connections);
    }
}

public class SocialMediaFeed {
    public static void main(String[] args) {
        Post p1 = new InstagramPost("Alice", "Beautiful sunset!", "10:00 AM", 120, "#nature #sunset");
        Post p2 = new TwitterPost("Bob", "Java rocks!", "11:30 AM", 40);
        Post p3 = new LinkedInPost("Charlie", "Excited to join new company!", "1:00 PM", 200);
        p1.display();
        p2.display();
        p3.display();
    }
}

