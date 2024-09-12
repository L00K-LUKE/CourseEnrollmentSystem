package courseenrollmentsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CoursePanel {

    private JPanel coursePanel;
    private DefaultListModel<Course> courseListModel;
    private JList<Course> courseList;

    public CoursePanel() {
        coursePanel = new JPanel(new BorderLayout());
        initialiseComponents();
    }

    private void initialiseComponents() {
        // Components and Layout
        JPanel botPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Course");
        JButton removeButton = new JButton("Remove Course");
        JButton viewClassesButton = new JButton("View/Edit Classes");
        JButton viewStudents = new JButton("View Students");

        botPanel.add(addButton);
        botPanel.add(removeButton);
        botPanel.add(viewClassesButton);
        botPanel.add(viewStudents);

        coursePanel.add(botPanel, BorderLayout.SOUTH);

        // Center panel for course list

        courseListModel = new DefaultListModel<>();
        courseList = new JList<>(courseListModel);

        coursePanel.add(new JScrollPane(courseList), BorderLayout.CENTER);

        // Action Listeners
        addButton.addActionListener(this::addCourse);
        removeButton.addActionListener(this::removeCourse);
        viewClassesButton.addActionListener(this::viewClasses);
        viewStudents.addActionListener(this::viewStudents);

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
        courseNameField.setPreferredSize(new Dimension(200, 25));
        lecturerNameField.setPreferredSize(new Dimension(200, 25));

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
            Course course = courseListModel.get(selectedIndex);
            course.getStudents().forEach(student -> student.unenroll(course));
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

    private void viewStudents(ActionEvent event) {
        int selectedIndex = courseList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a course to view its students.");
            return;
        }

        Course selectedCourse = courseListModel.get(selectedIndex);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Students Enrolled on Course:");

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Students");
        JTable table = new JTable(tableModel);

        for (Student student : selectedCourse.getStudents()) {
            String[] row = {student.getInfo()};
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel);

    }

    // Getters

    public Component getPanel() {
        return coursePanel;
    }

    public DefaultListModel<Course> getCourseListModel() {
        return courseListModel;
    }
}
