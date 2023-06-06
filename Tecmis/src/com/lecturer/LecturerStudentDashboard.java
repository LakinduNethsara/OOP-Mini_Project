package com.lecturer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecturerStudentDashboard extends JFrame{
    private JPanel lecStuDashboard;
    private JPanel lecStuDashTitlr;
    private JPanel lecStuDashImg;
    private JPanel lecStuFun;
    private JButton uploadMarksButton;
    private JButton backButton;
    private JButton StudentDetailsbtn;
    private JButton studentDetailsOfWholeButton;
    private JButton StudentEligibilitybtn;
    private JButton eligibilityOfWholeBatchButton;
    private JButton finalMarkOfWholeButton;
    private JButton finalMarkOfStudent;
    private JButton lecStuAttendan;
    private JButton lecStuMedicals;


    public LecturerStudentDashboard(){

        add(lecStuDashboard);
        setVisible(true);
        setTitle("Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        uploadMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerStudentUploadMarks();
            }
        });

        StudentDetailsbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new lecStuentDetails();
            }
        });

        StudentEligibilitybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentEligibility();
            }
        });

        studentDetailsOfWholeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerSeeStudentDetails();
            }
        });

        eligibilityOfWholeBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerSeeEligibilityOfBatch();
            }
        });

        finalMarkOfStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FinalMarkOfStudent();
            }
        });

        finalMarkOfWholeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FinalMarksOfWholeBatch();
            }
        });

        lecStuAttendan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerViewStudentAttendance();
            }
        });

        lecStuMedicals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerSeeStudentMedicals();
            }
        });


    }
}
