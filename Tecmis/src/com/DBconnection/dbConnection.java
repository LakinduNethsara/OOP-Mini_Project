package com.DBconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static Connection con;
    //private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/tecmis";
    private static String uname="root";
    private static String password="";

    private static void registerDb(){       //method to register the driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error in register the driver..."+e.getMessage());
        }
    }

    public static Connection dbConnect() {      //method to get connection with database
        //register the driver
        registerDb();

        try {
            con=DriverManager.getConnection(url,uname,password);
        } catch (SQLException e) {
            System.out.println("Error in getting database connection"+e.getMessage());
        }
        return con;
    }
}
