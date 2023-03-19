package com.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUser  extends JFrame {

    private JPanel AdminUserPanel;
    private JButton updateMyProfileButton;
    private JButton createStudentButton;
    private JButton createLecturerButton;
    private JButton createTechnicalOfficerButton;
    private JButton backButton;
    private JButton updateStudentButton;
    private JButton deleteStudentButton;
    private JButton updateLecturerButton;
    private JButton deleteLecturerButton;
    private JButton updateTechnicalOfficerButton;
    private JButton deleteTechnicalOfficerButton;


    public AdminUser() {

            add(AdminUserPanel);
            setVisible(true);
            setTitle("Maintain Users");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension screenSize = tk.getScreenSize();
            setSize(screenSize.width, screenSize.height);
            setLocationRelativeTo(null); //center the window

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminDashBoard adDash =new AdminDashBoard();
            }
        });
        updateMyProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateAdmin aduser = new UpdateAdmin();
            }
        });
        createStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateStudent Cs =new CreateStudent();
            }
        });
        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudent Ua = new UpdateStudent();
            }
        });
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteStudent dels =new DeleteStudent();
            }
        });
        createLecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateLecturer cl=new CreateLecturer();
            }
        });
        updateLecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateLecturer ul= new UpdateLecturer();
            }
        });
        deleteLecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteLecturer dl = new DeleteLecturer();
            }
        });
        createTechnicalOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTechnicalOfficer ct = new CreateTechnicalOfficer();
            }
        });
        updateTechnicalOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTechnicalOfficer ut =new UpdateTechnicalOfficer();
            }
        });
        deleteTechnicalOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteTechnicalOfficer dt= new DeleteTechnicalOfficer();
            }
        });
    }
}
