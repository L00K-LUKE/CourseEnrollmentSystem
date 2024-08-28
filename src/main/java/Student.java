package main.java;

import java.util.Set;

public class Student {

    private String name;
    private String studentID;
    private String email;
    private Timetable timetable;

    public Student(String name, String studentID, String email, Timetable timetable) {
        this.name = name;
        this.studentID = studentID;
        this.email = email;
        this.timetable = timetable;
    }

    // Methods
    public boolean enroll(Course course) {
        if (checkTimetableClash(course)) {
            return false;
        }
        timetable.addCourse(course);
        return true;
    }

    public boolean checkTimetableClash(Course course) {
        Set<ClassSession> newCourseClasses = course.getSchedule();
        for (ClassSession newSession : newCourseClasses) {
            if (timetable.checkClash(newSession)) {
                informUserOfClash(newSession);
                return true;
            }
        }
        return false;
    }

    private void informUserOfClash(ClassSession session) {
        System.out.println("Clash Detected: " + session.getDetails());
    }

}
