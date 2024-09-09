package courseenrollmentsystem;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private static int courseIdIncrementer = 1;

    private String courseId;
    private String courseName;
    private String lecturer;
    private Set<ClassSession> schedule;
    private Set<Student> students;

    public Course(String courseName, String lecturer ) {
        this.courseId = String.valueOf(courseIdIncrementer++);
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.schedule = new HashSet<>();
        this.students = new HashSet<>();
    }

    // Getters

    public String getCourseName() {
        return courseName;
    }

    public Set<ClassSession> getSchedule() {
        return schedule;
    }

    public Set<Student> getStudents() {
        return students;
    }

    // Methods
    public void addClassSession(ClassSession session) {
        schedule.add(session);
    }

    public void removeClassSession(ClassSession session) {
        for (Student student : students) {
            student.getTimetable().removeSession(session);
        }
        schedule.remove(session);
    }

    @Override
    public String toString() {

        return courseId + " | " + courseName + " | " + lecturer;
    }
}
