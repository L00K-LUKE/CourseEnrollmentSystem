package courseenrollmentsystem;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private static int courseIdIncrementer = 1;

    String courseId;
    String courseName;
    String lecturer;
    Set<ClassSession> schedule;

    public Course(String courseName, String lecturer ) {
        this.courseId = String.valueOf(courseIdIncrementer++);
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.schedule = new HashSet<>();
    }

    // Getters

    public String getCourseName() {
        return courseName;
    }

    public Set<ClassSession> getSchedule() {
        return schedule;
    }

    // Methods
    public void addClassSession(ClassSession session) {
        schedule.add(session);
    }

    public void removeClassSession(ClassSession session) {
        schedule.remove(session);
    }

    @Override
    public String toString() {

        return courseId + " | " + courseName + " | " + lecturer;
    }
}
