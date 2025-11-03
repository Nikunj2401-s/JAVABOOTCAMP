class StudentNode {
    int rollNo;
    StudentNode next;

    StudentNode(int rollNo) {
        this.rollNo = rollNo;
        this.next = null;
    }
}

public class StudentRecordManager {
    StudentNode head;

    // Add new student at the end
    void addStudent(int rollNo) {
        StudentNode newNode = new StudentNode(rollNo);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
    }

    // Display all student roll numbers
    void displayRecords() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        System.out.print("Student Roll Numbers: ");
        StudentNode temp = head;
        while (temp != null) {
            System.out.print(temp.rollNo + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StudentRecordManager srm = new StudentRecordManager();
        srm.addStudent(101);
        srm.addStudent(102);
        srm.addStudent(103);
        srm.displayRecords();
    }
}
