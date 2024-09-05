package courseenrollmentsystem;

import javax.swing.*;
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
        JPanel coursePanel = coursePanel();
        tabbedPane.addTab("Courses", coursePanel);


        // Create student panel
        JPanel studentPanel = studentPanel();
        tabbedPane.addTab("Students", studentPanel);

        // Add tabbed pane to frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    public JPanel coursePanel() {
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BorderLayout());

        JButton addButton;
        JButton removeButton;
        DefaultListModel<Course> courseListModel;
        JList<Course> courseList;

        // Top Panel (Add and Remove Course Buttons)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        addButton = new JButton("Add Course");
        removeButton = new JButton("Remove Course");

        topPanel.add(addButton);
        topPanel.add(removeButton);

        coursePanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel (List of Courses)
        JPanel centerPanel = new JPanel();
        courseListModel = new DefaultListModel<>();
        courseList = new JList<>(courseListModel);
        courseList.setBackground(Color.WHITE);
        courseList.setForeground(Color.BLACK);

        coursePanel.add(new JScrollPane(courseList), BorderLayout.CENTER);

        // Action Listeners

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField courseNameField = new JTextField();
                JTextField lecturerNameField = new JTextField();

                courseNameField.setPreferredSize(new Dimension(200,25));
                lecturerNameField.setPreferredSize(new Dimension(200,25));

                JPanel iptPanel = new JPanel();
                iptPanel.setLayout(new FlowLayout());
                iptPanel.add(new JLabel("Course Name:"));
                iptPanel.add(courseNameField);
                iptPanel.add(Box.createHorizontalStrut(15));
                iptPanel.add(new JLabel("Course Lecturer:"));
                iptPanel.add(lecturerNameField);

                int result = JOptionPane.showConfirmDialog(null, iptPanel, "Please input Course Name and Lecturer", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    courseListModel.addElement(new Course(courseNameField.getText(), lecturerNameField.getText()));
                }
            }
        });


        return coursePanel;
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

        addButton = new JButton("Add Student");
        removeButton = new JButton("Remove Student");
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
                JOptionPane.showMessageDialog(null, "Timetable feature is not implemented yet.");
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = studentList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a student to view details.");
                }
                else {
                JOptionPane.showMessageDialog(null, studentListModel.get(selectedIndex).getInfo());

                }
            }
        });

        return studentPanel;
    }


    public static void main(String[] args) {
        new App();
    }
}
