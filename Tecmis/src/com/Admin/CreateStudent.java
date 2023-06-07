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

public class CreateStudent extends JFrame{
    private JPanel CreateStudentPanel;
    private JTextField textField1;
    private JButton backButton;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    String id,fName,lName,userName,password,email,address,dob,phoneNumber,gender,level,depId;


    public CreateStudent() {
    add(CreateStudentPanel);
    setVisible(true);
    setTitle("Create Student");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk=Toolkit.getDefaultToolkit();
    Dimension screenSize=tk.getScreenSize();
    setSize(screenSize.width,screenSize.height);
    setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select dep_id from department");
            comboBox2.removeAllItems();
            while (rs.next()) {
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createStudent();
            }
        });
    }
    public void createStudent(){
        id=textField1.getText();
        fName=textField2.getText();
        lName=textField3.getText();
        userName=textField4.getText();
        password=textField5.getText();
        email=textField6.getText();
        address=textField7.getText();
        dob=textField8.getText();
        phoneNumber=textField9.getText();
        level=(String)comboBox1.getSelectedItem();
        depId=(String)comboBox2.getSelectedItem();
        gender=(String) comboBox3.getSelectedItem();
        System.out.println(gender);

        try {
            Connection con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            String query="select student_id from student";
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String readStudentId = rs.getString("student_id");
                if (id.equals(readStudentId)) {
                    JOptionPane.showMessageDialog(null, "Student is exists");
                    dispose();
                    new CreateStudent();
                }
            }
            String insertQuery="insert into student (student_id,first_name,last_name,user_name,password,email,address,DOB,phone_number,gender,level,dep_id) values("+"'"+id+"','"+fName+"','"+lName+"','"+userName+"','"+password+"','"+email+"','"+address+"','"+dob+"','"+phoneNumber+"','"+gender+"','"+level+"','"+depId+"')";
            ps.executeUpdate(insertQuery);
            JOptionPane.showMessageDialog(null, "Added!!!");
            dispose();
            new AdminUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

}
