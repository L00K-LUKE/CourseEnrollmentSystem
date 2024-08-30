package test.java;

import main.java.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void checkTimetableClash_noClashSoShouldReturnFalse() {
        Student student = new Student("Mary", "53629M", "mary@studentemail.ac.uk", new Timetable());

        Course course = new Course("5", "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(12,20);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", "SNGL1", course);
        ClassSession tut = new Tutorial(DaysOfWeek.FRIDAY, start, end, "Garden", "Brian", "SNGT7", course);

        course.addClassSession(lab);
        course.addClassSession(tut);

        assertFalse(student.checkTimetableClash(course));
    }

    @Test
    void checkTimetableClash_clashShouldReturnTrue() {
        Student student = new Student("Kyle", "53612K", "kyle@studentemail.ac.uk", new Timetable());

        Course course = new Course("5", "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(10,30);
        LocalTime end = LocalTime.of(12,20);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", "SNGL1", course);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", "SNGT7", course);

        course.addClassSession(lab);

        student.getTimetable().addSession(tut);

        assertTrue(student.checkTimetableClash(course));

    }

    @Test
    void enroll_noClashSoShouldAddClassesToTimetableAndReturnTrue() {
        Student student = new Student("Erin", "53611E", "Erin@studentemail.ac.uk", new Timetable());

        Course course = new Course("5", "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", "SNGL1", course);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", "SNGT7", course);

        course.addClassSession(lab);
        course.addClassSession(tut);

        assertTrue(student.enroll(course));
        assertEquals(2, student.getTimetable().getSessions().size());
    }

    @Test
    void enroll_ClashSoShouldReturnFalse() {
        Student student = new Student("Erin", "53611E", "Erin@studentemail.ac.uk", new Timetable());

        Course course1 = new Course("5", "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", "SNGL1", course1);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", "SNGT7", course1);

        course1.addClassSession(lab);
        course1.addClassSession(tut);

        student.enroll(course1);

        Course course2 = new Course("7", "3D Modeling", "Mr Blendr");
        ClassSession lab2 = new Lab(DaysOfWeek.THURSDAY, start, end, "Computer Lab", "Anna", "SNGL1", course2);
        course2.addClassSession(lab2);

        assertFalse(student.enroll(course2));
    }

    @Test
    void enroll_ClashSoShouldInformUserByPrintingDetails() {
        Student student = new Student("Erin", "53611E", "Erin@studentemail.ac.uk", new Timetable());

        Course course1 = new Course("5", "Singing", "Mr Soprano");
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);
        ClassSession lab = new Lab(DaysOfWeek.THURSDAY, start, end, "Garden", "Anna", "SNGL1", course1);
        ClassSession tut = new Tutorial(DaysOfWeek.THURSDAY, start, end, "Garden", "Brian", "SNGT7", course1);

        course1.addClassSession(lab);
        course1.addClassSession(tut);

        student.enroll(course1);

        Course course2 = new Course("7", "3D Modeling", "Mr Blendr");
        ClassSession lab2 = new Lab(DaysOfWeek.THURSDAY, start, end, "Computer Lab", "Anna", "SNGL1", course2);
        course2.addClassSession(lab2);

        String expectedOutput = "Clash Detected: COURSE: 3D Modeling | TYPE: 3D Modeling (Lab) | LOCATION: Computer Lab | DAY: THURSDAY | TIME: 11:00 am UNTIL 12:00 pm";

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