package com.lecturer;

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

public class LecturerSeeStudentDetails extends JFrame {


    private JPanel LecturerSeeStudentDetails;
    private JButton backButton;
    private JTable table1;

    public LecturerSeeStudentDetails() {
        add(LecturerSeeStudentDetails);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);
        courseLoad();


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    void courseLoad(){

        try {
            Connection con=dbConnection.dbConnect();
            PreparedStatement pst2=con.prepareStatement("select student_id,first_name,last_name,email,address,phone_number,level,SGPA,CGPA from student ");
            ResultSet rs2= pst2.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(rs2));


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
