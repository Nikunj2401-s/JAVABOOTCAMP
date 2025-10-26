class Teacher {
    String name, subject;
    Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }
}

class Student {
    String name;
    int rollNo;
    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
}

public class ObjectDiagramDemo {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("Karthik", "Maths");
        Student s1 = new Student("Ravi", 101);
        Student s2 = new Student("Priya", 102);
        System.out.println(t1.name + " teaches " + s1.name + " and " + s2.name);
    }
}
