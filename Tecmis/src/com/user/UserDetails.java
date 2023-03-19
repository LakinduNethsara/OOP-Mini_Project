package com.user;

import com.DBconnection.dbConnection;
import com.login.Login;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDetails {
    private static String myUserame=null;
    private static String myPassword=null;
    private static String myType=null;

    public static void setUserDetails(String username,String password,String type){
        myUserame=username;
        myPassword=password;
        myType=type;
    }

    public static String getUsername() {
        return myUserame;
    }
    public static String getPassword() {
        return myPassword;
    }
    public static String getType() {
        return myType;
    }


}
