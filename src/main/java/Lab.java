package main.java;

import java.time.LocalTime;

public class Lab extends ClassSession{

    String labID;

    public Lab(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, String labID) {
        super(day, startTime, endTime, location, tutor);
        this.labID = labID;
    }
}
