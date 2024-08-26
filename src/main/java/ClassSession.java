package main.java;

import java.time.LocalTime;

public abstract class ClassSession {
    private DaysOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String tutor;

    public ClassSession(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.tutor = tutor;
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

    // Methods
    public boolean confilctsWith(ClassSession session) {

        return true;

    }

}
