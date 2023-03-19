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

public class UpdateLecturer extends JFrame{
    private JPanel UpdateLecturerpanel;
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
    private JTextField txt9;
    private JTextField txt10;
    private JComboBox comboBox2;

    String LecId,fName,lName,Uname,password,eMail,address,dob,lable,phoneNumber,position,gender;
    public UpdateLecturer() {
    add(UpdateLecturerpanel);
    setVisible(true);
    setTitle("Update Lecturer");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk=Toolkit.getDefaultToolkit();
    Dimension screenSize=tk.getScreenSize();
    setSize(screenSize.width,screenSize.height);
    setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select lecturer_id from lecturer");
            comboBox1.removeAllItems();
            while (rs.next()) {
                comboBox1.addItem(rs.getString("lecturer_id"));
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
                LecId=(String)comboBox1.getSelectedItem();
                fName=txt1.getText();
                lName=txt2.getText();
                Uname=txt3.getText();
                password=txt4.getText();
                eMail=txt5.getText();
                address=txt6.getText();
                dob= txt7.getText();
                phoneNumber= txt9.getText();;
                position=txt10.getText();
                gender=(String) comboBox2.getSelectedItem();
                try {
                    Connection con = dbConnection.dbConnect();
                    Statement ps = con.createStatement();
                    String query = "update lecturer set lecturer_id= '"+LecId+"' ,first_name='"+fName+"',last_name='"+lName+"',User_name='"+Uname+"',password='"+password+"',email='"+eMail+"',address='"+address+"',DOB='"+dob+"',phone_number='"+phoneNumber+"',gender='"+gender+"',position='"+position+"' where lecturer_id = '"+LecId+"'";
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
