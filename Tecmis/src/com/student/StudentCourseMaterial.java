package com.student;

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
                String queryGetId="SELECT student_id FROM student where User_name= '"+ UserDetails.getUsername()+"'";
                rs= st.executeQuery(queryGetId);
                rs.next();
                myId=rs.getString("student_id");
                String query="SELECT sub_code FROM student_subject where student_id= '"+myId+"'";

                rs= st.executeQuery(query);
                subjectComboBox.removeAllItems();
                while(rs.next()){
                    subjectComboBox.addItem(rs.getString("sub_code"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        subjectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subCode= (String) subjectComboBox.getSelectedItem();

                try {
                    con = dbConnection.dbConnect();
                    st = con.createStatement();

                    String query="SELECT m_id FROM course_material where sub_code= '"+subCode+"'";

                    rs= st.executeQuery(query);
                    idComboBox.removeAllItems();
                    while(rs.next()){
                        idComboBox.addItem(rs.getString("m_id"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentCourseList();
            }
        });


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMaterial();
            }
        });
    }

    public void viewMaterial(){
        try {
            materialId= (String) idComboBox.getSelectedItem();
            con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            String query1="select m_path from course_material where m_id='"+ materialId+"'";
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
}
