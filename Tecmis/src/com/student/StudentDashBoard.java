package com.student;

import com.DBconnection.dbConnection;
import com.login.Login;

import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class StudentDashBoard extends JFrame {
    private JPanel studentDashBoard;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel userLevelLabel;
    private JLabel userGpaLabel;
    private JPanel studentDashboardPanel3;
    private JLabel attendanceLabel;
    private JButton attendanceButtton;
    private JPanel studentDashboardPanel5;
    private JPanel studentDashboardPanel6;
    private JButton timetableButton;
    private JButton noticeButton;
    private JPanel studentDashboardBody;
    private JPanel studentDashboardPanel4;
    private JButton medicalButton;
    private JLabel homeImage;
    private JButton logoutButton;
    private JButton updateMyProfileButton;
    private JLabel pro_pic_label;
    private JButton courseButton;
    private String userName, first_name,last_name;
    public String level;
    public String gpa;
    Connection con;
    public StudentDashBoard(){

        add(studentDashBoard);
        setTitle("Student Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk=Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);




        //Log out button functionality
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });

        //Update profile button functionality
        updateMyProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentUpdateProfile stup=new StudentUpdateProfile();
                stup.loadUserDetails();
            }
        });

        //Notice button functionality
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentViewNotice();
            }
        });

        //course button functionality
        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentCourseList();
            }
        });

        //medical button functionality
        medicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewMedical().viewMedicalList();
            }
        });

        //attendance button functionality
        attendanceButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewAttendance().viewAttendanceList();
            }
        });

        //Timetable button functionality
        timetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentViewTimeTable();
            }
        });
    }

    //method to load user details
    public void loadUserDetails(){

            StudentDetails studentDetails=new StudentDetails();     //Create StudentDetails object to access student attributes
            first_name=studentDetails.getFirstName();
            last_name=studentDetails.getLastName();
            usernameLabel.setText(first_name+" "+last_name);

            level=studentDetails.getLevel();
            userLevelLabel.setText("    Level "+level);

            gpa=studentDetails.getCgpa();
            userGpaLabel.setText("GPA "+gpa);

            //display profile picture on the dashboard
        try {
            byte[] imageData = studentDetails.getProPic();
            ImageIcon format = new ImageIcon(imageData);
            Image mm = format.getImage();
            Image img2 = mm.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(img2);
            pro_pic_label.setIcon(image);
        }catch(NullPointerException e){}

    }
}

