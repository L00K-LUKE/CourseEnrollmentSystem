package courseenrollmentsystem;

import java.time.LocalTime;

public class Tutorial extends ClassSession{
    private static int idIncrementer = 1;

    private String tutorialId;
    private String tutor;

    public Tutorial(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, Course course) {
        super(day, startTime, endTime, location, course);
        this.tutorialId = "T" + idIncrementer++;
        this.tutor = tutor;

        setTitle("Tutorial");
    }

    @Override
    public String toString() {
        return super.getDetails();
    }
}
