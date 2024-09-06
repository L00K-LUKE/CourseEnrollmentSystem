package courseenrollmentsystem;

import java.time.LocalTime;

public class Tutorial extends ClassSession{

    String tutorialId;
    String tutor;

    public Tutorial(DaysOfWeek day, LocalTime startTime, LocalTime endTime, String location, String tutor, String tutorialId, Course course) {
        super(day, startTime, endTime, location, course);
        this.tutorialId = tutorialId;
        this.tutor = tutor;

        setTitle("Tutorial");
    }

    @Override
    public String toString() {
        return super.getDetails();
    }
}
