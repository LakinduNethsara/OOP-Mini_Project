package com.lecturer;

public interface studentMarksExecutable {
    public Double calCGPA(String sid);
    public Double calSGPA(String sid);
    public Double finalMark(Double Q1,Double a11,Double a21,Double mid11,Double et1,Double ep1,Double etp1,Double epp1);
    public Double CAFinal(Double Q,Double a1,Double a2,Double mid1);
    public Double quizFinal(Double Q1,Double Q2,Double Q3,Double Q4);
    public Double getFinalQuizMarks(Double Q1,Double Q2,Double Q3,Double Q4);
    public Double getFinalQuizMarks(Double Q1,Double Q2,Double Q3);

}
