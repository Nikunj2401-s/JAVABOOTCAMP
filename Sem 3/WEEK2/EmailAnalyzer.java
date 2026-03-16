package javabootcamp.WEEK2;

import java.util.Scanner;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex == -1 || dotIndex == -1 || atIndex > dotIndex) {
            System.out.println("Invalid email format");
        } else {
            String username = email.substring(0, atIndex);
            String domain = email.substring(atIndex + 1, dotIndex);
            String extension = email.substring(dotIndex + 1);

            System.out.println("Full Email: " + email);
            System.out.println("Username: " + username);
            System.out.println("Domain: " + domain);
            System.out.println("Extension: " + extension);
        }

        sc.close();
    }
}
