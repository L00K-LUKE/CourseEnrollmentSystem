package courseenrollmentsystem;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class ClassSessionTest {

    @Test
    void conflictsWith_Session1EndsBeforeSession2StartsShouldReturnFalse() {

        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start1 = LocalTime.of(10,30);
        LocalTime end1 = LocalTime.of(11,30);

        LocalTime start2 = LocalTime.of(12,30);
        LocalTime end2 = LocalTime.of(14,30);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start1, end1, "Main Building", course1);
        courseenrollmentsystem.ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start2, end2, "Across Campus", course2);

        Assertions.assertFalse(cs1.conflictsWith(cs2));
    }

    @Test
    void conflictsWith_Session1StartsAfterSession2EndsShouldReturnFalse() {

        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start1 = LocalTime.of(12,30);
        LocalTime end1 = LocalTime.of(14,30);

        LocalTime start2 = LocalTime.of(10,30);
        LocalTime end2 = LocalTime.of(11,30);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start1, end1, "Main Building", course1);
        courseenrollmentsystem.ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start2, end2, "Across Campus", course2);

        Assertions.assertFalse(cs1.conflictsWith(cs2));
    }

    @Test
    void conflictsWith_SessionsAtExactSameTimesShouldReturnTrue() {

        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course1);
        courseenrollmentsystem.ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start, end, "Across Campus", course2);

        Assertions.assertTrue(cs1.conflictsWith(cs2));
    }

    @Test
    void conflictsWith_SessionsOnDifferentDaysShouldReturnFalse() {
        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(11,30);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "Main Building", course1);
        courseenrollmentsystem.ClassSession cs2 = new Lecture(DaysOfWeek.TUESDAY, start, end, "Across Campus", course2);

        Assertions.assertFalse(cs1.conflictsWith(cs2));

    }

    @Test
    void conflictsWith_Session2StartsDuringSession1ShouldReturnTrue() {

        Course course1 = new Course("HCI", "Bob");
        Course course2 = new Course("ADS", "Sarah");

        LocalTime start1 = LocalTime.of(10,0);
        LocalTime end1 = LocalTime.of(14,0);

        LocalTime start2 = LocalTime.of(11,0);
        LocalTime end2 = LocalTime.of(11,30);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start1, end1, "Main Building", course1);
        courseenrollmentsystem.ClassSession cs2 = new Lecture(DaysOfWeek.MONDAY, start2, end2, "Across Campus", course2);

        Assertions.assertTrue(cs1.conflictsWith(cs2));
    }

    @Test
    void getDetails_correctlyFormatsOutputWhenSessionStartsAndFinishesAM() {

        Course course = new Course("WAD", "Michael");

        LocalTime start = LocalTime.of(9,0);
        LocalTime end = LocalTime.of(11,0);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "JMS112", course);

        String correctlyFormatted = "COURSE: WAD | TYPE: Lecture | LOCATION: JMS112 | DAY: MONDAY | TIME: 09:00 am UNTIL 11:00 am";

        Assertions.assertEquals(correctlyFormatted, cs1.getDetails());
    }

    @Test
    void getDetails_correctlyFormatsOutputWhenSessionStartsAMAndFinishesPM() {

        Course course = new Course("WAD", "Michael");

        LocalTime start = LocalTime.of(9,0);
        LocalTime end = LocalTime.of(13,0);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "JMS112", course);

        String correctlyFormatted = "COURSE: WAD | TYPE: Lecture | LOCATION: JMS112 | DAY: MONDAY | TIME: 09:00 am UNTIL 01:00 pm";

        Assertions.assertEquals(correctlyFormatted, cs1.getDetails());
    }

    @Test
    void getDetails_correctlyFormatsOutputWhenSessionStartsAndFinishesPM() {

        Course course = new Course("WAD", "Michael");

        LocalTime start = LocalTime.of(14,0);
        LocalTime end = LocalTime.of(17,15);

        courseenrollmentsystem.ClassSession cs1 = new Lecture(DaysOfWeek.MONDAY, start, end, "JMS112", course);

        String correctlyFormatted = "COURSE: WAD | TYPE: Lecture | LOCATION: JMS112 | DAY: MONDAY | TIME: 02:00 pm UNTIL 05:15 pm";

        Assertions.assertEquals(correctlyFormatted, cs1.getDetails());
    }

}