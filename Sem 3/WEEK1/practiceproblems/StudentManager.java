package javabootcamp.WEEK1.practiceproblems;
import java.util.Arrays;
import java.util.Scanner;
public class StudentManager {
    public static void displayStudents(String[] students, int count) {
        if (count == 0) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("Student List:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }

    // Method to search for a student
    public static void searchStudent(String[] students, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].equalsIgnoreCase(name)) {
                System.out.println(name + " found at position " + (i + 1));
                return;
            }
        }
        System.out.println(name + " not found.");
    }

    // Method to remove a student by name
    public static int removeStudent(String[] students, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].equalsIgnoreCase(name)) {
                // Shift elements left
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null;
                System.out.println(name + " removed from the list.");
                return count - 1;
            }
        }
        System.out.println(name + " not found. Cannot remove.");
        return count;
    }

    // Method to sort students alphabetically
    public static void sortStudents(String[] students, int count) {
        String[] temp = Arrays.copyOf(students, count);
        Arrays.sort(temp, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Sorted Student List:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + temp[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] students = new String[50]; // fixed-size array
        int count = 0; // number of students added

        while (true) {
            System.out.println("\n--- Student Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Sort Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (count < students.length) {
                        System.out.print("Enter student name: ");
                        students[count] = sc.nextLine();
                        count++;
                        System.out.println("Student added.");
                    } else {
                        System.out.println("List is full! Cannot add more students.");
                    }
                    break;

                case 2:
                    displayStudents(students, count);
                    break;

                case 3:
                    System.out.print("Enter student name to search: ");
                    String searchName = sc.nextLine();
                    searchStudent(students, count, searchName);
                    break;

                case 4:
                    System.out.print("Enter student name to remove: ");
                    String removeName = sc.nextLine();
                    count = removeStudent(students, count, removeName);
                    break;

                case 5:
                    sortStudents(students, count);
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
}
