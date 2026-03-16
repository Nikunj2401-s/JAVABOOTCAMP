package WEEK7.practiceproblems;
// Parent class
class User {
    void postContent() {
        System.out.println("User posts a text update.");
    }
}

// Child class 1
class Influencer extends User {
    @Override
    void postContent() {
        System.out.println("Influencer posts content with hashtags and tags.");
    }
}

// Child class 2
class BusinessUser extends User {
    @Override
    void postContent() {
        System.out.println("Business User posts promotional content with ads.");
    }
}

public class SocialMedia {
    public static void main(String[] args) {
        User u1 = new User();          // General user
        User u2 = new Influencer();    // Upcasting
        User u3 = new BusinessUser();  // Upcasting

        u1.postContent();  // Calls User’s method
        u2.postContent();  // Calls Influencer’s overridden method
        u3.postContent();  // Calls BusinessUser’s overridden method
    }
}
