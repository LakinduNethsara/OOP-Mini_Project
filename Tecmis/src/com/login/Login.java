package com.login;

import com.Admin.AdminDashBoard;
import com.Admin.RegisterAdmin;
import com.DBconnection.dbConnection;
import com.lecturer.LecturerDashboard;
import com.student.StudentDashBoard;
import com.technicalOfficer.TechnicaOfficerDashBoard;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Login extends JFrame {
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel registerLabel;

    private int sign=0;

    Connection con;
    ResultSet rs;
    public Login(){
        add(loginPanel);
        setSize(500,330);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("TECMIS Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username= usernameInput.getText();
                String password = String.valueOf(passwordInput.getPassword());

                try {
                    con= dbConnection.dbConnect();

                    String query1="select user_name,password from admin";
                    String query2="select user_name,password from technical_officer";
                    String query3="select user_name,password from lecturer";
                    String query4="select user_name,password from student";

                    Statement ps=con.createStatement();
                    rs = ps.executeQuery(query1);
                    while(rs.next())
                    {
                        String dbUser_name= rs.getString("user_name");
                        String dbPassword = rs.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)){
                            UserDetails.setUserDetails(username,password,"Admin");
                            sign=1;
                            dispose();

                            //direct admin home page-----------------------------------------
                            AdminDashBoard ad=new AdminDashBoard();

                        }
                    }

                    rs = ps.executeQuery(query2);
                    while(rs.next()){
                        String dbUser_name= rs.getString("user_name");
                        String dbPassword = rs.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)){
                            UserDetails.setUserDetails(username,password,"Technical Officer");
                            sign=1;
                            dispose();

                            //direct Technical officer home page-----------------------------------------
                            TechnicaOfficerDashBoard toDashBoard= new TechnicaOfficerDashBoard();
                        }
                    }

                    rs = ps.executeQuery(query3);
                    while(rs.next()){
                        String dbUser_name= rs.getString("user_name");
                        String dbPassword = rs.getString("password");
                        if(dbUser_name.equals(username)&&dbPassword.equals(password)){
                            UserDetails.setUserDetails(username,password,"Lecturer");
                            sign=1;
                            dispose();

                            //direct Lecturer home page-----------------------------------------
                            LecturerDashboard lec1=new LecturerDashboard();
                        }
                    }

                    rs = ps.executeQuery(query4);
                    while(rs.next()){
                        String dbUser_name= rs.getString("user_name");
                        String dbPassword = rs.getString("password");
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
                    System.out.println(ex.getMessage());
                }
                finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Error in closing the database connection.."+ex.getMessage());
                    }
                }
            }

        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //call register from with creating an object or register form.
                dispose();
                new RegisterAdmin();
            }
        });
    }

    public static void main(String[] args) {
        new Login();
    }
}
