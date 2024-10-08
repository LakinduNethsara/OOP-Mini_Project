package com.lecturer;

import com.DBconnection.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static java.lang.Double.parseDouble;

public class LecturerStudentUploadMarks extends JFrame implements studentMarksExecutable{


    private JPanel lecStuUploadMarks;
    private JPanel lecStuUploadMarksTitle;
    private JTextField textField1;
    private JButton addButton;
    private JButton backButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField14;
    private JTextField textField15;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    Connection con;

    private String stid;
    private String subcode;
    private Double q1 = 0.0;
    private Double q2 = 0.0;
    private Double q3 = 0.0;
    private Double q4 = 0.0;
    private Double asses1 = 0.0;
    private Double asses2 = 0.0;
    private Double mid = 0.0;
    private Double et = 0.0;
    private Double ep = 0.0;
    private Double CA = 0.0;
    private String grade;
    private Double gradeVal = 0.0;
    private Double finalQuizMarks = 0.0;
    private Double etp = 0.0;
    private Double epp = 0.0;
    private Double CGPA = 0.0;
    private Double SGPA = 0.0;

    private Double finalSubMark = null;

    ResultSet r4,r5;

    public LecturerStudentUploadMarks() {

        add(lecStuUploadMarks);
        setVisible(true);
        setTitle("Upload Student Marks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
        setLocationRelativeTo(null);

        ResultSet rs = LecturerDB.allStudentId();

        try {
            while(rs.next())
             {
                comboBox1.addItem(rs.getString("student_id"));
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

                //get marks form the form
                stid = (String) comboBox1.getSelectedItem();
                subcode = (String) comboBox2.getSelectedItem();
                q1 = parseDouble(textField3.getText());
                q2 = parseDouble(textField4.getText());
                q3 = parseDouble(textField5.getText());
                q4 = parseDouble(textField6.getText());
                asses1 = parseDouble(textField7.getText());
                asses2 = parseDouble(textField8.getText());
                mid = parseDouble(textField9.getText());
                et = parseDouble(textField11.getText());
                ep = parseDouble(textField12.getText());
                etp = parseDouble(textField14.getText());
                epp = parseDouble(textField15.getText());


                //method for get final quiz mark...............................................
                finalQuizMarks = quizFinal(q1,q2,q3,q4);


                //method for get final CA mark.................................................
                CA = CAFinal(finalQuizMarks,asses1,asses2,mid);

                //method for get final Subject mark
                finalSubMark = finalMark(finalQuizMarks,asses1,asses2,mid,et,ep,etp,epp);



                if(finalSubMark == null)
                {
                    JOptionPane.showMessageDialog(null, "Course Structure is not Defined");
                }
                else
                {
                    //methods for get grade and grade value
                    grade = getGrade(finalSubMark);
                    gradeVal = getGradeVal(finalSubMark);

                    String query = "insert into marks values ('"+stid+"','"+subcode+"',"+q1+","+q2+","+q3+"," +q4+"," +asses1+","+asses2+","+mid+","+CA+","+et+","+ep+",'"+grade+"',"+ gradeVal+")";

                    try {
                        con = dbConnection.dbConnect();
                        Statement ps=con.createStatement();
                        ps.executeUpdate(query);

                        JOptionPane.showMessageDialog(null, "Successfully Added marks");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Marks already exists in the Database");
                    }
                }



                //method for get CGPA
                CGPA = calCGPA(stid);
                CGPA = Math.round(CGPA*100.0)/100.0;


                //method for get SGPA
                SGPA = calSGPA(stid);
                SGPA = Math.round(SGPA*100.0)/100.0;


                //update SGPA and CGPA
                String query1 = "update student set SGPA="+SGPA+",CGPA="+CGPA+" where student_id='"+stid+"'";


                try {
                    con = dbConnection.dbConnect();
                    Statement ps=con.createStatement();
                    ps.executeUpdate(query1);

                    JOptionPane.showMessageDialog(null, "GPA Successfully Updated");
                    dispose();


                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                    System.out.println("Error is "+ex.getMessage());
                    dispose();
                    throw new RuntimeException(ex);

                }

            }
        });


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stid = (String) comboBox1.getSelectedItem();
                ResultSet rs = LecturerDB.subjectsForSid(stid);
                comboBox2.removeAllItems();

                try {
                    while (rs.next())
                    {
                            comboBox2.addItem(rs.getString("sub_code"));

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    //Method for Calculate CGPA........................................................
    public Double calCGPA(String sid)
    {
        Double cCGPA = 0.0;
        Double creGradeVal = 0.0;
        Double credit = 0.0;
        Double fcred = 0.0;
        Double fcredGPA = 0.0;
        Double fullCred = 0.0;


        String query = "select marks.student_id,marks.sub_code,marks.grade_val,subject.credit,subject.dep_id from marks INNER JOIN subject on marks.sub_code=subject.sub_code where student_id='"+sid+"'";

        try {
            con = dbConnection.dbConnect();
            Statement ps1=con.createStatement();
            r4 = ps1.executeQuery(query);

            System.out.println();


            while(r4.next())
            {
                if((r4.getString("subject.dep_id").equals("DEPMUL")) || (r4.getString("subject.credit").equals("Null")))
                {

                }

                else
                {
                    creGradeVal = r4.getDouble("marks.grade_val");
                    credit =r4.getDouble("subject.credit");

                    fullCred = fullCred+credit;

                    fcred = creGradeVal * credit;

                    fcredGPA = fcredGPA + fcred;
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        cCGPA = (fcredGPA/fullCred);

        return cCGPA;
    }


    //Method for Calculate SGPA.............................................................
    public Double calSGPA(String sid)
    {
        Double sCGPA = 0.0;
        Double creGradeVal = 0.0;
        Double credit = 0.0;
        Double fcred = 0.0;
        Double fcredGPA = 0.0;
        Double fullCred = 0.0;


        String query = "select marks.student_id,marks.sub_code,marks.grade_val,subject.credit,subject.dep_id from marks INNER JOIN subject on marks.sub_code=subject.sub_code where student_id='"+sid+"'";

        try {
            con = dbConnection.dbConnect();
            Statement ps2=con.createStatement();
            r5 = ps2.executeQuery(query);


            while(r5.next())
            {
                if((r5.getString("subject.credit").equals("Null"))){

                }

                else
                {
                    creGradeVal = r5.getDouble("marks.grade_val");
                    credit =r5.getDouble("subject.credit");

                    fullCred = fullCred+credit;

                    fcred = creGradeVal * credit;

                    fcredGPA = fcredGPA + fcred;
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        sCGPA = (fcredGPA/fullCred);

        return sCGPA;

    }



    //Method for return final Mark .....................................................................
    public Double finalMark(Double Q1,Double a11,Double a21,Double mid11,Double et1,Double ep1,Double etp1,Double epp1)
    {
        Double finalmark1 = null;


        if((Q1!=-1) && (mid11 != -1)&& (mid11 != -1)&& (et1 != -1) && (ep1 != -1))
        {
            Double quizFinal = Q1/10;
            Double midFinal = mid11/5;
            Double endTFinal = (et1*etp1)/100;
            Double endPFinal = (et1*epp1)/100;

            finalmark1 = (quizFinal+midFinal+endTFinal+endPFinal);

        }

        if((Q1!=-1) && (mid11 != -1)&& (a11 != -1)&& (a21 != -1)&& (et1 != -1))
        {
            Double quizFinal = Q1/10;
            Double assesFinal = (a11+a21)/10;
            Double midFinal = mid11/5;
            Double endTFinal = (et1*etp1)/100;


            finalmark1 = (quizFinal+midFinal+endTFinal+assesFinal);

        }

        if((Q1!=-1) && (a11 != -1)&& (a21 != -1)&& (et1 != -1) && (ep1 != -1))
        {
            Double quizFinal = Q1/10;
            Double assesFinal = (a11+a21)/10;
            Double endTFinal = (et1*etp1)/100;
            Double endPFinal = (et1*epp1)/100;

            finalmark1 = (quizFinal+endTFinal+assesFinal+endPFinal);

        }

        if((Q1!=-1) && (a11 != -1)&& (a21 != -1)&& (et1 != -1) && (ep1 == -1))
        {
            Double quizFinal = Q1/10;
            Double assesFinal = (a11+a21)/20;
            Double endTFinal = (et1*etp1)/100;

            finalmark1 = (quizFinal+endTFinal+assesFinal);

        }

        return finalmark1;

    }



    //Method for return final CA Mark ....................................................................
    public Double CAFinal(Double Q,Double a1,Double a2,Double mid1)
    {
        Double CA1 = null;


        if((Q!=-1) && (mid1 != -1))
        {
            CA1 = (Q+mid1)/2;
        }

        if((a1!=-1) && (a2 != -1) && (Q != -1)&& (mid1 != -1))
        {
            CA1 = (a1+a2+Q+mid1)/4;
        }

        if((a1!=-1) && (a2 != -1) && (Q != -1))
        {
            CA1 = (a1+a2+Q)/3;
        }

        return CA1;
    }




    //Method for return final quiz mark ................................................................
    public Double quizFinal(Double Q1,Double Q2,Double Q3,Double Q4)
    {
        Double finalQuizMark = 0.0;

        if((Q1!=-1.0) && (Q2!=-1.0) && (Q3!=-1.0) && (Q4!=-1.0))
        {
            finalQuizMark = getFinalQuizMarks(Q1,Q2,Q3,Q4);        //method for get final quiz mark if 4 quizzes are existing
        }
        else if((Q1==-1.0) && (Q2!=-1.0) && (Q3!=-1.0) && (Q4!=-1.0))
        {
            finalQuizMark = getFinalQuizMarks(Q2,Q3,Q4);           //method for get final quiz mark if 3 quizzes are existing
        }
        else if((Q1!=-1.0) && (Q2==-1.0) && (Q3!=-1.0) && (Q4!=-1.0))
        {
            finalQuizMark = getFinalQuizMarks(Q1,Q3,Q4);
        }
        else if((Q1!=-1.0) && (Q2!=-1.0) && (Q3==-1.0) && (Q4!=-1.0))
        {
            finalQuizMark = getFinalQuizMarks(Q1,Q2,Q4);
        }
        else if((Q1!=-1.0) && (Q2!=-1.0) && (Q3!=-1.0) && (Q4==-1.0))
        {
            finalQuizMark = getFinalQuizMarks(Q1,Q2,Q3);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Enter Quiz Marks correctly");
        }



        return finalQuizMark;
    }


    //method for calculate final quiz marks if there is 4 quizzes ................................................
    public Double getFinalQuizMarks(Double Q1,Double Q2,Double Q3,Double Q4)
    {
        Double finalQuizMark = 0.0;

        Double[] QM = new Double[]{Q1,Q2,Q3,Q4};
        Arrays.sort(QM);

        finalQuizMark = (QM[1]+QM[2]+QM[3])/3;

        return finalQuizMark;

    }


    //method for calculate final quiz marks if there is 3 quizzes ...................................................
    public Double getFinalQuizMarks(Double Q1,Double Q2,Double Q3)
    {
        Double finalQuizMark = 0.0;

        Double[] QM = new Double[]{Q1,Q2,Q3};
        Arrays.sort(QM);

        finalQuizMark = (QM[1]+QM[2])/2;

        return finalQuizMark;

    }



    //method for calculate final grade ....................................................................
    public String getGrade(Double FinalMark)
    {
        if((finalSubMark<101)&&(finalSubMark>=80))
        {
            grade = "A+";
        }

        else if((finalSubMark<80)&&(finalSubMark>=75))
        {
            grade = "A";
        }

        else if((finalSubMark<75)&&(finalSubMark>=70))
        {
            grade = "A-";
        }

        else if((finalSubMark<70)&&(finalSubMark>=65))
        {
            grade = "B+";
        }

        else if((finalSubMark<65)&&(finalSubMark>=60))
        {
            grade = "B";
        }

        else if((finalSubMark<60)&&(finalSubMark>=55))
        {
            grade = "B-";
        }

        else if((finalSubMark<55)&&(finalSubMark>=50))
        {
            grade = "C+";
        }

        else if((finalSubMark<50)&&(finalSubMark>=45))
        {
            grade = "C";
        }

        else if((finalSubMark<45)&&(finalSubMark>=40))
        {
            grade = "C-";
        }

        else if((finalSubMark<40)&&(finalSubMark>=30))
        {
            grade = "D";
        }

        else if((finalSubMark<30)&&(finalSubMark>=0))
        {
            grade = "F";
        }

        return grade;

    }



    //method for calculate final grade value ......................................................................
    public Double getGradeVal(Double FinalMark)
    {
        if((finalSubMark<101)&&(finalSubMark>=80))
        {
            gradeVal = 4.0;
        }

        else if((finalSubMark<80)&&(finalSubMark>=75))
        {
            gradeVal = 4.0;
        }

        else if((finalSubMark<75)&&(finalSubMark>=70))
        {
            gradeVal = 3.7;
        }

        else if((finalSubMark<70)&&(finalSubMark>=65))
        {
            gradeVal = 3.3;
        }

        else if((finalSubMark<65)&&(finalSubMark>=60))
        {
            gradeVal = 3.0;
        }

        else if((finalSubMark<60)&&(finalSubMark>=55))
        {
            gradeVal = 2.7;
        }

        else if((finalSubMark<55)&&(finalSubMark>=50))
        {
            gradeVal = 2.3;
        }

        else if((finalSubMark<50)&&(finalSubMark>=45))
        {
            gradeVal = 2.0;
        }

        else if((finalSubMark<45)&&(finalSubMark>=40))
        {
            gradeVal = 1.7;
        }

        else if((finalSubMark<40)&&(finalSubMark>=30))
        {
            gradeVal = 1.0;
        }

        else if((finalSubMark<30)&&(finalSubMark>=0))
        {
            gradeVal = 0.0;
        }

        return gradeVal;

    }
}
