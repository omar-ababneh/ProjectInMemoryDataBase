package ClientSide;

import Info.StudentInfo;
import Info.UserInfo;
import java.util.Scanner;

public class DataInput {
    private Scanner input;
    public DataInput(){
        input=new Scanner(System.in);
    }
    public StudentInfo CreateStudentInformation(){
        input=new Scanner(System.in);
        System.out.println("Please Enter The Following information : ");
        System.out.print("Student Id : ");
        int StudentId=input.nextInt();
        System.out.print("Student First Name : ");
        String StudentFirstName=input.next();
        System.out.print("Student Last Name : ");
        String StudentLastName=input.next();
        System.out.print("GPA : ");
        Double GPA=input.nextDouble();
        System.out.print("Number Of Course : ");
        int NumberOfCourse=input.nextInt();
        return new StudentInfo(StudentId,StudentFirstName,StudentLastName,GPA,NumberOfCourse);
    }
    public int CreateStudentId(){
        System.out.print("Student Id : ");
        int StudentId=input.nextInt();
        return StudentId;
    }
    public int getOperationNumber(){
        int OperationNumber=input.nextInt();
        return OperationNumber;
    }
    public UserInfo CreateUserInformation(){
       UserInfo User=new UserInfo();
        input=new Scanner(System.in);
        System.out.println("Please Enter UserName :");
        String UserName=input.nextLine();
        System.out.println("Please Enter Password :");
        String Password=input.nextLine();
        System.out.println("Please Enter Id :");
        int Id=input.nextInt();
        User.setUserName(UserName);
        User.setPassword(Password);
        User.setUserId(Id);
        return User;
    }
}
