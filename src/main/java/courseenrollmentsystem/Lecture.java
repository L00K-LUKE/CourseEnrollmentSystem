package courseenrollmentsystem;

import java.time.LocalTime;

public class Lecture extends ClassSession{
    public Lecture(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, Course course) {
        super(day, startTime, endTime, location, course);

        setTitle(course.getCourseName() +" (Lecture)");
    }


}
