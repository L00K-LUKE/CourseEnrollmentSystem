package main.java;


import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("Timetable Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create course panel
        JPanel coursePanel = new JPanel();
        coursePanel.add(new JLabel("Manage Courses Here"));
        tabbedPane.addTab("Courses", coursePanel);

        // Create timetable panel
        JPanel timetablePanel = new JPanel();
        timetablePanel.add(new JLabel("Manage Timetable Here"));
        tabbedPane.addTab("Timetable", timetablePanel);

        // Create student panel
        JPanel studentPanel = new JPanel();
        studentPanel.add(new JLabel("Manage Students Here"));
        tabbedPane.addTab("Students", studentPanel);

        // Add tabbed pane to frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }
}
