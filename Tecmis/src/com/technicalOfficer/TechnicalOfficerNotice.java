package com.technicalOfficer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TechnicalOfficerNotice extends JFrame {
    private JPanel TOnoticepanel;
    private JPanel TOnotice1;
    private JPanel TOnotice2;
    private JLabel TOnoticelabel1;
    private JLabel TOnoticelabel2;
    private JLabel TOnoticelabel3;
    private JComboBox comboBox1;
    private JButton noticebtn1;
    private JButton noticebtn2;
    private JTextArea textArea1;
    private JLabel TOnoticelabel4;

    Connection con;
    ResultSet rs;
    Statement st;

    public TechnicalOfficerNotice() {
        add(TOnoticepanel);
        setTitle("Notice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width, screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);
        textArea1.setMaximumSize(new Dimension(10, screenSize.height));

        {
            try {

                con = dbConnection.dbConnect();
                String query = "SELECT title FROM notice";

                st = con.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    comboBox1.addItem(rs.getString("title"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        }

        noticebtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNotice();
            }
        });
        noticebtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void showNotice() {
        String title = (String) comboBox1.getSelectedItem();
        try {

            st = con.createStatement();
            rs = st.executeQuery("select description,date from notice where title=" + "'" + title + "'");

            rs.next();
            String description = rs.getString("description");
            String noticeDate = rs.getString("date");
            textArea1.setText(description);
            TOnoticelabel4.setText(noticeDate + "    ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
