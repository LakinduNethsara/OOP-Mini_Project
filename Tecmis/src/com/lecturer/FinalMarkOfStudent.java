package com.lecturer;

import com.DBconnection.dbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FinalMarkOfStudent extends JFrame {


    private JPanel FinalMarkOfStudentPanel;
    private JTextField textField1;
    private JButton searchButton;
    private JButton backButton;
    private JLabel textFeild23;
    private JTable studentMarksTable;
    private JComboBox comboBox1;
    private JTextField TextFeild24;
    private JComboBox comboBox12;


    Connection con;
    ResultSet rs,rs1;
    String stid;


    public FinalMarkOfStudent(){

        add(FinalMarkOfStudentPanel);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);

        try {
        ResultSet rs = LecturerDB.allStudentId();
            while (rs.next())
            {
                comboBox1.addItem(rs.getString("student_id"));
            }


            String query="select sub_code,grade from marks where student_id="+"'"+stid+"'";


            con = dbConnection.dbConnect();
            Statement ps=con.createStatement();
            rs = ps.executeQuery(query);

            studentMarksTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stid = (String) comboBox1.getSelectedItem();

                String query="select sub_code,grade from marks where student_id="+"'"+stid+"'";


                try {
                    con = dbConnection.dbConnect();
                    Statement ps=con.createStatement();
                    rs = ps.executeQuery(query);

                    studentMarksTable.setModel(DbUtils.resultSetToTableModel(rs));

                    while(rs.next())
                    {
                        studentMarksTable.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
