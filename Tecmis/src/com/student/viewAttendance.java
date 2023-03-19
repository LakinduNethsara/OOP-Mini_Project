package com.student;

import com.DBconnection.dbConnection;
import com.user.UserDetails;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewAttendance extends JFrame{
    private JPanel main;
    private JTable table1;
    private JButton backButton;

    public String myId;

    public viewAttendance(){
        add(main);
        setTitle("Attendance");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk=Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void viewAttendanceList(){

        try {
            Connection con1= dbConnection.dbConnect();
            PreparedStatement pst1 = con1.prepareStatement("select student_id from student where user_name= '"+ UserDetails.getUsername()+"'");


            ResultSet rs1= pst1.executeQuery();
            rs1.next();
            myId=rs1.getString("student_id");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

        try {
            Connection con2=dbConnection.dbConnect();
            PreparedStatement pst2=con2.prepareStatement("select sub_code,type,date,attempt_status from attendance where student_id='"+myId+"'");
            ResultSet rs2= pst2.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs2));
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
}
