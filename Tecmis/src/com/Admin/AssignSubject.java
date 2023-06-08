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

public class AssignSubject extends JFrame{
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;
    private JComboBox comboBox3;
    private JPanel Assignsubjects;
    private JButton backButton;

    Connection con1,con2;
    Statement st1,st2;
    ResultSet rs1,rs2;
    String query,query1;
    String type,userId,depId,subcode;

    {

    }


    public AssignSubject() {
        add(Assignsubjects);
        setVisible(true);
        setTitle("Admin");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setLocationRelativeTo(null);




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con1= dbConnection.dbConnect();
                    st1=con1.createStatement();
                    type= (String) comboBox1.getSelectedItem();

                    if(type.equals("Lecturer")){
                        query="select lecturer_id from lecturer";
                        rs1=st1.executeQuery(query);
                        comboBox2.removeAllItems();
                        while (rs1.next()){
                            comboBox2.addItem(rs1.getString("lecturer_id"));
                        }


                    }



                    else if (type.equals("Student")){
                        query="select student_id from student";
                        rs1=st1.executeQuery(query);
                        comboBox2.removeAllItems();
                        while (rs1.next()){
                            comboBox2.addItem(rs1.getString("student_id"));
                        }
                    }


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userId = (String) comboBox2.getSelectedItem();

                try {
                    con2 = dbConnection.dbConnect();
                    st2 = con2.createStatement();
                    if (type.equals("Lecturer")) {
                        query = "select dep_id from lecturer where lecturer_id='" + userId + "'";
                        rs2 = st2.executeQuery(query);
                        rs2.next();
                        depId = rs2.getString("dep_id");

                        query = "select sub_code from subject where dep_id='" + depId + "' or dep_id='DEPMUL'";
                        rs2 = st2.executeQuery(query);
                        comboBox3.removeAllItems();
                        while (rs2.next()) {
                            comboBox3.addItem(rs2.getString("sub_code"));
                        }


                    } else if (type.equals("Student")) {
                        query = "select dep_id from student where student_id='" + userId + "'";
                        rs2 = st2.executeQuery(query);
                        rs2.next();
                        depId = rs2.getString("dep_id");

                        query = "select sub_code from subject where dep_id='" + depId + "' or dep_id='DEPMUL'";
                        rs2 = st2.executeQuery(query);
                        comboBox3.removeAllItems();
                        while (rs2.next()) {
                            comboBox3.addItem(rs2.getString("sub_code"));
                        }
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcode = (String) comboBox3.getSelectedItem();
                Statement st1p;

                if(type.equals("Lecturer"))
                {
                    query1 = "insert into lecturer_subject values('"+userId+"','"+subcode+"')";
                    System.out.println("lec");
                }

                else
                {
                    query1 = "insert into student_subject values('"+userId+"','"+subcode+"')";
                }


                try {
                    con1= dbConnection.dbConnect();
                    st1=con1.createStatement();
                    st1.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Successfully Assigned");
                    dispose();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                    throw new RuntimeException(ex);

                }

            }
        });
    }}
