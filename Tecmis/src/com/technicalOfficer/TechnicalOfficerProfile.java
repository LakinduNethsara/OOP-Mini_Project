package com.technicalOfficer;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TechnicalOfficerProfile extends JFrame {
    private JPanel Technicalofficerprofilepanel;
    private JPanel TOPpanel;
    private JPanel TOPpanel1;
    private JPanel TOPpanel2;
    private JLabel TOPlabel;
    private JLabel TOPlabel2;
    private JLabel TOPlabel4;
    private JTextField textField2;
    private JLabel TOPlabel5;
    private JTextField textField3;
    private JLabel TOPlabel6;
    private JTextField textField4;
    private JLabel TOPlabel7;
    private JTextField textField6;
    private JTextArea textArea1;
    private JLabel TOPlabel9;
    private JTextField textField5;
    private JTextField textField7;
    private JButton TOPsavebtn1;
    private JButton TOPbakebtn2;
    private JButton updateButton;
    private JButton saveButton;

    String Uname;
    ResultSet rs;

    String Fname;
    String Lname;
    String Email;
    String Address;
    String Phone_no;

    public  TechnicalOfficerProfile(){
        add(Technicalofficerprofilepanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Technical Officer Profile");

        Toolkit tk = Toolkit.getDefaultToolkit();   //Initialize toolkit class
        Dimension screenSize=tk.getScreenSize();    //Get the screen resolution
        setSize(screenSize.width,screenSize.height);    //Set window width and height
        setLocationRelativeTo(null);    //center the window

        Uname= UserDetails.getUsername();
        Connection con=null;

        try{
            con = dbConnection.dbConnect();
            String query="select first_name,last_name,email,address,phone_number from technical_officer where user_name="+"'"+Uname+"'";
            Statement ps= con.createStatement();
            rs = ps.executeQuery(query);
            rs.next();

             Fname = rs.getString("first_name");
             Lname = rs.getString("last_name");
             Email = rs.getString("email");
             Address = rs.getString("address");
             Phone_no = rs.getString("phone_number");

            textField2.setText(Fname);
            textField3.setText(Lname);
            textField4.setText(Email);
            textArea1.setText(Address);
            textField5.setText(Phone_no);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fname = textField2.getText();
                Lname = textField3.getText();
                Email = textField4.getText();
                Address = textArea1.getText();
                Phone_no = textField5.getText();

                Fname = "'"+Fname+"'";
                Lname ="'"+Lname+"'";
                Email ="'"+Email+"'";
               Address ="'"+Address+"'";
                Phone_no ="'"+Phone_no+"'";

                String query ="Update technical_officer set first_name=" +Fname+ ",last_name=" +Lname+ ","+ "email=" +Email+ "," +"address=" +Address+ ","+ "phone_number=" +Phone_no + " where user_name="+"'"+Uname+"'";
                Connection con;

                JOptionPane.showMessageDialog(null, "Your Deatails Uploaded.....!!!");

                try {
                    con = dbConnection.dbConnect();
                    Statement ps= con.createStatement();
                    ps.executeUpdate(query);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        TOPbakebtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }}
