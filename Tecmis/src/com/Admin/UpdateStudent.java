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

public class UpdateStudent extends JFrame{
    private JPanel UpdateStudentLable;
    private JTextField textField1;
    private JButton button1;
    private JButton updateButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JComboBox comboBox3;
    private JTextField textField9;

    String stId,fName,lName,uName,password,eMail,address,dob,phoneNumber,gender,level;

    public UpdateStudent(){
        add(UpdateStudentLable);
        setVisible(true);
        setTitle("Update Student");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select student_id from student");
            comboBox2.removeAllItems();

            while (rs.next()) {
                comboBox2.addItem(rs.getString("student_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stId=(String) comboBox2.getSelectedItem();
                fName=textField1.getText();
                lName=textField2.getText();
                uName=textField3.getText();
                password=textField4.getText();
                eMail=textField5.getText();
                address=textField6.getText();
                dob= textField7.getText();
                phoneNumber= textField8.getText();
                gender=(String) comboBox3.getSelectedItem();

                try {
                    Connection con = dbConnection.dbConnect();
                    Statement ps = con.createStatement();
                    String query = "update student set student_id='"+stId+"' ,first_name='"+fName+"',last_name='"+lName+"',user_name='"+uName+"',password='"+password+"',email='"+eMail+"',address='"+address+"',DOB='"+dob+"',phone_number='"+phoneNumber+"',gender='"+gender+"',level="+level+" where student_id='"+stId+"'";
                    ps.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Successfully updated!!!");
                    dispose();

                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }


            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stId=(String) comboBox2.getSelectedItem();
                    Connection con = dbConnection.dbConnect();
                    Statement ps = con.createStatement();

                    String query = "select * from student where student_id = '"+stId+"'";
                    ResultSet rs = ps.executeQuery(query);
                    rs.next();

                    textField1.setText(rs.getString(2));
                    textField2.setText(rs.getString(3));
                    textField3.setText(rs.getString(4));
                    textField4.setText(rs.getString(5));
                    textField5.setText(rs.getString(6));
                    textField6.setText(rs.getString(7));
                    textField7.setText(rs.getString(8));
                    textField8.setText(rs.getString(9));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
