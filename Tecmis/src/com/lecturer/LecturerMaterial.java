package com.lecturer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecturerMaterial extends JFrame {


    private JPanel lecMaterial;
    private JButton addMaterialButton;
    private JButton deleteMaterialButton;
    private JButton backButton;
    private JButton seeMaterialButton;

    public LecturerMaterial(){

        add(lecMaterial);
        setVisible(true);
        setTitle("Lecture Materials");
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

        addMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddLectureMaterial();
            }
        });


        deleteMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteCourseMaterial();
            }
        });


        seeMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeeLectureMaterials();
            }
        });
    }
}
