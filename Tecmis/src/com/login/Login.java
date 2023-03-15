package com.login;
import com.DBconnection.dbConnection;
import com.user.UserDetails;

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

                    UserDetails ud= new UserDetails();
                    while(r1.next())
                    {
                        String dbUser_name=r1.getString("user_name");
                        String dbPassword =r1.getString("password");
                       if(dbUser_name.equals(username)){
                           ud.setUserDetails(username,password,"Admin");

                            System.exit(0);

                            //direct admin home page-----------------------------------------

                        }
                    }

                    r2 = ps.executeQuery(query2);
                   while(r2.next()){
                        String dbUser_name=r2.getString("user_name");
                        String dbPassword =r2.getString("password");
                        if(dbUser_name.equals(username)){
                            ud.setUserDetails(username,password,"Technical Officer");

                            System.exit(0);

                            //direct Technical officer home page-----------------------------------------

                        }
                    }

                    r3 = ps.executeQuery(query3);
                    while(r3.next()){
                        String dbUser_name=r3.getString("user_name");
                        String dbPassword =r3.getString("password");
                        if(dbUser_name.equals(username)){
                            ud.setUserDetails(username,password,"Lecturer");
                            System.exit(0);
                            //direct Lecturer home page-----------------------------------------

                        }
                    }

                    r4 = ps.executeQuery(query4);
                    while(r4.next()){
                        String dbUser_name=r4.getString("user_name");
                        String dbPassword =r4.getString("password");
                        if(dbUser_name.equals(username)) {
                            ud.setUserDetails(username,password,"Student");
                            System.exit(0);
                            //direct Student home page-----------------------------------------
                        }
                    }

                    JOptionPane.showMessageDialog(null,"Invalid login details or not registered");
                    dispose();
                    new Login();

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
