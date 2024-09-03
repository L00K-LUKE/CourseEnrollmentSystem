package main.java;

import java.util.Set;

public class Student {

    static int studentIdIncrementer = 1;

    private String name;
    private String studentID;
    private String email;
    private Timetable timetable;

    public Student(String name, Timetable timetable) {
        this.name = name;
        this.timetable = timetable;

        this.studentID = String.valueOf(Student.studentIdIncrementer++);
        this.email = this.studentID + "@student.scl.edu.uk";
    }

    // Getters

    public Timetable getTimetable() {
        return timetable;
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

    @Override
    public String toString() {
        return "Name: " + name + "   Student_Id: " + studentID;
    }
}
