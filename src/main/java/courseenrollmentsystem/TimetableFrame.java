package courseenrollmentsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.Set;

public class TimetableFrame {
    Timetable timetable;


    public TimetableFrame(Timetable timetable) {
        this.timetable = timetable;

        JFrame frame = new JFrame("Timetable");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        placeTable(panel);


        frame.setVisible(true);
    }

    private void placeTable(JPanel panel) {
        // creating table
        String[] columnNames = {"Time","Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 11);
        JTable timetableTable = new JTable(tableModel);

        populateTable(tableModel, timetable);

        JScrollPane scrollPane = new JScrollPane(timetableTable);
        panel.add(scrollPane, BorderLayout.CENTER);

    }


    private void populateTable(DefaultTableModel tableModel, Timetable studTimetable) {
        LocalTime time = LocalTime.of(8,0);
        for (int i = 0; i < 11; i++) {
            tableModel.setValueAt(time.toString(),i,0);
            time = time.plusHours(1);
        }

        Set<ClassSession> sessions = studTimetable.getSessions();

        for (ClassSession session : sessions) {
            int colIdx = getColIdx(session);

            int startHourRow = session.getStartTime().getHour() -8;
            int endHourRow = session.getEndTime().getHour() -8;

            for (int i = startHourRow; i <= endHourRow; i++) {

                tableModel.setValueAt(session.getDetails(), i, colIdx); // TODO: make Timetable cells bigger

            }
        }

    }

    private static int getColIdx(ClassSession session) {
        DaysOfWeek day = session.getDay();

        int colIdx;
        switch (day) {
            case MONDAY -> colIdx = 1;
            case TUESDAY -> colIdx = 2;
            case WEDNESDAY -> colIdx = 3;
            case THURSDAY -> colIdx = 4;
            case FRIDAY -> colIdx = 5;
            default -> throw new RuntimeException("Session date is not within Mon-Fri range of enums");
        }
        return colIdx;
    }
}