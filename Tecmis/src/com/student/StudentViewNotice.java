package com.student;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentViewNotice extends JFrame {
    private JPanel viewNoticePanel;
    private JPanel viewNoticePanelLeft;
    private JPanel viewNoticePanelRight;
    private JLabel titleLable;
    private JLabel noticeIconLabel;
    private JLabel selectNoticeLabel;
    private JComboBox noticeComboBox;
    private JButton viewNoticeButton;
    private JLabel dateLabel;
    private JButton backButton;
    private JTextArea noticeTextArea;



    Connection con;
    ResultSet rs;
    Statement st;


    public StudentViewNotice(){
        add(viewNoticePanel);
        setTitle("TECMIS-Notices");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk=Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);
        noticeTextArea.setMaximumSize(new Dimension(10,screenSize.height));


        {
            try {

                con = dbConnection.dbConnect();

                //read notice titles and set to the combo box
                String query="SELECT title FROM notice";
                st = con.createStatement();
                rs= st.executeQuery(query);
                noticeComboBox.removeAllItems();
                while(rs.next()){
                    noticeComboBox.addItem(rs.getString("title"));
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

        //Back button functionality
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    //method to show relevant notice
    public void showNotice(){
        String title= (String) noticeComboBox.getSelectedItem();
        try {

            st=con.createStatement();
            rs=st.executeQuery("select description,date from notice where title="+"'"+title+"'");

            rs.next();
            String description=rs.getString("description");
            String noticeDate=rs.getString("date");
            noticeTextArea.setText(description);
            dateLabel.setText(noticeDate+"    ");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No Notices Available");
            System.out.println("No Notices Available..."+e.getMessage());
        }
        
    }



}
