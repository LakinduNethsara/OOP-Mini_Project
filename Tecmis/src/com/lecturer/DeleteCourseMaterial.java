package com.lecturer;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteCourseMaterial extends JFrame {

    private JPanel coursematdel;
    private JComboBox comboBox30;
    private JButton deleteButton;
    private JButton backButton;


    public DeleteCourseMaterial()  {

        add(coursematdel);
        setVisible(true);
        setTitle("Delete Course Material");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);

        String Uname = UserDetails.getUsername();

        try {
            Connection con= dbConnection.dbConnect();
            String query="select lecturer_id from lecturer where user_name="+"'"+Uname+"'";
            Statement ps=con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            rs.next();


            String query1="select m_id from lecturer_upload_material where lecturer_id="+"'"+rs.getString("lecturer_id")+"'";
            Statement ps1=con.createStatement();
            ResultSet rs2 = ps1.executeQuery(query1);

            while(rs2.next())
            {
                comboBox30.addItem(rs2.getString("m_id"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String couid = (String)comboBox30.getSelectedItem();

                String q1 = "delete from lecturer_upload_material where m_id='"+couid+"'";

                Connection con= null;
                try {
                    con = dbConnection.dbConnect();
                    Statement ps1=con.createStatement();
                    ps1.executeUpdate(q1);
                    JOptionPane.showMessageDialog(null, "Successfully Deleted");
                    dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}
