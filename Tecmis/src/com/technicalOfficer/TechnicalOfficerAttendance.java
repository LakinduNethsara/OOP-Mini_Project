package com.technicalOfficer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TechnicalOfficerAttendance extends JFrame {

    private JPanel TOPattendancepanel;
    private JPanel TOApanel1;
    private JPanel TOApanel2;
    private JLabel TOattendance1;
    private JLabel TOattendance2;
    private JLabel TOattendancelabel3;
    private JLabel TOattendancelabel4;
    private JTextField textField2;
    private JLabel TOattendencelabel5;
    private JLabel TOattendancelabel6;
    private JComboBox comboBox1;
    private JButton attendancebtn1;
    private JButton attendancebtn2;
    private JComboBox comboBox2;
    private JButton TOattendancebtn3;
    private JComboBox comboBox3;
    private JLabel TOattendancelabel7;
    private JComboBox comboBox4;

    ResultSet rs;

    String Scode;
    String type;
    String Stid;
    String Date;
    String status;
    private Connection con;


    public TechnicalOfficerAttendance() {
        add(TOPattendancepanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Technical Officer Attendance");

        Toolkit tk = Toolkit.getDefaultToolkit();   //Initialize toolkit class
        Dimension screenSize = tk.getScreenSize();    //Get the screen resolution
        setSize(screenSize.width, screenSize.height);    //Set window width and height
        setLocationRelativeTo(null);

        Connection con = null;


        try {
            con = dbConnection.dbConnect();
            String query="select sub_code from subject";
            Statement ps= con.createStatement();
            rs = ps.executeQuery(query);

            while (rs.next())
            {
                comboBox2.addItem(rs.getString("sub_code"));

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





        //center the window
        attendancebtn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        TOattendancebtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStudentList();
            }
        });
        attendancebtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });
    }

    public void  getStudentList(){
        Date=textField2.getText();
        Scode= (String) comboBox2.getSelectedItem();
        try {
            Connection con = dbConnection.dbConnect();
            String query="select student_id from student_subject where sub_code ="+"'"+Scode+"'";
            Statement ps= con.createStatement();
            rs = ps.executeQuery(query);

            comboBox3.removeAllItems();
            while (rs.next())
            {
                comboBox3.addItem(rs.getString("student_id"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }



    }

    public  void markAttendance(){
        Stid= (String) comboBox3.getSelectedItem();
        status= (String) comboBox1.getSelectedItem();
        type= (String)comboBox4.getSelectedItem();
        Connection con = null;
        try {
            con = dbConnection.dbConnect();
            Statement ps= con.createStatement();
            String query="insert into attendance values ("+"'"+Scode+"',"+"'"+type+"',"+"'"+Stid+"',"+"'"+Date+"',"+"'"+status+"')";
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Attendance Added.....!!!");
            comboBox3.setSelectedIndex(comboBox3.getSelectedIndex()+1);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }





    }
}