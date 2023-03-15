package com.DBconnection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    static java.sql.Connection con;
    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://localhost/tecmis";
    static String uname="root";
    static String password="";

    public static java.sql.Connection dbConnect() throws SQLException {
        if(con==null){
            try {
                Class.forName(driver);
                con=DriverManager.getConnection(url,uname,password);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return con;
    }
}
