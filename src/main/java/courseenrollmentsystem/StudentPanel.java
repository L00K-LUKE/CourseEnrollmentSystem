package courseenrollmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;


public class StudentPanel {
    private JPanel studentPanel;
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;
    private DefaultListModel<Course> courseListModel;

    public StudentPanel(DefaultListModel<Course> courseListModel) {
        studentPanel = new JPanel(new BorderLayout());
        this.courseListModel = courseListModel;
        initialiseComponents();
    }

    private void initialiseComponents() {
        // Components and Layout

        // Center Panel
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");

        topPanel.add(addButton);
        topPanel.add(removeButton);

        studentPanel.add(topPanel, BorderLayout.NORTH);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton timetableButton = new JButton("Timetable");
        JButton detailsButton = new JButton("Details");
        JButton enrolButton = new JButton("Enrol");
        JButton unEnrolButton = new JButton("Drop Course");

        bottomPanel.add(timetableButton);
        bottomPanel.add(detailsButton);
        bottomPanel.add(enrolButton);
        bottomPanel.add(unEnrolButton);

        studentPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(this::addStudent);
        removeButton.addActionListener(this::removeStudent);
        timetableButton.addActionListener(this::viewTimetable);
        detailsButton.addActionListener(this::viewDetails);
        enrolButton.addActionListener(this::enrolStudent);
        unEnrolButton.addActionListener(this::dropCourse);
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
        } else {
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
            studentCourses.setText(studentCourses.getText() + course.getCourseName() + ", ");
        }

        panel.add(studentDetails);
        panel.add(titleLabel);
        panel.add(studentCourses);

        return panel;
    }

    private void enrolStudent(ActionEvent e) {
        int selectedIndex = studentList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a student to enrol into a course.");
            return;
        }

        Student selectedStudent = studentListModel.get(selectedIndex);
        DefaultListModel<Course> allCourses = courseListModel;

        if (allCourses.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no courses available for enrollment.");
            return;
        }

        JList<Course> courseList = new JList<>(allCourses);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int result = JOptionPane.showConfirmDialog(null, new JScrollPane(courseList), "Select a Course to Enrol", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Course selectedCourse = courseList.getSelectedValue();
            if (selectedCourse == null) {
                JOptionPane.showMessageDialog(null, "No course was selected");
                return;
            }

            if (selectedStudent.enroll(selectedCourse)) {
                JOptionPane.showMessageDialog(null, "Student Enrolled Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Student Enrollment Failed- Check Timetable for Clashes");
            }
        }
    }

    private void dropCourse(ActionEvent event) {
        int selectedIndex = studentList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a student.");
            return;
        }

        Student selectedStudent = studentListModel.get(selectedIndex);
        Set<Course> enrolledInCourses = selectedStudent.getTimetable().getCourses();

        if (enrolledInCourses.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Student is not enrolled in any courses.");
            return;
        }

        DefaultListModel<Course> coursesModel = new DefaultListModel<>();
        enrolledInCourses.forEach(coursesModel::addElement);

        JList<Course> courseList = new JList<>(coursesModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int result = JOptionPane.showConfirmDialog(null, new JScrollPane(courseList), "Select a Course to Drop", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Course selectedCourse = courseList.getSelectedValue();
            if (selectedCourse == null) {
                JOptionPane.showMessageDialog(null, "No course was selected");
                return;
            }
            selectedStudent.unenroll(selectedCourse);
            JOptionPane.showMessageDialog(null, "Course has been Dropped");
        }

    }
}
