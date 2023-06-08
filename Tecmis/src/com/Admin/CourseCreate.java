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

public class CourseCreate extends JFrame{
    private JPanel CourseCreatePanel;
    private JTextField textField1;
    private JButton backButton;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    String subCode,subName,type,credit,depId;
    public CourseCreate() {
    add( CourseCreatePanel);
    setTitle("Create Courses");
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    setSize(screenSize.width, screenSize.height);
    setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select dep_id from department");
            comboBox2.removeAllItems();
            while (rs.next()) {
                comboBox2.addItem(rs.getString("dep_id"));
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCourse();
            }
        });
    }

    public void CreateCourse(){
        subCode=textField1.getText();
        subName=textField2.getText();
        type=(String)comboBox1.getSelectedItem();
        credit=textField4.getText();
        depId=(String)comboBox2.getSelectedItem();

        /*try {

            String query="select sub_code,type from subject";
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String readSubCode = rs.getString("sub_code");
                String readType = rs.getString("type");
                if ((subCode.equals(readSubCode) || (type.equals(readType)))) {
                    JOptionPane.showMessageDialog(null, "Course is exists");
                    dispose();
                    new CourseCreate();
                }
            }*/

        try {
        Connection con= dbConnection.dbConnect();
        Statement ps= null;

        ps = con.createStatement();
        String insertQuery="insert into subject values("+"'"+subCode+"','"+subName+"','"+type+"',"+credit+",'"+depId+"')";
        ps.executeUpdate(insertQuery);
        JOptionPane.showMessageDialog(null, "Added!!!");
        dispose();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        }
}





