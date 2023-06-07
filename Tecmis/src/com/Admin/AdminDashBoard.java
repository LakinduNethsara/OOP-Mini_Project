package com.Admin;

import com.login.Login;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashBoard extends JFrame{

    private JPanel AdminDashbroadPanel;
    private JButton userButton;
    private JButton courseButton;
    private JButton timeTableButton;
    private JButton noticeButton;
    private JButton logOutButton;
    private JButton assignSubjectsToUserButton;

    public AdminDashBoard(){
        add(AdminDashbroadPanel);
        setVisible(true);
        setTitle("Admin");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setLocationRelativeTo(null); //center the window
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUser AdUser =new AdminUser();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminCourse Adcourse =new AdminCourse();
            }
        });
        timeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminTimetable adTimetable = new AdminTimetable();
            }
        });


        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminNotice adNotice = new AdminNotice();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserDetails.setUserDetails(null,null,null);
                new Login();
            }
        });
        assignSubjectsToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignSubject();
            }
        });
    }

}

