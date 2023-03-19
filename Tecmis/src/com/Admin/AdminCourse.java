package com.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCourse extends JFrame{

    private JLabel Courses;
    private JButton createCourseButton;
    private JButton updateCourseButton;
    private JButton deleteCourseButton;
    private JPanel AdminCoursePanel;
    private JButton backButton;

    public AdminCourse(){
        add(AdminCoursePanel);
        setVisible(true);
        setTitle("Maintain Courses");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setLocationRelativeTo(null);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseCreate cc =new CourseCreate();
            }
        });
        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCourse uc = new UpdateCourse();
            }
        });
        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCourse dc = new DeleteCourse();
            }
        });
    }
}


