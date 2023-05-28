package com.lecturer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentEligibility extends JFrame {
    private JTextField textField18;
    private JButton searchButton;
    private JButton backButton;
    private JPanel studentEligibility;
    private JTextField textField19;
    private JLabel labelField20;
    private JComboBox Field18;
    private JComboBox Field19;

    private String Stid;
    private String SubCode;
    private static Double CAMarks;


    static ResultSet RS;

    public StudentEligibility() {
        add(studentEligibility);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width, screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);

        ResultSet R1 = LecturerDB.allStudentId();

        Field18.removeAllItems();
        Field19.removeAllItems();

        try {
            while(R1.next())
            {
                Field18.addItem(R1.getString("student_id"));
            }
        }
        catch (SQLException e) {
                throw new RuntimeException(e);
        }

        Field18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String stid= (String) Field18.getSelectedItem();

                ResultSet R2 = LecturerDB.subjectsForSid(stid);


                try {
                    while(R2.next())
                    {
                        Field19.addItem(R2.getString("sub_code"));
                    }
                }
                catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });





        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Stid = (String) Field18.getSelectedItem();
                SubCode = (String) Field19.getSelectedItem();

                labelField20.setText(studenFinalEligibility(Stid, SubCode));
            }
        });

    }



    //Function for get student eligibility for a subject
    public static String studenFinalEligibility(String Stid, String SubCode) {
        String status = "";
        String CAstatus = searchCAMarks(Stid, SubCode);
        String Attenstatus = searchAttendance(Stid, SubCode);

        if(CAstatus.equals("Eligible") && Attenstatus.equals("Eligible"))
            status = "Eligible";
        else
            status = "Not Eligible";

        return status;
    }


    //Function for get CA marks state for a subject of Student
    public static String searchCAMarks(String Stid, String SubCode) {

        String query = "select CA_marks from marks where student_id=" + "'" + Stid + "'" + " and sub_code=" + "'" + SubCode + "'";
        Connection con;
        String status = "";


        try {
            con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            RS = ps.executeQuery(query);
            RS.next();

            //CAMarks = parseDouble(RS.getString("CA_marks"));
            CAMarks = RS.getDouble("CA_marks");

            if (CAMarks >= 50) {
                status = "Eligible";
            } else {
                status = "Not Eligible";
            }


        } catch (SQLException ex) {
            status = "Not Eligible";
        }

        return status;
    }


    //Function for Attendance state for a subject of Student
    public static String searchAttendance(String Stid, String SubCode) {

        String query = "SELECT COUNT(*) AS count FROM (SELECT student_id FROM attendance WHERE attendance.student_id = '"+Stid+"'AND attendance.sub_code = '"+SubCode+"' AND attendance.attempt_status='Yes' UNION ALL SELECT student_id FROM medical WHERE medical.student_id = '"+Stid+"' AND medical.sub_code = '"+SubCode+"' AND medical.approve_status= 'Yes') AS combined_results";

        String query1 = "SELECT COUNT(*) AS count FROM attendance WHERE student_id = '"+Stid+"'AND sub_code = '"+SubCode+"'";

        String status = "";
        Connection con;
        int SAt,FAt = 0;

        try {
            con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            RS = ps.executeQuery(query);
            RS.next();
            SAt = RS.getInt("count");


            RS = ps.executeQuery(query1);
            RS.next();
            FAt = RS.getInt("count");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        double AttPrecentage = ((double) SAt/(double) FAt)*100;


        if (AttPrecentage >= 80) {
            status = "Eligible";
        } else {
            status = "Not Eligible";
        }

        return status;
    }




}