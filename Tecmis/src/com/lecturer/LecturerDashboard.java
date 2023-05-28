package com.lecturer;

import com.DBconnection.dbConnection;
import com.login.Login;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturerDashboard extends JFrame {


    private JPanel lecturerDash;
    private JLabel lecTitle;
    private JPanel panel1;
    private JPanel panel2;
    private JButton profileButton;
    private JLabel lecProfile;
    private JButton lecMaterial;
    private JButton lec_stu_details;
    private JLabel lecMaterial1;
    private JButton lecNotice1;
    private JLabel lecName1;
    private JButton leclogoutButton;

    private String Fname;
    private String Lname;
    private String Uname;

    ResultSet r3;

    public LecturerDashboard()  {

        add(lecturerDash);
        setVisible(true);
        setTitle("Lecture Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);
        Uname = UserDetails.getUsername();

        try {
            Connection con= dbConnection.dbConnect();
            String query="select first_name,last_name from lecturer where user_name="+"'"+Uname+"'";
            Statement ps=con.createStatement();
            r3 = ps.executeQuery(query);
            r3.next();

            Fname = r3.getString("first_name");
            Lname = r3.getString("last_name");


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


        lecName1.setText("Hi, "+Fname+" "+Lname);


        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecturerProfileUpdate lecprofupdate = new LecturerProfileUpdate();

            }
        });
        leclogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });

        lecMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecturerMaterial lecMat1 = new LecturerMaterial();
            }
        });

        lec_stu_details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecturerStudentDashboard lecStuDash = new LecturerStudentDashboard();
            }
        });

        lecNotice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerNotice();
            }
        });
    }
}
