package courseenrollmentsystem;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame{

    public App() {
        // Create main frame
        JFrame frame = new JFrame("Timetable Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create student panel
        StudentPanel studentPanel = new StudentPanel();
        tabbedPane.addTab("Students", studentPanel.getPanel());

        // Create course panel
        CoursePanel coursePanel = new CoursePanel(studentPanel.getStudentListModel());
        tabbedPane.addTab("Courses", coursePanel.getPanel());

        // Add tabbed pane to frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
