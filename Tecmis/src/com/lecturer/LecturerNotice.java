package com.lecturer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturerNotice extends JFrame {
    private JComboBox noticrcombobox;
    private JButton viewNoticeButton;
    private JTextArea noticeTextArea;
    private JButton backButton;
    private JPanel lecNotice;
    private JLabel DesDate;

    Connection con;

    public LecturerNotice() {
        add(lecNotice);
        setVisible(true);
        setTitle("Notices");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width, screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);
        ResultSet rs;
        Statement st;

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        {
            try {

                con = dbConnection.dbConnect();
                String query = "SELECT title FROM notice";

                st = con.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    noticrcombobox.addItem(rs.getString("title"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        }

        viewNoticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNotice();
            }
        });

    }

    public void showNotice( ) {
        String title = (String) noticrcombobox.getSelectedItem();
        ResultSet rs;
        Statement st;

        try {

            st = con.createStatement();
            rs = st.executeQuery("select description,date from notice where title=" + "'" + title + "'");

            rs.next();
            String description = rs.getString("description");
            String noticeDate = rs.getString("date");
            noticeTextArea.setText(description);
            DesDate.setText(noticeDate + "      ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


