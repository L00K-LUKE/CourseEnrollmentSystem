package main.java;

import java.time.LocalTime;

public class Tutorial extends ClassSession{

    String tutorialId;

    public Tutorial(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, String tutorialId) {
        super(day, startTime, endTime, location, tutor);
        this.tutorialId = tutorialId;
    }
}
