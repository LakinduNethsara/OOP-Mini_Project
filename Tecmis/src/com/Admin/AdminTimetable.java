package com.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTimetable extends JFrame {
    private JButton createTimeTableButton;
    private JButton deleteTimeTableButton;
    private JButton backButton;
    private JPanel AdminTimeTablePanel;

    public AdminTimetable() {
    add(AdminTimeTablePanel);
    setVisible(true);
    setTitle("Maintain Timetable");
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
        createTimeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTimetable ct =new CreateTimetable();
            }
        });
        deleteTimeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteTimetable dt =new DeleteTimetable();
            }
        });
    }
}
