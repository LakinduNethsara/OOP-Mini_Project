package com.lecturer;

import com.DBconnection.dbConnection;
import com.user.UserDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturerDB {

    //For get all subjects related to a student id
    public static ResultSet subjectsForSid(String stid) {

            Connection con = null;
            ResultSet rs;
            try {
                con = dbConnection.dbConnect();
                String query = "SELECT sub_code FROM student_subject where student_id='" + stid + "'";

                Statement st = con.createStatement();
                rs = st.executeQuery(query);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return rs;
        }



        //For get all student id numbers
        public static ResultSet allStudentId() {

            Connection con = null;
            ResultSet rs;

            try {
                con = dbConnection.dbConnect();

                String query = "SELECT student_id FROM student";
                Statement st = con.createStatement();
                rs = st.executeQuery(query);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return rs;
        }


        //For get lecturer comductimg Subjects
        public static ResultSet lecurerSubjects()
        {
            String uname = UserDetails.getUsername();
            ResultSet rs,rs1 = null;
            Connection con = null;

            try {
                con= dbConnection.dbConnect();
                Statement ps=con.createStatement();
                rs = ps.executeQuery("select lecturer_id from lecturer where user_name='"+uname+"'");
                rs.next();

                Statement ps1=con.createStatement();
                rs1 = ps1.executeQuery("select sub_code from lecturer_subject where lecturer_id='"+rs.getString("lecturer_id")+"'");


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return rs1;

        }



    //For get all student ids related to a subject_code
    public static ResultSet studentIdForSubject(String subid) {

        Connection con = null;
        ResultSet rs;
        try {
            con = dbConnection.dbConnect();
            String query = "SELECT student_id FROM student_subject where sub_code='" + subid + "'";

            Statement st = con.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }

}



