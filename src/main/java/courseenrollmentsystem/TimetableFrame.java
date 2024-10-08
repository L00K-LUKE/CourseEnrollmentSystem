package courseenrollmentsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalTime;
import java.util.Set;

public class TimetableFrame {
    Timetable timetable;


    public TimetableFrame(Timetable timetable) {
        this.timetable = timetable;

        JFrame frame = new JFrame("Timetable");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1056, 792);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        placeTable(panel);

        frame.setVisible(true);
    }

    private void placeTable(JPanel panel) {
        // creating table
        String[] columnNames = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 11){
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return String.class;
                }
                return JLabel.class;
            }
        };

        JTable timetableTable = new JTable(tableModel);

        for (int i = 0; i <columnNames.length; i++) {
            timetableTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof JLabel) {
                        return (JLabel) value; // Return JLabel directly
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            });
        }

        populateTable(tableModel, timetable);

        timetableTable.setRowHeight(60);
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        panel.add(scrollPane, BorderLayout.CENTER);

    }


    private void populateTable(DefaultTableModel tableModel, Timetable studTimetable) {
        LocalTime time = LocalTime.of(8, 0);
        for (int i = 0; i < 11; i++) {
            tableModel.setValueAt(time.toString(), i, 0);
            time = time.plusHours(1);
        }

        Set<ClassSession> sessions = studTimetable.getSessions();

        for (ClassSession session : sessions) {
            int colIdx = getColIdx(session);

            int startHourRow = session.getStartTime().getHour() - 8;
            int endHourRow = session.getEndTime().getHour() - 8;

            for (int i = startHourRow; i <= endHourRow; i++) {

                tableModel.setValueAt(new JLabel(session.timetableString()), i, colIdx);

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