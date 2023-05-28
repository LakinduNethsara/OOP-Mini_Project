package com.technicalOfficer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class TechnicalOfficerMedical extends JFrame {

    private JPanel TOmedical;
    private JPanel TOmedical1;
    private JPanel TOmedical2;
    private JLabel TOmedicallabel1;
    private JLabel TOmedicallabel2;
    private JLabel TOmedicallabel3;
    private JTextField textField1;
    private JLabel TOmedicallabel4;
    private JComboBox comboBox1;
    private JLabel TOmedicallabel5;
    private JComboBox comboBox2;
    private JLabel TOmedicallabel6;
    private JLabel TOmedicallabel7;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel TOmedicallabel8;
    private JComboBox comboBox3;
    private JTextField textField4;
    private JLabel TOmedicallabel10;
    private JButton browseButton;
    private JButton uploadButton;
    private JButton backButton;

    ResultSet rs;
    File f=null;
    String mid,sid,scode,sdate,edate,status,path;
    private Connection con=null;

    public  TechnicalOfficerMedical(){
        add(TOmedical);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Medical");

        Toolkit tk = Toolkit.getDefaultToolkit();   //Initialize toolkit class
        Dimension screenSize=tk.getScreenSize();    //Get the screen resolution
        setSize(screenSize.width,screenSize.height);    //Set window width and height
        setLocationRelativeTo(null);    //center the window

        //Connection con=null;

        try {
            con = dbConnection.dbConnect();
            String query="select student_id from student";
            Statement ps= con.createStatement();
            rs = ps.executeQuery(query);

            comboBox1.removeAllItems();
            while (rs.next())
            {
                comboBox1.addItem(rs.getString("student_id"));

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sid= (String) comboBox1.getSelectedItem();
                String query="select sub_code from student_subject where student_id= '"+sid+"'";

                try {
                    con = dbConnection.dbConnect();
                    Statement ps= con.createStatement();
                    rs = ps.executeQuery(query);

                    comboBox2.removeAllItems();
                    while(rs.next()){
                        comboBox2.addItem(rs.getString("sub_code"));
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseMedical();
            }
        });
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadMedical();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void browseMedical(){
        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter fnwf =new FileNameExtensionFilter("PNG JPG AND JPEG","png","jpg","jpeg");
        fileChooser.addChoosableFileFilter(fnwf);
        int load = fileChooser.showOpenDialog(null);

        if(load==fileChooser.APPROVE_OPTION){
            f=fileChooser.getSelectedFile();
            path=f.getAbsolutePath();
            textField4.setText(path);
            ImageIcon ii= new ImageIcon(path);
            Image img =ii.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
            TOmedicallabel10.setIcon(new ImageIcon(img));
        }
    }

    public void uploadMedical(){
        mid=textField1.getText();
        scode= (String) comboBox2.getSelectedItem();
        sdate=textField2.getText();
        edate=textField3.getText();
        status=(String)comboBox3.getSelectedItem();

        try {
            con = dbConnection.dbConnect();
            InputStream is=new FileInputStream(f);
            PreparedStatement pst;
            pst=con.prepareStatement("insert into medical (medical_id,student_id,sub_code,start_date,end_date,approve_status,medical_pic_path) values ('"+mid+"','"+sid+"','"+scode+"','"+sdate+"','"+edate+"','"+status+"','"+path+"')");
            int updated=pst.executeUpdate();

            if(updated>0){
                JOptionPane.showMessageDialog(null,"Medical Successfully Added!!");
                dispose();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Could not upload!");
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
