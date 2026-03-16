package javabootcamp.WEEK1.labprograms;

import java.util.*;

public class LengthWithoutMethod {

    public static int findLength(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine(); // allows full sentence input

        int customLength = findLength(input);
        int builtInLength = input.length();

        System.out.println("Length (Custom Method): " + customLength);
        System.out.println("Length (Built-in Method): " + builtInLength);

        sc.close();
    }
}

