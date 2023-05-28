package com.Admin;

import com.DBconnection.dbConnection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class CreateTimetable extends JFrame{
    private JPanel CreateTimetableLable;
    private JTextField textField1;
    private JButton backButton;
    private JButton addButton;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JButton browseButton;
    private JComboBox comboBox2;
    private JTextField textField3;

    Connection con;
    String path=null;
    File f=null;
    String depId,level;

    PreparedStatement pst;
    public CreateTimetable() {

        add(CreateTimetableLable);
        setVisible(true);
        setTitle("Create Timetable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);

        try {
            Connection con = dbConnection.dbConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select dep_id from department");
            comboBox1.removeAllItems();
            while (rs.next()) {
                comboBox1.addItem(rs.getString("dep_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTimetable();
            }
        });
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borwseTimetable();
            }
        });
    }
    //METHOD TO MAKE BROWSE BUTTON
    public void borwseTimetable(){
        JFileChooser fileChooser =new JFileChooser();
        FileNameExtensionFilter fnwf =new FileNameExtensionFilter("PNG JPG AND JPEG PDF","png","jpeg","jpg","pdf");
        fileChooser.addChoosableFileFilter(fnwf);
        int load =fileChooser.showOpenDialog(null);

        if(load==fileChooser.APPROVE_OPTION){
            f=fileChooser.getSelectedFile();
            path=f.getAbsolutePath();
            textField2.setText(path);
        }
    }

    public void CreateTimetable(){
        //Upload file path to the database
        File f= new File(path);

        try {
            depId= (String) comboBox1.getSelectedItem();
            level= (String) comboBox2.getSelectedItem();

            con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            //InputStream is=new FileInputStream(f);
            pst=con.prepareStatement("insert into time_table (level,t_table_path,dep_id) values(?,?,?)");
            pst.setString(1,level);
            pst.setString(2,path);
            pst.setString(3,depId);


            int updated=pst.executeUpdate();
            if(updated>0){
                JOptionPane.showMessageDialog(null,"Timetable Successfully Uploaded");
                dispose();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Time Table is Exists");
            throw new RuntimeException(ex);
        }

    }

}




