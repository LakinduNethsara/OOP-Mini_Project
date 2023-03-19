package com.lecturer;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class AddLectureMaterial extends JFrame {


    private JPanel addLectureMaterial;
    private JTextField materialId12;
    private JButton uploadButton;
    private JTextField textField30;
    private JButton browseButton;
    private JComboBox subcode12;
    private JButton backButton;

    File f=null;
    String path=null;

    String subcode,matid;
    PreparedStatement pst,pst1;

    String uid;

    public AddLectureMaterial() {

        add(addLectureMaterial);
        setVisible(true);
        setTitle("Upload Student Marks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);

        try {
            String Uname = UserDetails.getUsername();

            Connection con;
            con = dbConnection.dbConnect();
            String query="SELECT lecturer_id FROM lecturer where user_name='"+Uname+"'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            uid = rs.getString("lecturer_id");

            String query1="SELECT sub_code FROM lecturer_subject where lecturer_id='nun"+uid+"'";
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);

            subcode12.removeAllItems();

            while(rs1.next()){
                subcode12.addItem(rs1.getString("sub_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borwseTimetable();
            }
        });
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadTimetable();
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
            textField30.setText(path);
        }
    }


    //METHOD TO UPLOAD SELECTED DETAILS TO THE TABLE
    public void uploadTimetable(){
        //Upload file path to the database
        File f= new File(path);

        try {
            subcode= (String) subcode12.getSelectedItem();
            matid=  materialId12.getText();

            Connection con;

            con= dbConnection.dbConnect();
            Statement ps=con.createStatement();
            //InputStream is=new FileInputStream(f);
            pst1=con.prepareStatement("insert into course_material (m_id,sub_code,m_path) values(?,?,?)");
            pst1.setString(1,matid);
            pst1.setString(2,subcode);
            pst1.setString(3,path);

            int updated1=pst1.executeUpdate();


            pst=con.prepareStatement("insert into lecturer_upload_material (lecturer_id,m_id) values(?,?)");
            pst.setString(1,uid);
            pst.setString(2,matid);


            int updated=pst.executeUpdate();


            if(updated>0 && updated1>0){
                JOptionPane.showMessageDialog(null,"Material Successfully Uploaded");
                dispose();
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error...! ");
            throw new RuntimeException(ex);
        }
    }
}
