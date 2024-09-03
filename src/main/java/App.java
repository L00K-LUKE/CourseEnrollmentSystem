package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame{

    public App() {
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


        // Create student panel
        JPanel studentPanel = studentPanel();
        tabbedPane.addTab("Students", studentPanel);

        // Add tabbed pane to frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    public JPanel studentPanel() {

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());

        JButton addButton;
        JButton removeButton;
        JButton timetableButton;
        JButton detailsButton;
        DefaultListModel<Student> studentListModel;
        JList<Student> studentList;

        // Center with list
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setBackground(Color.WHITE);
        studentList.setForeground(Color.BLACK);

        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);

        // Bottom Panel with add, remove, timetable, and view details buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        timetableButton = new JButton("Timetable");
        detailsButton = new JButton("Details");
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(timetableButton);
        bottomPanel.add(detailsButton);

        studentPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Button Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = JOptionPane.showInputDialog("Enter Student Name:");
                if (studentName != null && !studentName.trim().isEmpty()) {
                    studentListModel.addElement(new Student(studentName, new Timetable()));
                }
            }
        });

        // TODO: Automatically un-enroll students when they are deleted
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = studentList.getSelectedIndex();
                if (selectedIndex != -1) {
                    studentListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a student to remove.");
                }
            }
        });

        timetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle Timetable button click
                JOptionPane.showMessageDialog(null, "Timetable feature is not implemented yet.");
            }
        });

        return studentPanel;
    }


    public static void main(String[] args) {
        new App();
    }
}
