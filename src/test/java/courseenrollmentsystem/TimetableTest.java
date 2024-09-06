package courseenrollmentsystem;


import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimetableTest {


    @Test
    void checkClash_WithClashesShouldReturnTrue() {
        Timetable timetable = new Timetable();

        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.TUESDAY, start, end, "Across Campus", course2);
        ClassSession cs3 = new Lecture(DaysOfWeek.MONDAY, start, end, "Across Campus", course2);

        timetable.addSession(cs1);
        timetable.addSession(cs2);

        assertTrue(timetable.checkClash(cs3));
    }

    @Test
    void checkClash_WithNoClashesShouldReturnFalse() {
        Timetable timetable = new Timetable();

        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.TUESDAY, start, end, "Across Campus", course2);
        ClassSession cs3 = new Lecture(DaysOfWeek.WEDNESDAY, start, end, "Across Campus", course2);

        timetable.addSession(cs1); // also tests add session
        timetable.addSession(cs2);

        assertFalse(timetable.checkClash(cs3));

    }

    @Test
    void addCourse_shouldAddAllClassesBelongingToCourseToTimetableSet() {
        Timetable timetable = new Timetable();

        Course course = new Course("HCI", "Louise");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course);
        ClassSession cs2 = new Lecture(DaysOfWeek.TUESDAY, start, end, "Across Campus", course);
        ClassSession cs3 = new Lecture(DaysOfWeek.WEDNESDAY, start, end, "Across Campus", course);

        course.addClassSession(cs1);
        course.addClassSession(cs2);
        course.addClassSession(cs3);

        timetable.addCourse(course);

        assertEquals(3,timetable.getSessions().size());

    }

    @Test
    void addCourse_addingCourseWithNoSessionsShouldDoNothing() {
        Timetable timetable = new Timetable();

        Course course = new Course("JUDO101", "Sam");

        timetable.addCourse(course);

        assertEquals(0, timetable.getSessions().size());
    }

    @Test
    void removeSession_shouldRemoveSessionFromTimetable() {
        Timetable timetable = new Timetable();

        Course course = new Course("JUDO101", "Sam");
        LocalTime start = LocalTime.of(13,0);
        LocalTime end = LocalTime.of(14,0);
        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course);

        timetable.addSession(cs1);
        assertEquals(1,timetable.getSessions().size());

        timetable.removeSession(cs1);
        assertEquals(0,timetable.getSessions().size());
    }
}