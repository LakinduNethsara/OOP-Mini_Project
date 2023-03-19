package com.Admin;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTechnicalOfficer extends JFrame{
    private JPanel UpdateTechnicalOfficerPanel;
    private JButton backButton;
    private JButton updateButton;
    private JComboBox comboBox1;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;
    private JTextField txt6;
    private JTextField txt7;
    private JTextField txt8;
    private JTextField txt9;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    String officerID,fName,lName,Uname,Password,eMail,address,dob,phoneNumber,gender;


public UpdateTechnicalOfficer() {
    add(UpdateTechnicalOfficerPanel);
    setVisible(true);
    setTitle("Update technical officer");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    setSize(screenSize.width, screenSize.height);
    setLocationRelativeTo(null);

    try {
        Connection con = dbConnection.dbConnect();
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery("select officer_id from technical_officer");
        comboBox1.removeAllItems();
        while (rs.next()) {
            comboBox1.addItem(rs.getString("officer_id"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    try {
        Connection con= dbConnection.dbConnect();
        Statement ps=con.createStatement();
        ResultSet rs = ps.executeQuery("select dep_id from department");
        comboBox2.removeAllItems();
        while (rs.next()){
            comboBox2.addItem(rs.getString("dep_id"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }




    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           dispose();
        }
    });


    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            officerID=(String)comboBox1.getSelectedItem();
            fName=txt1.getText();
            lName=txt2.getText();
            Uname=txt3.getText();
            Password=txt4.getText();
            eMail=txt5.getText();
            address=txt6.getText();
            dob= txt7.getText();
            phoneNumber=txt8.getText();
            gender=(String) comboBox3.getSelectedItem();

            try {
                Connection con = dbConnection.dbConnect();
                Statement ps = con.createStatement();
                String query = "update technical_officer set officer_id='"+officerID+"' ,first_name='"+fName+"',last_name='"+lName+"',user_name='"+Uname+"',password='"+Password+"',email='"+eMail+"',address='"+address+"',DOB='"+dob+"',phone_number='"+phoneNumber+"',gender='"+gender+"' where officer_id='"+officerID+"'";
                ps.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully updated!!!");
                dispose();

            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }


        }
    });



}
}
