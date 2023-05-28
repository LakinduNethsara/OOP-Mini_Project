package com.lecturer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecturerSeeEligibilityOfBatch extends JFrame {


    private JPanel lecturerEligibilityWholeBatch;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton viewButton;
    private JButton backButton;
    private JScrollPane table5;
    DefaultTableModel model = (DefaultTableModel) table1.getModel();

    public LecturerSeeEligibilityOfBatch(){
        add(lecturerEligibilityWholeBatch);
        setVisible(true);
        setTitle("Student Eligibility");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height


        try {

            ResultSet rs = LecturerDB.lecurerSubjects();

            while(rs.next())
            {
                comboBox1.addItem(rs.getString("sub_code"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        model.addColumn("Student Id");
        model.addColumn("Status");


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeEligibilityOfBatch();
            }
        });
    }


    public void seeEligibilityOfBatch()
    {
        String subid = (String) comboBox1.getSelectedItem();

        ResultSet rs = LecturerDB.studentIdForSubject(subid);

        try {
            model.setRowCount(0);
            revalidate();

            while (rs.next())
            {

                String status = StudentEligibility.studenFinalEligibility(rs.getString("student_id"),subid);

                Object[] row = {rs.getString("student_id"),status};

                model.addRow(row);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}


