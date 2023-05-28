package com.lecturer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Double.parseDouble;

public class StudentEligibility extends JFrame{
    private JTextField textField18;
    private JButton searchButton;
    private JButton backButton;
    private JPanel studentEligibility;
    private JTextField textField19;
    private JLabel labelField20;

    private String Stid;
    private String SubCode;
    private Double CAMarks;


    ResultSet RS;

    public StudentEligibility( ) {
        add(studentEligibility);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMarks();
            }
        });
    }

    public void searchMarks() {
        Stid = textField18.getText();
        SubCode = textField19.getText();

        String query = "select CA_marks from marks where student_id="+"'"+Stid+"'"+" and sub_code="+"'"+SubCode+"'";
        Connection con;

        try {
            con = dbConnection.dbConnect();
            Statement ps=con.createStatement();
            RS = ps.executeQuery(query);
            RS.next();

            //CAMarks = parseDouble(RS.getString("CA_marks"));
            CAMarks =RS.getDouble("CA_marks");


            if(CAMarks>=50)
            {
                labelField20.setText("Eligible");
            }

            else
            {
                labelField20.setText("Not Eligible");
            }



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
}
