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

public class DeleteStudent extends JFrame{
    private JPanel DeleteStudentPanel;
    private JButton StudetDeletebackButton;
    private JButton StudentdeleteButton;
    private JComboBox comboBox1;

    private String student_id;
public DeleteStudent() {
    add(DeleteStudentPanel);
    setVisible(true);
    setTitle("Delete Student");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk=Toolkit.getDefaultToolkit();
    Dimension screenSize=tk.getScreenSize();
    setSize(screenSize.width,screenSize.height);
    setLocationRelativeTo(null);

    Connection con = null;
    try {
        con = dbConnection.dbConnect();
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery("select student_id from student");
        comboBox1.removeAllItems();

        while (rs.next()) {
            comboBox1.addItem(rs.getString("student_id"));
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    StudetDeletebackButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        dispose();
        }
    });


    StudentdeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            student_id=(String)comboBox1.getSelectedItem();

            String query ="delete from student where student_id="+"'"+student_id+"'";

            Connection con1;

            try {
                con1 = dbConnection.dbConnect();
                Statement ps = con1.createStatement();
                ps.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully Deleted");
                dispose();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });



}
}
