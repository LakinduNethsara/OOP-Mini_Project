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

public class LecturerViewStudentAttendance extends JFrame {

    private JPanel lecStuAttendance;
    private JTextField textField25;
    private JButton backButton;
    private JButton searchButton;
    private JTextField textField26;
    private JTable table1;

    String id;
    String subcode;

    public LecturerViewStudentAttendance()  {

        add(lecStuAttendance);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {

                String  UN = textField25.getText();
                String Sub = textField26.getText();

                try {
                    Connection con2=dbConnection.dbConnect();
                    PreparedStatement pst2=con2.prepareStatement("select sub_code,type,date,attempt_status from attendance where student_id='"+UN+"' and sub_code='"+Sub+"'");
                    ResultSet rs2= pst2.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs2));

                    while(rs2.next())
                    {
                        table1.setModel(DbUtils.resultSetToTableModel(rs2));
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
