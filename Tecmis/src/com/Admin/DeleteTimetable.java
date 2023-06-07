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

public class DeleteTimetable extends JFrame{
    private JPanel DeleteTimetableLable;
    private JButton backButton;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public DeleteTimetable() {

    add(DeleteTimetableLable);
    setVisible(true);
    setTitle("Delete Timetable");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    setSize(screenSize.width, screenSize.height);
    setLocationRelativeTo(null);

        Connection con = null;
        try {
            con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select dep_id from department");
            comboBox1.removeAllItems();

            while (rs.next()) {
                comboBox1.addItem(rs.getString("dep_id"));
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
                String depid = (String)comboBox1.getSelectedItem();
                String level = (String)comboBox2.getSelectedItem();


                String query ="delete from time_table where dep_id='"+depid+"' and level="+level;

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
