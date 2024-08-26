package main.java;

import java.time.LocalTime;

public class Lecture extends ClassSession{
    public Lecture(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor) {
        super(day, startTime, endTime, location, tutor);
    }


}
