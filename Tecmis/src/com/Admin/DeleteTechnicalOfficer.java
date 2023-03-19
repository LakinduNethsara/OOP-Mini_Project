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

public class DeleteTechnicalOfficer extends JFrame {
    private JPanel DeleteTechnicalOfficerPanel;
    private JButton backButton;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private String officerId;

public DeleteTechnicalOfficer() {
    add(DeleteTechnicalOfficerPanel);
    setVisible(true);
    setTitle("Delete Technical officer");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    setSize(screenSize.width, screenSize.height);
    setLocationRelativeTo(null);

    Connection con = null;
    try {
        con = dbConnection.dbConnect();
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery("select officer_id from technical_officer");
        comboBox1.removeAllItems();

        while (rs.next()) {
            comboBox1.addItem(rs.getString("officer_id"));
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
            officerId=(String)comboBox1.getSelectedItem();

            String query ="delete from technical_officer where officer_id="+"'"+officerId+"'";

            Connection con1;

            try {
                con1 = dbConnection.dbConnect();
                Statement ps = con1.createStatement();
                ps.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully Deleted");
                dispose();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }



        }
    });





}
}
