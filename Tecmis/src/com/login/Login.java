package com.login;
import com.DBconnection.dbConnection;
import com.student.StudentDashBoard;
import com.technicalOfficer.TechnicaOfficerDashBoard;
import com.user.UserDetails;
import com.lecturer.LecturerDashboard;
import com.Admin.AdminDashBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Login extends JFrame {
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernaleInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JButton cancelButton;

    private int sign=0;

    ResultSet r1,r2,r3,r4;
    public Login(){
        add(loginPanel);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("TECMIS Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username=usernaleInput.getText();
                String password = String.valueOf(passwordInput.getPassword());

                try {
                    Connection con= dbConnection.dbConnect();

                    String query1="select user_name,password from admin";
                    String query2="select user_name,password from technical_officer";
                    String query3="select user_name,password from lecturer";
                    String query4="select user_name,password from student";

                    Statement ps=con.createStatement();
                    r1 = ps.executeQuery(query1);
                    while(r1.next())
                    {
                        String dbUser_name=r1.getString("user_name");
                        String dbPassword =r1.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)){
                            UserDetails.setUserDetails(username,password,"Admin");
                            sign=1;
                            dispose();

                            //direct admin home page-----------------------------------------
                            AdminDashBoard ad=new AdminDashBoard();

                        }
                    }

                    r2 = ps.executeQuery(query2);
                    while(r2.next()){
                        String dbUser_name=r2.getString("user_name");
                        String dbPassword =r2.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)){
                            UserDetails.setUserDetails(username,password,"Technical Officer");
                            sign=1;
                            dispose();

                            //direct Technical officer home page-----------------------------------------
                            TechnicaOfficerDashBoard toDashBoard= new TechnicaOfficerDashBoard();
                        }
                    }

                    r3 = ps.executeQuery(query3);
                    while(r3.next()){
                        String dbUser_name=r3.getString("user_name");
                        String dbPassword =r3.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)){
                            UserDetails.setUserDetails(username,password,"Lecturer");
                            sign=1;
                            dispose();

                            //direct Lecturer home page-----------------------------------------
                            LecturerDashboard lec1=new LecturerDashboard();
                        }
                    }

                    r4 = ps.executeQuery(query4);
                    while(r4.next()){
                        String dbUser_name=r4.getString("user_name");
                        String dbPassword =r4.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)) {
                            UserDetails.setUserDetails(username,password,"Student");
                            sign=1;
                            dispose();

                            //direct Student home page-----------------------------------------
                            StudentDashBoard sDashboard = new StudentDashBoard();
                            sDashboard.loadUserDetails();
                        }
                    }

                    if(sign==0){
                        JOptionPane.showMessageDialog(null, "Invalid login details or not registered");
                        dispose();
                        new Login();
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new Login();
    }
}
