package main.java;

import java.time.LocalTime;
import java.util.HashSet;
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

    public Course(String courseId, String courseName, String lecturer ) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.schedule = new HashSet<ClassSession>();
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

}
