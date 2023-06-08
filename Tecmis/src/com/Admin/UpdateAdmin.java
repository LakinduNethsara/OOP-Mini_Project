package com.Admin;

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

public class UpdateAdmin extends JFrame {
    private JTextField FnameTextfield;
    private JButton backButton;
    private JPanel UpdateAdminPanel;
    private JTextField LnameTextfield;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JTextField UsernameTextfield;
    private JTextField PasswordTextfield;
    private JTextField EmailTextfield;
    private JTextField AddressTextfield;
    private JTextField PhonenumberTextfield;
    private JButton updateButton;

    private String Fname;
    private String Lname;
    private String Uname;
    private String Password;
    private String Email;
    private String Address;
    private String Phonenumber;

    private String Username;

    ResultSet r3;

    public UpdateAdmin(){
        add(UpdateAdminPanel);
        setVisible(true);
        setTitle("Update Admin Profile");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setLocationRelativeTo(null);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();}});
       Uname = UserDetails.getUsername();
        Connection con=null;


        try{
            con = dbConnection.dbConnect();
            String query = "select first_name,last_name,email,Phone_number,address,password,user_name from admin where user_name="+"'"+Uname+"'";
            Statement ps=con.createStatement();
            r3=ps.executeQuery(query);
            r3.next();

            Fname = r3.getString("first_name");
            Lname = r3.getString("last_name");
            Email = r3.getString("email");
            Address = r3.getString("address");
            Phonenumber = r3.getString("Phone_number");
            Password =r3.getString("password");
            Username= r3.getString("user_name");


            FnameTextfield.setText(Fname);
            LnameTextfield.setText(Lname);
            EmailTextfield.setText(Email);
            AddressTextfield.setText(Address);
            PhonenumberTextfield.setText(Phonenumber);
            UsernameTextfield.setText(Username);
            PasswordTextfield.setText(Password);


        }catch (SQLException e){
            throw new RuntimeException();
        }


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fname = FnameTextfield.getText();
                Lname=LnameTextfield.getText();
                Email=EmailTextfield.getText();
                Address=AddressTextfield.getText();
                Phonenumber=PhonenumberTextfield.getText();
                Username=UsernameTextfield.getText();
                Password=PasswordTextfield.getText();

                Fname = "'"+Fname+"'";
                Lname ="'"+Lname+"'";
                Email ="'"+Email+"'";
                Address ="'"+Address+"'";
                Phonenumber ="'"+Phonenumber+"'";
                Username ="'"+Username+"'";
                Password = "'"+Password+"'";




                Connection con;

                String query1 = "Update admin set first_name="+Fname+",last_name="+Lname+",email="+Email+",address="+Address+",user_name="+Username+",phone_number="+Phonenumber+",password="+Password+"where user_name="+"'"+Uname+"'";

                try {
                    con = dbConnection.dbConnect();
                    Statement ps=con.createStatement();
                    ps.executeUpdate(query1);

                    JOptionPane.showMessageDialog(null, "Successfully updated");
                    dispose();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

