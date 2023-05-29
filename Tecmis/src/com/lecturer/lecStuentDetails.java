package com.lecturer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class lecStuentDetails extends JFrame {

    private JPanel lecStuDetilsPanel;
    private JTextField textField15;
    private JTextField textField2;
    private JButton searchButton;
    private JButton backButton;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JComboBox comboBox1;

    private String Stid;

    ResultSet RS;

    public lecStuentDetails() {
        add(lecStuDetilsPanel);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height

        try {
            ResultSet rs = LecturerDB.allStudentId();
            while (rs.next()) {
                comboBox1.addItem(rs.getString("student_id"));
            }
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
                Stid = (String) comboBox1.getSelectedItem();

                String query = "select * from student where student_id="+"'"+Stid+"'";
                Connection con;
                try {
                    con = dbConnection.dbConnect();
                    Statement ps=con.createStatement();
                    RS = ps.executeQuery(query);
                    RS.next();

                    textField15.setText(RS.getString("student_id"));
                    textField16.setText(RS.getString("first_name"));
                    textField17.setText(RS.getString("last_name"));
                    textField18.setText(RS.getString("email"));
                    textField19.setText(RS.getString("address"));
                    textField20.setText(RS.getString("phone_number"));
                    textField21.setText(RS.getString("level"));
                    textField22.setText(RS.getString("SGPA"));
                    textField23.setText(RS.getString("CGPA"));


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }


}
