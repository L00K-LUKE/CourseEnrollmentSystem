package main.java;

import java.time.LocalTime;

public class Tutorial extends ClassSession{

    String tutorialId;

    public Tutorial(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, Course course, String title, String tutorialId) {
        super(day, startTime, endTime, location, tutor, course);
        this.tutorialId = tutorialId;

        setTitle(course.getCourseName() +" (Tutorial)");
    }
}
