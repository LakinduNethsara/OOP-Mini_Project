package com.Admin;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateLecturer extends JFrame{
    private JPanel UpdateLecturerpanel;
    private JButton backButton;
    private JButton updateButton;
    private JComboBox comboBox1;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;
    private JTextField txt6;
    private JTextField txt7;
    private JTextField txt9;
    private JTextField txt10;
    private JComboBox comboBox2;
    Connection con=null;
    Statement ps=null;
    ResultSet rs=null;

    String LecId,fName,lName,Uname,password,eMail,address,dob,lable,phoneNumber,position,gender;
    public UpdateLecturer() {
    add(UpdateLecturerpanel);
    setVisible(true);
    setTitle("Update Lecturer");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk=Toolkit.getDefaultToolkit();
    Dimension screenSize=tk.getScreenSize();
    setSize(screenSize.width,screenSize.height);
    setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select lecturer_id from lecturer");
            comboBox1.removeAllItems();
            while (rs.next()) {
                comboBox1.addItem(rs.getString("lecturer_id"));
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecId=(String)comboBox1.getSelectedItem();
                fName=txt1.getText();
                lName=txt2.getText();
                Uname=txt3.getText();
                password=txt4.getText();
                eMail=txt5.getText();
                address=txt6.getText();
                dob= txt7.getText();
                phoneNumber= txt9.getText();;
                position=txt10.getText();
                gender=(String) comboBox2.getSelectedItem();
                try {
                    con= dbConnection.dbConnect();
                    ps = con.createStatement();
                    String query = "update lecturer set lecturer_id= '"+LecId+"' ,first_name='"+fName+"',last_name='"+lName+"',User_name='"+Uname+"',password='"+password+"',email='"+eMail+"',address='"+address+"',DOB='"+dob+"',phone_number='"+phoneNumber+"',gender='"+gender+"',position='"+position+"' where lecturer_id = '"+LecId+"'";
                    ps.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Successfully updated!!!");
                    dispose();

                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LecId=(String) comboBox1.getSelectedItem();
                    con = dbConnection.dbConnect();
                    ps = con.createStatement();
                    String query = "select * from lecturer where lecturer_id = '"+LecId+"'";
                    rs = ps.executeQuery(query);
                    rs.next();

                    txt1.setText(rs.getString(2));
                    txt2.setText(rs.getString(3));
                    txt3.setText(rs.getString(4));
                    txt4.setText(rs.getString(5));
                    txt5.setText(rs.getString(6));
                    txt6.setText(rs.getString(7));
                    txt7.setText(rs.getString(8));
                    txt9.setText(rs.getString(9));
                    txt10.setText(rs.getString(11));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }





            }
        });
    }
}
