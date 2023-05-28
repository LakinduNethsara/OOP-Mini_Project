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
    Connection con;

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

        //Back button functionality
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    //Method to view attendance
    public void viewAttendanceList(){
        con= dbConnection.dbConnect();

            //get student id
            StudentDetails studentDetails=new StudentDetails();
            myId=studentDetails.getId();


        try {
            //get attendance details from database
            con=dbConnection.dbConnect();
            PreparedStatement pst2=con.prepareStatement("select sub_code,type,date,attempt_status from attendance where student_id='"+myId+"'");
            ResultSet rs2= pst2.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error in closing db connection..."+e.getMessage());
            }
        }
    }
}
