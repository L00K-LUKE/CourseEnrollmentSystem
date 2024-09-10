package courseenrollmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;


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

    public JPanel getPanel() {
        return studentPanel;
    }

    public DefaultListModel<Student> getStudentListModel() {
        return studentListModel;
    }

    private void addStudent(ActionEvent e) {
        String studentName = JOptionPane.showInputDialog("Enter Student Name:");
        if (studentName != null && !studentName.trim().isEmpty()) {
            studentListModel.addElement(new Student(studentName, new Timetable()));
        }
    }

    private void removeStudent(ActionEvent e) {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            Student student = studentListModel.getElementAt(selectedIndex);
            Set<Course> courses = student.getTimetable().getCourses();
            for (Course course : courses) {
                student.unenroll(course);
            }

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
            JOptionPane.showMessageDialog(null, detailsPanel(studentListModel.get(selectedIndex)));
        }
    }

    private JPanel detailsPanel(Student student) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel studentDetails = new JLabel(student.getInfo());

        JLabel titleLabel = new JLabel("Enrolled on the Following Courses:");
        JLabel studentCourses = new JLabel();

        for (Course course : student.getTimetable().getCourses()) {
            studentCourses.setText(studentCourses.getText() + course.getCourseName() +", ");
        }

        panel.add(studentDetails);
        panel.add(titleLabel);
        panel.add(studentCourses);

        return panel;
    }


}