package com.student;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentViewTimeTable {
    String myLevel,myDepartment;
    Connection con;
    String path;


    public StudentViewTimeTable(){
        try {
            con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            String query1="select level,dep_id from student where user_name='"+ UserDetails.getUsername()+"'";
            ResultSet r1 = ps.executeQuery(query1);
            r1.next();
            myLevel=r1.getString("level");
            myDepartment=r1.getString("dep_id");

            String query2="select t_table_path from time_table where dep_id='"+ myDepartment+"' and level='"+myLevel+"'";
            ResultSet r2 = ps.executeQuery(query2);
            r2.next();
            path=r2.getString("t_table_path");
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
            JOptionPane.showMessageDialog(null,"No timetable has been uploaded");
            System.out.println("No timetable has been uploaded");
            throw new RuntimeException(e);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}
