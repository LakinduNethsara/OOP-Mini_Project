package com.student;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentViewTimeTable extends StudentDetails{
    Connection con;
    String path;


    public StudentViewTimeTable(){
        try {
            con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            //get the relevant path for the timetable
            String query2="select t_table_path from time_table where dep_id='"+ getDepId()+"' and level='"+getLevel()+"'";    //depId and level are inherited from StudentDetails
            ResultSet r2 = ps.executeQuery(query2);
            r2.next();
            path=r2.getString("t_table_path");

            //open the timetable through the path
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
            System.out.println("No time table uploded");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error with closing the connection..."+e.getMessage());
            }
        }

    }
}
