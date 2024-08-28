package main.java;

import java.time.LocalTime;

public class Lab extends ClassSession{

    String labID;

    public Lab(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, String labID, Course course, String title) {
        super(day, startTime, endTime, location, tutor, course);
        this.labID = labID;

        setTitle(course.getCourseName() +" (Lab)");
    }
}
