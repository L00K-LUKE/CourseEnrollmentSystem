package test.java;

import main.java.ClassSession;
import main.java.Course;
import main.java.DaysOfWeek;
import main.java.Lecture;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
class ClassSessionTest {

    @Test
    void conflictsWith_Session1EndsBeforeSession2StartsShouldReturnFalse() {

        Course course1 = new Course("0001","HCI", "Bob");
        Course course2 = new Course("0002","ADS", "Sarah");

        LocalTime start1 = LocalTime.of(10,30);
        LocalTime end1 = LocalTime.of(11,30);

        LocalTime start2 = LocalTime.of(12,30);
        LocalTime end2 = LocalTime.of(14,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start1, end1, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start2, end2, "Across Campus", course2);

        assertFalse(cs1.conflictsWith(cs2));
    }

    @Test
    void conflictsWith_Session1StartsAfterSession2EndsShouldReturnFalse() {

        Course course1 = new Course("0001","HCI", "Bob");
        Course course2 = new Course("0002","ADS", "Sarah");

        LocalTime start1 = LocalTime.of(12,30);
        LocalTime end1 = LocalTime.of(14,30);

        LocalTime start2 = LocalTime.of(10,30);
        LocalTime end2 = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start1, end1, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start2, end2, "Across Campus", course2);

        assertFalse(cs1.conflictsWith(cs2));
    }

    @Test
    void conflictsWith_SessionsAtExactSameTimesShouldReturnTrue() {

        Course course1 = new Course("0001","HCI", "Bob");
        Course course2 = new Course("0002","ADS", "Sarah");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start, end, "Across Campus", course2);

        assertTrue(cs1.conflictsWith(cs2));
    }

    @Test
    void conflictsWith_SessionsOnDifferentDaysShouldReturnFalse() {
        Course course1 = new Course("0001","HCI", "Bob");
        Course course2 = new Course("0002","ADS", "Sarah");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.TUESDAY, start, end, "Across Campus", course2);

        assertFalse(cs1.conflictsWith(cs2));

    }

    @Test
    void conflictsWith_Session2StartsDuringSession1ShouldReturnTrue() {

        Course course1 = new Course("0001","HCI", "Bob");
        Course course2 = new Course("0002","ADS", "Sarah");

        LocalTime start1 = LocalTime.of(10,0);
        LocalTime end1 = LocalTime.of(14,0);

        LocalTime start2 = LocalTime.of(11,0);
        LocalTime end2 = LocalTime.of(11,30);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start1, end1, "Main Building", course1);
        ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start2, end2, "Across Campus", course2);

        assertTrue(cs1.conflictsWith(cs2));
    }

    @Test
    void getDetails_correctlyFormatsOutputWhenSessionStartsAndFinishesAM() {

        Course course = new Course("0001","WAD", "Michael");

        LocalTime start = LocalTime.of(9,0);
        LocalTime end = LocalTime.of(11,0);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "JMS112", course);

        String correctlyFormatted = "COURSE: WAD | TYPE: WAD (Lecture) | LOCATION: JMS112 |  DAY: MONDAY | TIME: 09:00 am UNTIL 11:00 am";

        assertEquals(correctlyFormatted, cs1.getDetails());
    }

    @Test
    void getDetails_correctlyFormatsOutputWhenSessionStartsAMAndFinishesPM() {

        Course course = new Course("0001","WAD", "Michael");

        LocalTime start = LocalTime.of(9,0);
        LocalTime end = LocalTime.of(13,0);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "JMS112", course);

        String correctlyFormatted = "COURSE: WAD | TYPE: WAD (Lecture) | LOCATION: JMS112 |  DAY: MONDAY | TIME: 09:00 am UNTIL 01:00 pm";

        assertEquals(correctlyFormatted, cs1.getDetails());
    }

    @Test
    void getDetails_correctlyFormatsOutputWhenSessionStartsAndFinishesPM() {

        Course course = new Course("0001","WAD", "Michael");

        LocalTime start = LocalTime.of(14,0);
        LocalTime end = LocalTime.of(17,15);

        ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "JMS112", course);

        String correctlyFormatted = "COURSE: WAD | TYPE: WAD (Lecture) | LOCATION: JMS112 |  DAY: MONDAY | TIME: 02:00 pm UNTIL 05:15 pm";

        assertEquals(correctlyFormatted, cs1.getDetails());
    }

}