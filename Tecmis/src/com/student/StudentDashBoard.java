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
    public int level;
    public double gpa;

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




        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        updateMyProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentUpdateProfile stup=new StudentUpdateProfile();
                stup.loadUserDetails();
            }
        });
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentViewNotice();
            }
        });
        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentCourseList();
            }
        });
        medicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewMedical().viewMedicalList();
            }
        });
        attendanceButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewAttendance().viewAttendanceList();
            }
        });
        timetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentViewTimeTable();
            }
        });
    }

    public void loadUserDetails(){

        try {
            String currentUsername=UserDetails.getUsername();
            Connection con = dbConnection.dbConnect();
            String getStudentDetailsQuery="select first_name,last_name,level,CGPA,profile_pic_image from student where user_name="+"'"+currentUsername+"'";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(getStudentDetailsQuery);
            rs.next();
            first_name=rs.getString("first_name");
            last_name=rs.getString("last_name");
            usernameLabel.setText(first_name+" "+last_name);

            level=rs.getInt("level");
            userLevelLabel.setText("    Level "+level);

            gpa= rs.getDouble("CGPA");
            userGpaLabel.setText("GPA "+gpa);

            byte[] imagedata=rs.getBytes("profile_pic_image");
            ImageIcon format = new ImageIcon(imagedata);
            Image mm=format.getImage();
            Image img2= mm.getScaledInstance( 100, 100,Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(img2);
            pro_pic_label.setIcon(image);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
