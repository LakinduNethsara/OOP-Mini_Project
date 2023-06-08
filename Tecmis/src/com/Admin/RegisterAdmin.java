package com.Admin;

import com.DBconnection.dbConnection;
import com.login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterAdmin extends JFrame {
    private JTextField idInput;
    private JTextField lastNameInput;
    private JComboBox genderInput;
    private JButton registerButton;
    private JButton clearButton;
    private JButton backButton;
    private JPanel registerAdminPanel;
    private JTextField firstNameInput;
    private JTextField userNameInput;
    private JTextField emailInput;
    private JTextField addressInput;
    private JTextField DoBInput;
    private JTextField phoneNumberInput;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField secretCode;

    Connection con;
    PreparedStatement pst;

    private String id, fName, lName, uName, email, pass1, pass2, sCode, address, dob, phoneNo, gender;

    public RegisterAdmin() {
        add(registerAdminPanel);
        setVisible(true);
        setTitle("Register Admin Profile");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Toolkit tk=Toolkit.getDefaultToolkit();
        //Dimension screenSize=tk.getScreenSize();
        //setSize(screenSize.width,screenSize.height);
        setSize(400, 550);
        setLocationRelativeTo(null);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idInput.setText("");
                firstNameInput.setText("");
                lastNameInput.setText("");
                userNameInput.setText("");
                emailInput.setText("");
                passwordField1.setText("");
                passwordField2.setText("");
                secretCode.setText("");
                addressInput.setText("");
                DoBInput.setText("");
                phoneNumberInput.setText("");


            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerAdmin();        //Calling the method to register an admin
            }
        });
    }

    public void registerAdmin() {           //Method that register the admin
        id = idInput.getText();
        fName = firstNameInput.getText();
        lName = lastNameInput.getText();
        uName = userNameInput.getText();
        email = emailInput.getText();
        pass1 = String.valueOf(passwordField1.getPassword());
        pass2 = String.valueOf(passwordField2.getPassword());
        sCode = String.valueOf(secretCode.getPassword());
        address = addressInput.getText();
        dob = DoBInput.getText();
        phoneNo = phoneNumberInput.getText();
        gender = (String) genderInput.getSelectedItem();


        if(id!=null && fName!=null && lName!=null && uName!=null && email!=null && address!=null && dob!=null && phoneNo!=null){
            if(sCode.equals("fotadmin")){
                if(pass1.equals(pass2))
                {
                    con= dbConnection.dbConnect();
                    String registerAdminQuery="insert into admin values(?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst=con.prepareStatement(registerAdminQuery);
                        pst.setString(1,id);
                        pst.setString(2,fName);
                        pst.setString(3,lName);
                        pst.setString(4,uName);
                        pst.setString(5,pass1);
                        pst.setString(6,email);
                        pst.setString(7,address);
                        pst.setString(8,dob);
                        pst.setString(9,phoneNo);
                        pst.setString(10,gender);

                        int result=pst.executeUpdate();
                        if(result>0){
                            JOptionPane.showMessageDialog(null,"Successfully Registered...");
                            dispose();
                            new Login();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Could not register...");
                        }

                    } catch (SQLException e) {
                        System.out.println("Error with prepared Statement");
                    }


                }
                else{
                    JOptionPane.showMessageDialog(null,"Passwords are not matched");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Secret code is invalid");
            }

        }
        else{
            JOptionPane.showMessageDialog(null,"All fields need to be filled");

        }



    }

}
