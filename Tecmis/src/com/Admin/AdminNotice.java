package com.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminNotice extends JFrame{
    private JPanel AdminNoticePanel;
    private JButton createNoticeButton;
    private JButton deleteNoticeButton;
    private JButton backButton;
public AdminNotice() {
    add(AdminNoticePanel);
    setTitle("Maintain Notice");
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    setSize(screenSize.width, screenSize.height);
    setLocationRelativeTo(null);



    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        AdminDashBoard adnotice = new AdminDashBoard();
        }
    });
    createNoticeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreateNotice cn= new CreateNotice();
        }
    });
    deleteNoticeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteNotice dn= new DeleteNotice();
        }
    });
}
}
