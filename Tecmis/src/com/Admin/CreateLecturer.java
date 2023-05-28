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

public class CreateLecturer extends JFrame {
    private JPanel CreateLecturerPanel;
    private JTextField textField1;
    private JButton backButton;
    private JButton addButton;


    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JComboBox comboBox1;
    private JComboBox comboBox2;


    String id, fName, lName, userName, password, email, address, dob, phoneNumber, gender, position, department;


    public CreateLecturer() {
        add(CreateLecturerPanel);
        setVisible(true);
        setTitle("Create lecturer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);


        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select dep_id from department");
            comboBox1.removeAllItems();
            while (rs.next()) {
                comboBox1.addItem(rs.getString("dep_id"));
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
                createLecProfile();

            }
        });
    }

    public void createLecProfile() {
        id = textField1.getText();
        fName = textField2.getText();
        lName = textField3.getText();
        userName = textField4.getText();
        password = textField5.getText();
        email = textField7.getText();
        address = textField10.getText();
        dob = textField8.getText();
        gender = (String) comboBox2.getSelectedItem();
        department = (String) comboBox1.getSelectedItem();
        phoneNumber = textField9.getText();
        position = textField11.getText();

        try {
            Connection con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            String query="select lecturer_id,user_name from lecturer";
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String user_name = rs.getString("user_name");
                String lecturer_id = rs.getString("lecturer_id");
                if ((id.equals(lecturer_id) || (userName.equals(user_name)))) {
                    JOptionPane.showMessageDialog(null, "Data is exists");
                    dispose();
                    new CreateLecturer();
                }
            }
            String insertQuery="insert into lecturer values("+"'"+id+"','"+fName+"','"+lName+"','"+userName+"','"+password+"','"+email+"','"+address+"','"+dob+"','"+phoneNumber+"','"+gender+"','"+position+"','"+department+"')";
            ps.executeUpdate(insertQuery);
            JOptionPane.showMessageDialog(null, "Added!!!");
            dispose();
            new AdminUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

