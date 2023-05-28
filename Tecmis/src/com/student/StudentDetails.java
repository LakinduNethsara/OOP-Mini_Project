package com.student;
import com.DBconnection.dbConnection;
import com.user.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDetails {
    private String studentID=null;
    private String firstName=null;
    private String lastName=null;
    private String userName=null;
    private String email=null;
    private String address= null;
    private String dob =null;
    private String phoneNumber=null;
    private String gender=null;
    private String level=null;
    private String sgpa=null;
    private String cgpa=null;
    private String profilePicPath =null;
    private String profilePicName=null;
    private String depId=null;

    private byte[] proPic=null;

    private Connection con=null;
    private PreparedStatement ps=null;
    private ResultSet rs= null;

    public StudentDetails(){
        userName= UserDetails.getUsername();
        con= dbConnection.dbConnect();
        try {
            ps=con.prepareStatement("select * from student where user_name=?");
            ps.setString(1,userName);
            rs=ps.executeQuery();
            rs.next();
            
            studentID=rs.getString(1);
            firstName=rs.getString(2);
            lastName=rs.getString(3);
            userName=rs.getString(4);
            email=rs.getString(6);
            address=rs.getString(7);
            dob=rs.getString(8);
            phoneNumber=rs.getString(9);
            gender=rs.getString(10);
            level=rs.getString(11);
            sgpa=rs.getString(12);
            cgpa=rs.getString(13);
            profilePicName=rs.getString(14);
            profilePicPath=rs.getString(15);
            proPic=rs.getBytes(16);
            depId=rs.getString(17);

            
        } catch (SQLException e) {
            System.out.println("Error in prepared statement..."+e.getMessage());
        }

    }

    public String getId(){
        return studentID;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getDob(){
        return dob;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getGender(){
        return gender;
    }
    public String getLevel(){
        return level;
    }
    public String getSgpa(){
        return sgpa;
    }
    public String getCgpa(){
        return cgpa;
    }
    public String getProfilePicPath(){
        return profilePicName;
    }
    public String getProfilePicName(){
        return profilePicName;
    }
    public String getPrfilePicPath(){
        return profilePicPath;
    }

    public byte[] getProPic(){
        return proPic;
    }
    public String getDepId(){
        return depId;
    }

}
