package courseenrollmentsystem;

import java.util.HashSet;
import java.util.Set;

public class Timetable {

    Set<Course> courses;
    Set<ClassSession> sessions;

    public Timetable() {
        this.courses = new HashSet<>();
        this.sessions = new HashSet<>();
    }

    // Getters

    public Set<ClassSession> getSessions() {
        return sessions;
    }

    public Set<Course> getCourses() {
        return courses;
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
        if (courses.add(course)) {
        Set<ClassSession> schedule = course.getSchedule();
        schedule.forEach(this::addSession);
        }
    }

    public void removeCourse(Course course) {
        if (courses.remove(course)) {
            course.getSchedule().forEach(this::removeSession);
        }
    }
}
