package main.java;

import java.util.HashSet;
import java.util.Set;

public class Timetable {

    Set<ClassSession> sessions;

    public Timetable() {
        this.sessions = new HashSet<ClassSession>();
    }

    // Getters

    public Set<ClassSession> getSessions() {
        return sessions;
    }


    // Methods

    public void addSession(ClassSession session) {
        sessions.add(session);
    }

    public void removeSession(ClassSession session) {
            sessions.remove(session);
    }

    public boolean checkClash(ClassSession newSession) {

        for (ClassSession existingClass : sessions) {
            if (existingClass.conflictsWith(newSession)) {
                return true;
            }
        }
        return false;
    }

    public void addCourse(Course course) {
        Set<ClassSession> schedule = course.getSchedule();
        schedule.forEach(this::addSession);
    }
}
