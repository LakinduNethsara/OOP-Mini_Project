package com.lecturer;

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

public class LecturerProfileUpdate extends JFrame {


    private JPanel lecProfile;
    private JTextField firstNameTextField;
    private JPanel lecProfileUpdateForm;
    private JButton saveButton;
    private JButton backButton;
    private JPanel lecUpdateProfileIcon;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JTextField addressTextField;
    private JTextField phonenoTextField;

    private String Fname;
    private String Uname;
    private String Lname;
    private String Email;
    private String Address;
    private String Phone_no;

    ResultSet r3;

    public LecturerProfileUpdate() {
        add(lecProfile);
        setVisible(true);
        setTitle("Lecturer Profile");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);

        Uname = UserDetails.getUsername();

        Connection con= null;



        try {
            con = dbConnection.dbConnect();
            String query="select first_name,last_name,email,address,DOB,phone_number from lecturer where user_name="+"'"+Uname+"'";
            Statement ps=con.createStatement();
            r3 = ps.executeQuery(query);
            r3.next();

            Fname = r3.getString("first_name");
            Lname = r3.getString("last_name");
            Email = r3.getString("email");
            Address = r3.getString("address");
            Phone_no = r3.getString("phone_number");

            firstNameTextField.setText(Fname);
            lastNameTextField.setText(Lname);
            emailTextField.setText(Email);
            addressTextField.setText(Address);
            phonenoTextField.setText(Phone_no);



        } catch (SQLException e) {

            throw new RuntimeException(e);

        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Fname = firstNameTextField.getText();
                Lname = lastNameTextField.getText();
                Email = emailTextField.getText();
                Address = addressTextField.getText();
                Phone_no = phonenoTextField.getText();

                Fname = "'"+Fname+"'";
                Lname = "'"+Lname+"'";
                Email = "'"+Email+"'";
                Address = "'"+Address+"'";
                Phone_no = "'"+Phone_no+"'";


                String query1="update lecturer set first_name="+Fname+",last_name="+Lname+",email="+Email+",address="+Address+",phone_number="+Phone_no+" where user_name="+"'"+Uname+"'";



                Connection con1;
                try {
                    con1 = dbConnection.dbConnect();
                    Statement ps1=con1.createStatement();
                    ps1.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Successfully Updated");

                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    emailTextField.setText("");
                    addressTextField.setText("");
                    phonenoTextField.setText("");

                    dispose();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }


}
