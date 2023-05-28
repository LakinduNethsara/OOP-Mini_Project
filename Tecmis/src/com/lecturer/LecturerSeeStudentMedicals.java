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

public class LecturerSeeStudentMedicals extends JFrame {

    private JPanel lecstumedical;
    private JTextField textField28;
    private JButton searchButton;
    private JButton backButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField29;

    public LecturerSeeStudentMedicals()  {

        add(lecstumedical);
        setVisible(true);
        setTitle("Student Medicals");
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
                String id = (String) comboBox1.getSelectedItem();
                String sub = (String) comboBox2.getSelectedItem();

                try {
                    Connection con2= dbConnection.dbConnect();
                    PreparedStatement pst2=con2.prepareStatement("select medical_id,sub_code,approve_status from medical where student_id='"+id+"' and sub_code='"+sub+"'");
                    ResultSet rs2= pst2.executeQuery();

                    table1.setModel(DbUtils.resultSetToTableModel(rs2));

                    while(rs2.next())
                    {
                        table1.setModel(DbUtils.resultSetToTableModel(rs2));
                    }

                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                }

            }

        });
    }
}
