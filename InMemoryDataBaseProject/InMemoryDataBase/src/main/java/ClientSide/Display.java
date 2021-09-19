package ClientSide;
import IDisplay.IDisplayGoodBye;
import IDisplay.IDisplaySorry;
import Info.StudentInfo;
import IDisplay.IDisplayHello;

public class Display implements IDisplayHello, IDisplaySorry, IDisplayGoodBye {

    @Override
    public void ShowHelloMessage() {
        System.out.println("Hello You present in the Student Database.");
    }

    @Override
    public void ShowSorryMessage() {
        System.out.println("Iam Sorry you are not register in this service.");
    }

    @Override
    public void ShowGoodByeMessage() {
        System.out.println("Good Bye :)");
    }
    public void ShowMenu(){
        System.out.println("----------------Welcome To DataBase Student----------------");
        System.out.println();
        System.out.println("Please Choose One of Operations :");
        System.out.println();
        System.out.println("1- Insert Record.");
        System.out.println("2- Delete Record.");
        System.out.println("3- Update Record.");
        System.out.println("4- Search (Retrieve One Record).");
        System.out.println("5- Create New User.");
        System.out.println("6- LogOut.");
    }
    public void ShowRecord(StudentInfo studentInfo){
        System.out.println();
        System.out.println("----------------Record Student----------------");
        System.out.println("Student Id : " + studentInfo.getStudentId());
        System.out.println("Student First Name : " + studentInfo.getStudentFirstName());
        System.out.println("Student Last Name : " + studentInfo.getStudentLastName());
        System.out.println("Student GPA : " + studentInfo.getGPA());
        System.out.println("Student Number Of Course : " + studentInfo.getNumberOfCourses());
        System.out.println("----------------Record Student----------------");
        System.out.println();
    }

}
