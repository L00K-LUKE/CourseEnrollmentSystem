package courseenrollmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class StudentPanel {
    private JPanel studentPanel;
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;

    public StudentPanel() {
        studentPanel = new JPanel(new BorderLayout());
        initialiseComponents();
    }

    private void initialiseComponents() {
        // Components and Layout
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton timetableButton = new JButton("Timetable");
        JButton detailsButton = new JButton("Details");

        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(timetableButton);
        bottomPanel.add(detailsButton);

        studentPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(this::addStudent);
        removeButton.addActionListener(this::removeStudent);
        timetableButton.addActionListener(this::viewTimetable);
        detailsButton.addActionListener(this::viewDetails);
    }

    private void addStudent(ActionEvent e) {
        String studentName = JOptionPane.showInputDialog("Enter Student Name:");
        if (studentName != null && !studentName.trim().isEmpty()) {
            studentListModel.addElement(new Student(studentName, new Timetable()));
        }
    }


    //TODO: un-enrol students from courses when they are deleted
    private void removeStudent(ActionEvent e) {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            studentListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a student to remove.");
        }
    }

    private void viewTimetable(ActionEvent e) {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            new TimetableFrame(studentListModel.get(selectedIndex).getTimetable());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a student to view their timetable.");
        }
    }

    private void viewDetails(ActionEvent e) {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a student to view details.");
        }
        else {
            JOptionPane.showMessageDialog(null, studentListModel.get(selectedIndex).getInfo());
        }
    }

    public JPanel getPanel() {
        return studentPanel;
    }
}