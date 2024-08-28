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

    // Getters
    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getEmail() {
        return email;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    // Methods
    public void enroll(Course course) {
        Set<ClassSession> newCourseClasses = course.getSchedule();

        for (ClassSession newSession : newCourseClasses) {
            if (timetable.checkClash(newSession)) {
                System.out.println("Clash Detected: " + newSession.getDetails());
                return;
            }
        }
        timetable.addCourse(course);
    }

    public boolean checkTimetableClash(Course course) {
        return true;
    }

}
