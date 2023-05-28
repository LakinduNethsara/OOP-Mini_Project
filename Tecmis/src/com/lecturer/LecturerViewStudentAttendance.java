package com.lecturer;

import com.DBconnection.dbConnection;
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
    private JComboBox comboBox1;
    private JComboBox comboBox2;

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


        ResultSet R1 = LecturerDB.allStudentId();

        comboBox1.removeAllItems();
        comboBox2.removeAllItems();

        try {
            while(R1.next())
            {
                comboBox1.addItem(R1.getString("student_id"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String stid= (String) comboBox1.getSelectedItem();

                ResultSet R2 = LecturerDB.subjectsForSid(stid);


                try {
                    while(R2.next())
                    {
                        comboBox2.addItem(R2.getString("sub_code"));
                    }
                }
                catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {

                String  UN = (String) comboBox1.getSelectedItem();
                String Sub = (String) comboBox2.getSelectedItem();

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
