package courseenrollmentsystem;

import java.time.LocalTime;

public class Lab extends ClassSession{

    String labID;
    String tutor;

    public Lab(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, String labID, Course course) {
        super(day, startTime, endTime, location, course);
        this.labID = labID;
        this.tutor = tutor;

        setTitle("Lab");
    }

    @Override
    public String toString() {
        return super.getDetails();
    }
}
