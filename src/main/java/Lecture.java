package main.java;

import java.time.LocalTime;

public class Lecture extends ClassSession{
    public Lecture(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, Course course) {
        super(day, startTime, endTime, location, tutor, course);

        setTitle(course.getCourseName() +" (Lecture)");
    }


}
