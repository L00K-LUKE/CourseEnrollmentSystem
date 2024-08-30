package main.java;

import java.time.LocalTime;

public class Tutorial extends ClassSession{

    String tutorialId;
    String tutor;

    public Tutorial(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, Course course, String tutorialId) {
        super(day, startTime, endTime, location, course);
        this.tutorialId = tutorialId;
        this.tutor = tutor;

        setTitle(course.getCourseName() +" (Tutorial)");
    }
}
