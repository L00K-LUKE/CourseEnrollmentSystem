package courseenrollmentsystem;


import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void addClassSession_shouldAddSessionToSchedule() {
        Course course = new Course("Algs", "Gus");
        LocalTime start = LocalTime.of(13,30);
        LocalTime end = LocalTime.of(15,15);
        courseenrollmentsystem.ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Red Building", "Gavin", "ALGSa", course);

        course.addClassSession(lab);

        assertEquals(1, course.getSchedule().size());
    }

    @Test
    void removeClassSession_shouldRemoveSessionFromSchedule() {
        Course course = new Course("Algs", "Gus");
        LocalTime start = LocalTime.of(14,20);
        LocalTime end = LocalTime.of(15,15);

        courseenrollmentsystem.ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Blue Building", "Mary", "ALGSb", course);

        course.addClassSession(lab);
        course.removeClassSession(lab);

        assertEquals(0, course.getSchedule().size());
    }
}