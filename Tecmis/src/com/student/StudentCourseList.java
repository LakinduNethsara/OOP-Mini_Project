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


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        courseMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentCourseMaterial();
            }
        });
    }
    void courseLoad(){
        try {
            Connection con1=dbConnection.dbConnect();
            PreparedStatement pst1 = con1.prepareStatement("select student_id from student where user_name= '"+ UserDetails.getUsername()+"'");


            ResultSet rs1= pst1.executeQuery();
            rs1.next();
            myId=rs1.getString("student_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection con=dbConnection.dbConnect();
            PreparedStatement pst2=con.prepareStatement("select subject.sub_code,subject.type,subject.sub_name,subject.sub_name,marks.grade from ( (subject inner join student_subject on subject.sub_code=student_subject.sub_code) left join marks on student_subject.sub_code=marks.sub_code) where student_subject.student_id='"+myId+"'");
            ResultSet rs2= pst2.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs2));
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }



    }
}
