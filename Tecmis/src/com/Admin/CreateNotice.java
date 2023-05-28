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

public class CreateNotice extends JFrame {
    private JPanel CreateNoticePanel;
    private JTextField textField1;
    private JButton backButton;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    String id, date="", title, description;

    public CreateNotice() {
        add(CreateNoticePanel);
        setVisible(true);
        setTitle("Create Notice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);


            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createNotice();
                }
            });
        }
        public void createNotice(){
        id=textField1.getText();
        date=textField2.getText();
        title=textField3.getText();
        description=textField4.getText();

            try {
                Connection con= dbConnection.dbConnect();
                Statement ps=con.createStatement();
                String query="select notice_id,title from notice";
                ResultSet rs = ps.executeQuery(query);
                while(rs.next()){
                    String notice_id = rs.getString("notice_id");
                    String readTitle = rs.getString("title");

                    if ((id.equals(notice_id))||(readTitle.equals(title))) {
                        JOptionPane.showMessageDialog(null, "Data is exists");
                        dispose();
                        new CreateNotice();
                    }
                }

                String insertQuery="insert into notice values("+"'"+id+"','"+date+"','"+title+"','"+description+"')";
                ps.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Added!!!");
                dispose();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

}

