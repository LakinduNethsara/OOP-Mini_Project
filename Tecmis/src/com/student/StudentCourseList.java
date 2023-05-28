package com.student;

import com.DBconnection.dbConnection;
import com.user.UserDetails;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class StudentCourseList extends JFrame{
    private JPanel coursePanal;
    private JLabel titleLabel;
    private JTable table1;
    private JScrollPane resultTable;
    private JButton backButton;
    private JButton courseMaterial;

    String myId;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;




    public StudentCourseList(){
        add(coursePanal);
        setTitle("Courses and grades");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk=Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);
        courseLoad();


        //Back button functionality
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Course material button functionality
        courseMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentCourseMaterial();
            }
        });
    }

    //method to load course list
    void courseLoad(){
        con=dbConnection.dbConnect();

        //get student id
        myId=new StudentDetails().getId();

        try {
            //read course details from the database
            pst=con.prepareStatement("select subject.sub_code,subject.type,subject.sub_name,subject.sub_name,marks.grade from ( (subject inner join student_subject on subject.sub_code=student_subject.sub_code) left join marks on student_subject.sub_code=marks.sub_code) where student_subject.student_id='"+myId+"'");
            rs= pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));     //insert course details to the table
        } catch (SQLException e) {
            System.out.println("Error in load course details..."+e.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error in close db connection..."+e.getMessage());
            }
        }

    }
}
