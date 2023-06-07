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

public class UpdateCourse extends JFrame{
    private JButton backButton;
    private JButton updateButton;
    private JPanel UpdateCoursePanel;
    private JComboBox comboBox1;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JLabel j;

    String Subcode;
    String Subname;
    String Subtype;
    double credit;

    public UpdateCourse() {
    add( UpdateCoursePanel);
    setTitle("Update Courses");
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    setSize(screenSize.width, screenSize.height);
    setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select sub_code from subject");
            comboBox1.removeAllItems();
            while (rs.next()) {
                comboBox1.addItem(rs.getString("sub_code"));
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

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subcode = (String)comboBox1.getSelectedItem();
                Subname = txt1.getText();
                Subtype = txt2.getText();
                credit = Double.parseDouble(txt3.getText());

                try {
                    Connection con = dbConnection.dbConnect();
                    Statement ps = con.createStatement();
                    String query = "update subject set sub_code='"+Subcode+"' ,sub_name='"+Subname+"',type='"+Subtype+"',credit="+credit+"where sub_code='"+Subcode+"'";
                    ps.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Successfully updated!!!");
                    dispose();

                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });



    }
}
