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

public class FinalMarksOfWholeBatch extends JFrame {


    private JPanel finalMarksofbatch;
    private JComboBox comboBox16;
    private JButton searchButton;
    private JButton backButton;
    private JTable table1;
    private JScrollPane finalMarksWholeBatch;

    Connection con;
    ResultSet rs,rs1;

    public FinalMarksOfWholeBatch()  {

        add(finalMarksofbatch);
        setVisible(true);
        setTitle("Student Marks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);


        String query ="select sub_code from subject ";

        try {
            con = dbConnection.dbConnect();
            Statement ps=con.createStatement();
            rs = ps.executeQuery(query);
            comboBox16.removeAllItems();


            while(rs.next())
            {
                comboBox16.addItem(rs.getString("sub_code"));
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

                String sub_code1 = (String)comboBox16.getSelectedItem();

                String query1 ="select student_id,sub_code,grade from marks where sub_code='"+sub_code1+"'";

                try {
                    con = dbConnection.dbConnect();
                    Statement ps=con.createStatement();
                    rs1 = ps.executeQuery(query1);

                    table1.setModel(DbUtils.resultSetToTableModel(rs1));

                    while(rs1.next())
                    {
                        table1.setModel(DbUtils.resultSetToTableModel(rs1));
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
