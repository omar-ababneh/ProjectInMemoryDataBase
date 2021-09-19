package Info;

import java.io.Serializable;

public class StudentInfo implements Serializable  {
    private int StudentId;
    private String StudentFirstName;
    private String StudentLastName;
    private double GPA;
    private int NumberOfCourses;
    public StudentInfo(){}
    public StudentInfo(int StudentId,String StudentFirstName,String StudentLastName,double GPA,int NumberOfCourses){
        this.StudentFirstName=StudentFirstName;
        this.StudentId=StudentId;
        this.StudentLastName=StudentLastName;
        this.GPA=GPA;
        this.NumberOfCourses=NumberOfCourses;
    }

    public int getStudentId() {
        return StudentId;
    }

    public String getStudentFirstName() {
        return StudentFirstName;
    }

    public String getStudentLastName() {
        return StudentLastName;
    }

    public double getGPA() {
        return GPA;
    }

    public int getNumberOfCourses() {
        return NumberOfCourses;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public void setStudentFirstName(String studentFirstName) {
        StudentFirstName = studentFirstName;
    }

    public void setStudentLastName(String studentLastName) {
        StudentLastName = studentLastName;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public void setNumberOfCourses(int numberOfCourses) {
        NumberOfCourses = numberOfCourses;
    }
}
