package courseenrollmentsystem;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void checkTimetableClash_noClashSoShouldReturnFalse() {
        Student student = new Student("Mary", new Timetable());

        Course course = new Course( "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(12,20);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", course);
        ClassSession tut = new Tutorial(DaysOfWeek.FRIDAY, start, end, "Garden", "Brian", course);

        course.addClassSession(lab);
        course.addClassSession(tut);

        assertFalse(student.checkTimetableClash(course));
    }

    @Test
    void checkTimetableClash_clashShouldReturnTrue() {
        Student student = new Student("Kyle", new Timetable());

        Course course = new Course( "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(12,20);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", course);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", course);

        course.addClassSession(lab);

        student.getTimetable().addSession(tut);

        assertTrue(student.checkTimetableClash(course));

    }

    @Test
    void enroll_noClashSoShouldAddClassesToTimetableAndReturnTrue() {
        Student student = new Student("Erin", new Timetable());

        Course course = new Course( "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", course);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", course);

        course.addClassSession(lab);
        course.addClassSession(tut);

        assertTrue(student.enroll(course));
        assertEquals(2, student.getTimetable().getSessions().size());
    }

    @Test
    void enroll_ClashSoShouldReturnFalse() {
        Student student = new Student("Erin", new Timetable());

        Course course1 = new Course( "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", course1);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", course1);

        course1.addClassSession(lab);
        course1.addClassSession(tut);

        student.enroll(course1);

        Course course2 = new Course( "3D Modeling", "Mr Blendr");
        ClassSession lab2 = new Lab(DaysOfWeek.THURSDAY, start, end, "Computer Lab", "Anna", course2);
        course2.addClassSession(lab2);

        assertFalse(student.enroll(course2));
    }

    @Test
    void enroll_ClashSoShouldInformUserByPrintingDetails() {
        Student student = new Student("Erin", new Timetable());

        Course course1 = new Course( "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", course1);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", course1);

        course1.addClassSession(lab);
        course1.addClassSession(tut);

        student.enroll(course1);

        Course course2 = new Course( "3D Modeling", "Mr Blendr");
        ClassSession lab2 = new Lab(DaysOfWeek.THURSDAY, start, end, "Computer Lab", "Anna", course2);
        course2.addClassSession(lab2);

        String expectedOutput = "Clash Detected: COURSE: 3D Modeling | TYPE: Lab | LOCATION: Computer Lab | DAY: THURSDAY | TIME: 11:00 am UNTIL 12:00 pm";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            student.enroll(course2);
            String output = outputStream.toString();
            output = output.trim();

            assertEquals(expectedOutput, output);
        } finally {
            System.setOut(originalOutput);
        }
    }


}