package com.lecturer;

import com.DBconnection.dbConnection;
import com.user.UserDetails;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecturerSeeEligibilityOfBatch extends JFrame {


    private JPanel lecturerEligibilityWholeBatch;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton viewButton;
    private JButton backButton;

    public LecturerSeeEligibilityOfBatch(){
        add(lecturerEligibilityWholeBatch);
        setVisible(true);
        setTitle("Student Eligibility");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height

        String uname = UserDetails.getUsername();
        System.out.println(uname);

        try {
            Connection con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            ResultSet rs = ps.executeQuery("select lecturer_id from lecturer where user_name='"+uname+"'");
            rs.next();

            Statement ps1=con.createStatement();
            ResultSet rs3= ps1.executeQuery("select sub_code from lecturer_subject where lecturer_id='"+rs.getString("lecturer_id")+"'");

            rs.next();

            while(rs3.next())
            {
                comboBox1.addItem(rs3.getString("sub_code"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    void courseLoad(){

        try {
            Connection con= dbConnection.dbConnect();
            PreparedStatement pst2=con.prepareStatement("select student_id,first_name,last_name,email,address,phone_number,level,SGPA,CGPA from student ");
            ResultSet rs2= pst2.executeQuery();
            while(rs2.next())
            {
                table1.setModel(DbUtils.resultSetToTableModel(rs2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
