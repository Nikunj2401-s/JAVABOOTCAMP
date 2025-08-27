package javabootcamp.WEEK2;
import java.util.Scanner;

public class CaesarCipher {
    
    public static String encrypt(String text, int shift) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            ch = (char) (ch + shift);
            result += ch;
        }
        return result;
    }

    public static String decrypt(String text, int shift) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            ch = (char) (ch - shift);
            result += ch;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted text: " + decrypted);

        sc.close();
    }
}

