package main.java;

import java.util.Set;

public class Course {

    String courseId;
    String courseName;
    String lecturer;
    Set<ClassSession> schedule;

    public Course(String courseId, String courseName, String lecturer, Set<ClassSession> schedule) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.schedule = schedule;
    }

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public Set<ClassSession> getSchedule() {
        return schedule;
    }

    // Methods
    public void addClassSession(ClassSession session) {

    }

}
