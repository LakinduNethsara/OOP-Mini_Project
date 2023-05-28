package com.technicalOfficer;

import com.login.Login;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechnicaOfficerDashBoard extends JFrame {

    private JPanel TOdashboard;
    private JLabel technicalofficerdashboard;
    private JPanel TODpanel1;
    private JLabel TODprofile;
    private JButton TODprofilebtn;
    private JPanel TODpanel2;
    private JLabel TODattendance;
    private JButton TODattendancebtn;
    private JPanel TODpanel3;
    private JLabel TODmedicine;
    private JButton TODmedicinebtn;
    private JPanel TODpanel4;
    private JLabel TODnotices;
    private JButton TODnoticesbtn;
    private JPanel TOpanel;
    private JLabel TOtimetable;
    private JButton TOtimetablebtn;
    private JButton TODlogoutbtn;


    public TechnicaOfficerDashBoard(){
        add(TOdashboard);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Technical Officer Dashboard");

        Toolkit tk = Toolkit.getDefaultToolkit();   //Initialize toolkit class
        Dimension screenSize=tk.getScreenSize();    //Get the screen resolution
        setSize(screenSize.width,screenSize.height);    //Set window width and height
        setLocationRelativeTo(null);    //center the window

        TODprofilebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficerProfile toProfile=new TechnicalOfficerProfile();
            }
        });
        TODlogoutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserDetails.setUserDetails(null,null,null);
               new Login();
            }
        });
        TODattendancebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficerAttendance toAttendance=new TechnicalOfficerAttendance();
            }
        });


        TODmedicinebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new TechnicalOfficerMedical();
    }

});

        TODnoticesbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficerNotice technicalOfficerNotice=new TechnicalOfficerNotice();
            }
        });
        TOtimetablebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TechnicalOfficerTimeTable();
            }
        });
    }}