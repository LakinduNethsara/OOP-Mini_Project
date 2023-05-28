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


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                String id = textField28.getText();
                String sub = textField29.getText();

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
