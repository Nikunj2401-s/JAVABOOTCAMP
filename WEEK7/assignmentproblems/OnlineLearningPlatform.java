package WEEK7.assignmentproblems;

class Course {
    String title, instructor, enrollmentDate;
    Course(String t, String i, String e) {
        title = t; instructor = i; enrollmentDate = e;
    }
    void displayProgress() {
        System.out.println("Course: " + title + " by " + instructor);
    }
}

class VideoCourse extends Course {
    int percent; int watchTime;
    VideoCourse(String t, String i, String e, int p, int wt) {
        super(t,i,e); percent=p; watchTime=wt;
    }
    @Override
    void displayProgress() {
        System.out.println("🎬 Video Course → " + title + " | Completion: " + percent + "% | Watch Time: " + watchTime + " hrs");
    }
}

class InteractiveCourse extends Course {
    int quizScore; int projects;
    InteractiveCourse(String t, String i, String e, int q, int p) {
        super(t,i,e); quizScore=q; projects=p;
    }
    @Override
    void displayProgress() {
        System.out.println("🧩 Interactive Course → " + title + " | Quiz Score: " + quizScore + " | Projects: " + projects);
    }
}

class ReadingCourse extends Course {
    int pages; int notes;
    ReadingCourse(String t, String i, String e, int p, int n) {
        super(t,i,e); pages=p; notes=n;
    }
    @Override
    void displayProgress() {
        System.out.println("📖 Reading Course → " + title + " | Pages Read: " + pages + " | Notes Taken: " + notes);
    }
}

class CertificationCourse extends Course {
    int attempts; boolean certified;
    CertificationCourse(String t, String i, String e, int a, boolean c) {
        super(t,i,e); attempts=a; certified=c;
    }
    @Override
    void displayProgress() {
        System.out.println("🏆 Certification Course → " + title + " | Attempts: " + attempts + " | Certified: " + certified);
    }
}

public class OnlineLearningPlatform {
    public static void main(String[] args) {
        Course c1 = new VideoCourse("Java Basics", "Alice", "Jan 2025", 60, 10);
        Course c2 = new InteractiveCourse("Web Dev", "Bob", "Feb 2025", 80, 3);
        Course c3 = new ReadingCourse("History", "Clara", "Mar 2025", 120, 5);
        Course c4 = new CertificationCourse("AWS Cloud", "David", "Apr 2025", 2, true);

        c1.displayProgress();
        c2.displayProgress();
        c3.displayProgress();
        c4.displayProgress();
    }
}
