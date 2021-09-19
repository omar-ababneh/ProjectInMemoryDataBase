package ClientSide;

import Communicate.Communicate;
import Info.StudentInfo;
import Info.UserInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientCommunicate implements Communicate {
    private ClientConnection connection;
    private DataInput dataInput;
    private Display display;
    public ClientCommunicate(){
       connection=new ClientConnection();
       dataInput=new DataInput();
       display=new Display();
   }
    @Override
    public void init(){
       connection.OpenConnection();
       connection.OpenResource();
   }
    @Override
    public Boolean CommunicateInOrderLogin(){
        UserInfo User=dataInput.CreateUserInformation();
        ObjectOutputStream out=connection.getOut();
        ObjectInputStream in=connection.getIn();
        Boolean isUser=false;
        try{
            out.writeObject("Log in");
            out.writeObject(User);
            out.flush();
            isUser=in.readBoolean();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return isUser;
        }
    }
    public  void CommunicateInOrderStopServer(){
        ObjectOutputStream out=connection.getOut();
        try {
            out.writeObject("Stop");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void CommunicateInOrderInsert() {
        StudentInfo studentInfo;
        try {
            studentInfo=dataInput.CreateStudentInformation();
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            out.writeObject("Insert");
            out.writeObject(studentInfo);
            out.flush();
            Boolean isInsert = in.readBoolean();
            if (isInsert) {
                System.out.println("Insert Record Successfully...");
            } else {
                System.out.println("Insert Record Not Successfully...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void CommunicateInOrderDelete() {
        int StudentId;
        try {
            StudentId=dataInput.CreateStudentId();
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            out.writeObject("Delete");
            out.writeInt(StudentId);
            out.flush();
            Boolean isDelete = in.readBoolean();
            if (isDelete) {
                System.out.println("Delete Record Successfully...");
            } else {
                System.out.println("Delete Record Not Successfully...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void CommunicateInOrderUpdate() {
        StudentInfo studentInfo;
        int StudentId;
        try {
            studentInfo=dataInput.CreateStudentInformation();
            StudentId=dataInput.CreateStudentId();
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            out.writeObject("Update");
            out.writeObject(studentInfo);
            out.writeInt(StudentId);
            out.flush();
            Boolean isUpdate = in.readBoolean();
            if (isUpdate) {
                System.out.println("Update Record Successfully...");
            } else {
                System.out.println("Update Record Not Successfully...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void CommunicateInOrderSearch() {
        int StudentId;
        try {
            StudentId=dataInput.CreateStudentId();
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            out.writeObject("Search");
            out.writeInt(StudentId);
            out.flush();
            StudentInfo studentInfo = (StudentInfo) in.readObject();
            display.ShowRecord(studentInfo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public Boolean CommunicateInOrderCreateUser(){
        UserInfo User=dataInput.CreateUserInformation();
        ObjectOutputStream out=connection.getOut();
        ObjectInputStream in=connection.getIn();
        Boolean isUser=false;
        try{
            out.writeObject("CreateNewUser");
            out.writeObject(User);
            out.flush();
            isUser=in.readBoolean();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return isUser;
        }
    }
    @Override
    public void CommunicateInOrderLogout() {
        try {
            ObjectOutputStream out=connection.getOut();
            out.writeObject("Log Out");
            out.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void close(){
        connection.CloseResource();
        connection.CloseConnection();
    }

}