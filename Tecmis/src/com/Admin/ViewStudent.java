package com.Admin;

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

public class ViewStudent extends JFrame {
    private JTable table1;

    private JPanel viewStudent;
    private JButton backButton;

    ViewStudent() {
        add(viewStudent);
        setVisible(true);
        setTitle("View Students");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    Connection con;
    Statement st;
    ResultSet rs;

    public void viewStudents() {
        try {
            con = dbConnection.dbConnect();
            st = con.createStatement();
            rs = st.executeQuery("select student_id,first_name,last_name,user_name,email,email,address,DOB,phone_number,gender,level,SGPA,CGPA,dep_id from student");
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println("Error in connection the database" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the database");
            }
        }
    }
}
