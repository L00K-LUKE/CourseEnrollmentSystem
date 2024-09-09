package courseenrollmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CoursePanel {
    
    private JPanel coursePanel;
    private DefaultListModel<Course> courseListModel;
    private JList<Course> courseList;
    private DefaultListModel<Student> studentListModel;
    
    public CoursePanel(DefaultListModel<Student> studentListModel) {
        coursePanel = new JPanel(new BorderLayout());
        this.studentListModel = studentListModel;
        initialiseComponents();
    }

    private void initialiseComponents() {
        // Components and Layout
        JPanel botPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Course");
        JButton removeButton = new JButton("Remove Course");
        JButton viewClassesButton = new JButton("View/Edit Classes");
        JButton enrollButton = new JButton("Enroll Students");

        botPanel.add(addButton);
        botPanel.add(removeButton);
        botPanel.add(viewClassesButton);
        botPanel.add(enrollButton);

        coursePanel.add(botPanel, BorderLayout.SOUTH);

        // Center panel for course list

        courseListModel = new DefaultListModel<>();
        courseList = new JList<>(courseListModel);

        coursePanel.add(new JScrollPane(courseList), BorderLayout.CENTER);

        // Action Listeners
        addButton.addActionListener(this::addCourse);
        removeButton.addActionListener(this::removeCourse);
        viewClassesButton.addActionListener(this::viewClasses);
        enrollButton.addActionListener(this::enrollStudents);
    }

    private void addCourse(ActionEvent e) {
        JTextField courseNameField = new JTextField();
        JTextField lecturerNameField = new JTextField();
        JPanel inputPanel = createInputPanel(courseNameField, lecturerNameField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Please input Course Name and Lecturer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            courseListModel.addElement(new Course(courseNameField.getText(), lecturerNameField.getText()));
        }
    }

    private JPanel createInputPanel(JTextField courseNameField, JTextField lecturerNameField) {
        courseNameField.setPreferredSize(new Dimension(200,25));
        lecturerNameField.setPreferredSize(new Dimension(200,25));

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Course Name:"));
        inputPanel.add(courseNameField);
        inputPanel.add(Box.createHorizontalStrut(15));
        inputPanel.add(new JLabel("Course Lecturer:"));
        inputPanel.add(lecturerNameField);

        return inputPanel;
    }

    private void removeCourse(ActionEvent e) {
        int selectedIndex = courseList.getSelectedIndex();
        if (selectedIndex != -1) {
            courseListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a course to remove.");
        }
    }

    private void viewClasses(ActionEvent e) {
        int selectedIndex = courseList.getSelectedIndex();

        if (selectedIndex != -1) {
            new SessionFrame(courseListModel.get(selectedIndex));
        } else {
            JOptionPane.showMessageDialog(null, "Please select a course to view its classes.");
        }
    }

    private void enrollStudents(ActionEvent e) {
        int selectedIndex = courseList.getSelectedIndex();

        if (selectedIndex != -1) {
            Course selectedCourse = courseListModel.get(selectedIndex);

            DefaultListModel<Student> allStudents = studentListModel;

            if (allStudents.size() == 0) {
                JOptionPane.showMessageDialog(null, "There are no students available for enrollment.");
                return;
            }

            JList<Student> studentList = new JList<>(allStudents);
            studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            int result = JOptionPane.showConfirmDialog(null, new JScrollPane(studentList), "Select a Student to Enrol", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                Student selectedStudent = studentList.getSelectedValue();
                if (selectedStudent != null)  {
                    if (selectedStudent.enroll(selectedCourse)) {
                        JOptionPane.showMessageDialog(null, "Student Enrolled Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Student Enrollment Failed- Check Timetable for Clashes");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No student was selected");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please select a course to enrol students into.");
        }
    }

    public Component getPanel() {
        return coursePanel;
    }
}