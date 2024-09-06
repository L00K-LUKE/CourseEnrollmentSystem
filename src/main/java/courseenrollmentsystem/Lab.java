package courseenrollmentsystem;

import java.time.LocalTime;

public class Lab extends ClassSession{
    private static int idIncrementer = 1;

    private String labID;
    private String tutor;

    public Lab(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, Course course) {
        super(day, startTime, endTime, location, course);
        this.labID = "L" + idIncrementer++;
        this.tutor = tutor;

        setTitle("Lab");
    }

    @Override
    public String toString() {
        return super.getDetails();
    }
}
