package com.lecturer;

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

public class SeeLectureMaterials extends JFrame {

    private JPanel seelecmat;
    private JComboBox comboBox31;
    private JComboBox comboBox32;
    private JButton viewButton;
    private JButton backButton;


    Connection con;
    Statement st;
    ResultSet rs;
    String myId,subjectCode,path;



    public SeeLectureMaterials() throws HeadlessException {

        add(seelecmat);
        setVisible(true);
        setTitle("Lecturer Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);




        {
            try {

                con = dbConnection.dbConnect();
                st = con.createStatement();
                String queryGetId="SELECT lecturer_id FROM lecturer where user_name= '"+ UserDetails.getUsername()+"'";
                rs= st.executeQuery(queryGetId);
                rs.next();
                myId=rs.getString("lecturer_id");


                String query="select sub_code from lecturer_subject where lecturer_id='"+myId+"'";
                rs= st.executeQuery(query);
                comboBox31.removeAllItems();
                while(rs.next()){
                    comboBox31.addItem(rs.getString("sub_code"));
                }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                 }

        }


        comboBox31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /////////////////////////////////////////
                subjectCode= (String) comboBox31.getSelectedItem();
                try {
                    con = dbConnection.dbConnect();
                    st = con.createStatement();
                    String query="select m_id from course_material where sub_code='"+subjectCode+"'";
                    rs= st.executeQuery(query);
                    comboBox32.removeAllItems();
                    while (rs.next()){
                        comboBox32.addItem(rs.getString("m_id"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }

            }
        });


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {

                try {
                    String mat_id= (String) comboBox32.getSelectedItem();
                    con= dbConnection.dbConnect();
                    Statement ps=con.createStatement();
                    String query1="select m_path from course_material where m_id='"+ mat_id+"'";
                    ResultSet r1 = ps.executeQuery(query1);
                    r1.next();
                    path=r1.getString("m_path");

                    File file= new File(path);
                    if(file.exists()){
                        if(Desktop.isDesktopSupported()){
                            Desktop.getDesktop().open(file);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Not Supported");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"File Not Exist");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
