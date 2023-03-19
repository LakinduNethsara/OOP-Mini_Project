package com.student;

import com.DBconnection.dbConnection;
import com.login.Login;
import com.user.UserDetails;

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

public class StudentUpdateProfile extends JFrame {
    private JPanel studentUpdateProfile;
    private JPanel studentUpdateProfileLeft;
    private JPanel studentUpdateProfileRight;
    private JLabel updateProfileIcon;
    private JTextField MobileNoTextField;
    private JButton profilePictureBrowseButton;
    private JButton updateButton;
    private JButton backButton;
    private JButton logoutButton;
    private JLabel mobileNumberLabel;
    private JLabel profilePictureLael;
    private JTextField imagePath;
    private JLabel labelImage;
    Connection con;
    public String currentUsername;
    String profile_pic_path=null;
    String phone_number="Phone";
    File f=null;
    String path=null;
    private ImageIcon fomate=null;
    String fname=null;
    int s=0;
    byte[] pimage=null;

    PreparedStatement pst;
    ResultSet rs;


    public StudentUpdateProfile(){
        add(studentUpdateProfile);
        setTitle("Update Profile");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit tk=Toolkit.getDefaultToolkit();     //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize();  //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);    //Set the width and height of the JFrame.
        setMinimumSize(screenSize);
        setLocationRelativeTo(null);

        currentUsername=UserDetails.getUsername();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        profilePictureBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borwseNewProfilePic();
            }
        });
        //Connect database


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMyProfileContact();
                updateMyProfilePic();

            }
        });
    }

    public void loadUserDetails(){

        try {
            con= dbConnection.dbConnect();
            String getStudentDetailsQuery="select phone_number,profile_pic_path,profile_pic_image from student where user_name="+"'"+currentUsername+"'";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(getStudentDetailsQuery);
            rs.next();
            phone_number=rs.getString("phone_number");
            profile_pic_path=rs.getString("profile_pic_path");
            byte[] imagedata=rs.getBytes("profile_pic_image");
            ImageIcon format = new ImageIcon(imagedata);
            Image mm=format.getImage();
            Image img2= mm.getScaledInstance( 200, 200,Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(img2);
            labelImage.setIcon(image);
            MobileNoTextField.setText(phone_number);
            imagePath.setText(profile_pic_path);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borwseNewProfilePic(){
        JFileChooser fileChooser =new JFileChooser();
        FileNameExtensionFilter fnwf =new FileNameExtensionFilter("PNG JPG AND JPEG","png","jpeg","jpg");
        fileChooser.addChoosableFileFilter(fnwf);
        int load =fileChooser.showOpenDialog(null);

        if(load==fileChooser.APPROVE_OPTION){
            f=fileChooser.getSelectedFile();
            path=f.getAbsolutePath();
            imagePath.setText(path);
            ImageIcon ii=new ImageIcon(path);
            Image img=ii.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
            labelImage.setIcon(new ImageIcon(img));
        }
    }

    public void updateMyProfilePic(){
        //Upload image and contact files to the database
        File f= new File(path);

        try {

            con= dbConnection.dbConnect();
            InputStream is=new FileInputStream(f);
            pst=con.prepareStatement("update student set profile_pic_name= ?,profile_pic_path=?,profile_pic_image=? where user_name=?");
            pst.setString(1,f.getName());
            pst.setString(2,path);
            pst.setBlob(3,is);
            pst.setString(4,currentUsername);

            int updated=pst.executeUpdate();
            if(updated>0){
                JOptionPane.showMessageDialog(null,"Profile Picture Successfully Updated");
                dispose();

            }


        } catch (FileNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Profile picture not updated");
            throw new RuntimeException(ex);
        }
    }

    public void updateMyProfileContact(){
        phone_number=MobileNoTextField.getText();
        try {

            con= dbConnection.dbConnect();
            pst=con.prepareStatement("update student set phone_number=? where user_name=?");
            pst.setString(1,phone_number);
            pst.setString(2,currentUsername);
            int updated=pst.executeUpdate();
            if(updated>0){
                JOptionPane.showMessageDialog(null,"Contact Details Successfully Updated");
            }
            dispose();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Could not update contact details");
            throw new RuntimeException(e);
        }
    }




}
