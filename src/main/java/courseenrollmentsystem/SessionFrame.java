package courseenrollmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalTime;

public class SessionFrame {
    Course course;
    DefaultListModel<ClassSession> sessionsListModel;
    JList<ClassSession> sessionsList;

    public SessionFrame(Course course) {
        this.course = course;

        JFrame frame = new JFrame("Classes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        initialiseComponents(panel);

        frame.setVisible(true);
    }

    private void initialiseComponents(JPanel panel) {
        // Components & Layout
        sessionsListModel = new DefaultListModel<>();
        sessionsList = new JList<>(sessionsListModel);
        course.getSchedule().forEach(sessionsListModel::addElement);

        panel.add(new JScrollPane(sessionsList), BorderLayout.CENTER);


        JPanel botPanel = new JPanel(new FlowLayout());
        JButton addLectureButton = new JButton("Add Lecture");
        JButton addTutorialButton = new JButton("Add Tutorial");
        JButton addLabButton = new JButton("Add Lab");
        JButton removeButton = new JButton("Remove");

        botPanel.add(addLectureButton);
        botPanel.add(addLabButton);
        botPanel.add(addTutorialButton);
        botPanel.add(removeButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        //Action Listeners
        addLectureButton.addActionListener(this::addLecture);
        addLabButton.addActionListener(this::addLab);
        addTutorialButton.addActionListener(this::addTutorial);
        removeButton.addActionListener(this::removeSession);

    }

    private void addLecture(ActionEvent e) {
        createSession(course, sessionsListModel, SessionType.LECTURE);
    }

    private void addLab(ActionEvent e) {
        createSession(course, sessionsListModel, SessionType.LAB);
    }

    private void addTutorial(ActionEvent e) {
        createSession(course, sessionsListModel, SessionType.TUTORIAL);
    }

    private void removeSession(ActionEvent e) {
        int selectedIndex = sessionsList.getSelectedIndex();
        if (selectedIndex != -1) {
            course.removeClassSession(sessionsListModel.get(selectedIndex));
            sessionsListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a session to remove.");
        }
    }
    
    private void createSession(Course course, DefaultListModel<ClassSession> sessionsListModel, SessionType sessionType) {
        JComboBox<DaysOfWeek> daysComboBox = new JComboBox<>(DaysOfWeek.values());
        JComboBox<LocalTime> startComboBox = createTimeComboBox();
        JComboBox<LocalTime> endComboBox = createTimeComboBox();
        JTextField locationField = createTextField(200, 25);
        
        
        JPanel sessionPanel = createSessionPanel(daysComboBox, startComboBox, endComboBox, locationField);
        
        JTextField tutorField = null;
        if (sessionType != SessionType.LECTURE) {
            tutorField = createTextField(200,25);
            sessionPanel.add(new JLabel("Tutor:"));
            sessionPanel.add(tutorField);
        }
        
        // Confirm text
        String title = getDialogTitle(sessionType);
        int result = JOptionPane.showConfirmDialog(null, sessionPanel, title, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            ClassSession session = createSessionObject(sessionType, daysComboBox, startComboBox, endComboBox, locationField, tutorField, course);
            course.addClassSession(session);
            sessionsListModel.addElement(session);
        }
    }

    private ClassSession createSessionObject(SessionType sessionType, JComboBox<DaysOfWeek> daysComboBox, JComboBox<LocalTime> startComboBox, JComboBox<LocalTime> endComboBox, JTextField locationField, JTextField tutorField, Course course) {
        DaysOfWeek selectedDay = (DaysOfWeek) daysComboBox.getSelectedItem();
        LocalTime startTime = (LocalTime) startComboBox.getSelectedItem();
        LocalTime endTime = (LocalTime) endComboBox.getSelectedItem();
        String location = locationField.getText();

        return switch (sessionType) {
            case LECTURE -> new Lecture(selectedDay, startTime, endTime, location, course);
            case LAB -> new Lab(selectedDay, startTime, endTime, location, tutorField.getText(), course);
            case TUTORIAL -> new Tutorial(selectedDay, startTime, endTime, location, tutorField.getText(), course);
        };
    }

    private String getDialogTitle(SessionType sessionType) {
        return switch (sessionType) {
            case LECTURE -> "Add Lecture";
            case LAB -> "Add Lab";
            case TUTORIAL -> "Add Tutorial";
        };
    }

    private JTextField createTextField(int width, int height) {
        JTextField res = new JTextField();
        res.setPreferredSize(new Dimension(width, height));
        return res;
    }

    private JPanel createSessionPanel(JComboBox<DaysOfWeek> daysComboBox, JComboBox<LocalTime> startComboBox, JComboBox<LocalTime> endComboBox, JTextField locationField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Day:"));
        panel.add(daysComboBox);
        panel.add(new JLabel("Start Time:"));
        panel.add(startComboBox);
        panel.add(new JLabel("End Time:"));
        panel.add(endComboBox);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        return panel;
    }

    private JComboBox<LocalTime> createTimeComboBox() {
        LocalTime[] times = new LocalTime[11];
        LocalTime time = LocalTime.of(8, 0);
        for (int i = 0; i < 11; i++) {
            times[i] = time;
            time = time.plusHours(1);
        }
        return new JComboBox<>(times);
    }
}

