package com.student;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentCourseMaterial extends JFrame{
    private JPanel main;
    private JLabel titleLable;
    private JLabel noticeIconLabel;
    private JComboBox subjectComboBox;
    private JButton viewButton;
    private JComboBox idComboBox;
    private JButton backButton;


    String myId;
    String subCode=null;
    Connection con;
    ResultSet rs;
    Statement st;
    String materialId;
    String path;

    public StudentCourseMaterial(){
        add(main);
        setTitle("Course material");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk=Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);

        {
            try {
                con = dbConnection.dbConnect();
                st = con.createStatement();

                //get student id from the database
                myId=new StudentDetails().getId();

                //get subject codes for relevant student and insert to the combo box
                String query="SELECT sub_code FROM student_subject where student_id= '"+myId+"'";
                rs= st.executeQuery(query);
                subjectComboBox.removeAllItems();
                while(rs.next()){
                    subjectComboBox.addItem(rs.getString("sub_code"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        subjectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subCode= (String) subjectComboBox.getSelectedItem();

                try {
                    con = dbConnection.dbConnect();
                    st = con.createStatement();

                    //retrieve material id from the database and insert into the combo box
                    String query="SELECT m_id FROM course_material where sub_code= '"+subCode+"'";
                    rs= st.executeQuery(query);
                    idComboBox.removeAllItems();
                    while(rs.next()){
                        idComboBox.addItem(rs.getString("m_id"));
                        System.out.println(subCode);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        });

        //Back button functionality
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentCourseList();
            }
        });

        //View button functionality
        viewButton.addActionListener(new ActionListener() {     //View button
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMaterial();
            }
        });


    }


    //method to view selected material
    public void viewMaterial(){
        try {
            materialId= (String) idComboBox.getSelectedItem();
            con= dbConnection.dbConnect();
            Statement ps=con.createStatement();

            //get the path of the material
            String query1="select m_path from course_material where m_id='"+ materialId+"'";
            ResultSet r1 = ps.executeQuery(query1);
            r1.next();
            path=r1.getString("m_path");

            //create file object with the path and open the material
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
            System.out.println("Error in load material..."+e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error in closing db connection..."+e.getMessage());
            }
        }
    }
}
