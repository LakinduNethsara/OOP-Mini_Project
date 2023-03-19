package com.technicalOfficer;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TechnicalOfficerTimeTable extends JFrame {

    private JPanel TOTPanel;
    private JPanel TOTpanel1;
    private JPanel TOTpanel;
    private JLabel TOTlabel1;
    private JLabel TOTlabel2;
    private JLabel TTlabel1;
    private JComboBox comboBox1;
    private JButton viewButton;
    private JButton backButton;

    String level;
    String dep;
    String path;
    Connection con;

    public TechnicalOfficerTimeTable(){
        add(TOTPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Time Table View");

        Toolkit tk = Toolkit.getDefaultToolkit();   //Initialize toolkit class
        Dimension screenSize = tk.getScreenSize();    //Get the screen resolution
        setSize(screenSize.width, screenSize.height);    //Set window width and height
        setLocationRelativeTo(null);

        Connection con = null;

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        try {
            con = dbConnection.dbConnect();
            Statement ps=con.createStatement();
            String query1="select dep_id from technical_officer where user_name='"+ UserDetails.getUsername()+"'";
            ResultSet r1=ps.executeQuery(query1);
            r1.next();

            dep=r1.getString("dep_id");

            String query2="select level from time_table where dep_id ='"+dep+"'";
            ResultSet r2=ps.executeQuery(query2);
            comboBox1.removeAllItems();
            while (r2.next()){
                comboBox1.addItem(r2.getString("level"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTimeTable();
            }

        });
    }


    public void viewTimeTable(){
        try {
            level= (String) comboBox1.getSelectedItem();
            con = dbConnection.dbConnect();
            Statement ps=con.createStatement();
            String query3="select t_table_path from time_table where dep_id='"+ dep+"' and level='"+level+"'";
            ResultSet r3 = ps.executeQuery(query3);
            r3.next();
            path=r3.getString("t_table_path");
            File file= new File(path);

            if(file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(null, "File Not Supported");
                }
            }
            else {
                 JOptionPane.showMessageDialog(null,"File Not Exist");
                }




    } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
