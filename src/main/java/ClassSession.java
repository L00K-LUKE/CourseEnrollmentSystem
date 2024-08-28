package main.java;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public abstract class ClassSession {
    private DaysOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String tutor;
    private Course course;
    private String title;

    public ClassSession(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, Course course) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.tutor = tutor;
        this.course = course;

        this.title = null;
    }

    // Getters
    public DaysOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getTutor() {
        return tutor;
    }

    public Course getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    // Setters

    public void setTitle(String str) {
        this.title = str;
    }


    // Methods
    public boolean conflictsWith(ClassSession session) {
        if (this.day != session.getDay()) { // check if sessions on same day
            return false;
        }

        // get other sessions times
        LocalTime otherStartTime = session.getStartTime();
        LocalTime otherEndTime = session.getEndTime();

        boolean endsBeforeOrAtStart = this.endTime.isBefore(otherStartTime) || this.endTime.equals(otherStartTime);
        boolean startsAfterOrAtEnd = this.startTime.isAfter(otherEndTime) || this.startTime.equals(otherEndTime);

        // if either is true, return false
        // else return true

        return !(endsBeforeOrAtStart || startsAfterOrAtEnd);
    }

    public String getDetails() {
        String courseName = course.getCourseName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String details = String.format("COURSE: %s | TYPE: %s |  DAY: %s | TIME: %s  UNTIL %s",
                courseName, this.title, this.day, this.startTime.format(formatter), this.endTime.format(formatter));
        return details;
    }



}
