package ServerSide;


import Communicate.Communicate;
import Info.StudentInfo;
import Info.UserInfo;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerCommunicate implements Communicate {
    private ServerServices ServerServices = new ServerServices();
    private ServerConnection connection;

    public ServerCommunicate(){
        connection=new ServerConnection();
    }
    @Override
    public void init(){
        connection.OpenConnection();
        ServerServices.init();
    }
    public void AcceptConnection(){
        connection.Accept();
        connection.OpenResource();
    }
    public String OperationType(){
        ObjectInputStream in=connection.getIn();
        String OperationType;
        try {
             OperationType = (String) in.readObject();
        } catch (Exception e) {
           OperationType="null";
        }
        return OperationType;
    }

    @Override
    public Boolean CommunicateInOrderLogin(){
        Boolean isUser=null;
        try {
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            UserInfo userInfo = (UserInfo) in.readObject();
            isUser = ServerServices.ValidationUser(userInfo);
            out.writeBoolean(isUser);
            out.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isUser;
    }
    @Override
    public void CommunicateInOrderInsert() {
        try {
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            StudentInfo studentInfo = (StudentInfo) in.readObject();
            Boolean isInsert = ServerServices.InsertStudent(studentInfo);
            out.writeBoolean(isInsert);
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void CommunicateInOrderDelete() {
        try {
            try {
                ObjectOutputStream out=connection.getOut();
                ObjectInputStream in=connection.getIn();
                int studentId = in.readInt();
                Boolean isDelete = ServerServices.DeleteStudent(studentId);
                out.writeBoolean(isDelete);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void CommunicateInOrderUpdate() {
        try {
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            StudentInfo studentInfo = (StudentInfo) in.readObject();
            int StudentId = in.readInt();
            Boolean isUpdate = ServerServices.UpdateStudent(studentInfo, StudentId);
            out.writeBoolean(isUpdate);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void CommunicateInOrderSearch() {
        try {
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            int StudentId = in.readInt();
            StudentInfo studentInfo = ServerServices.SearchStudent(StudentId);
            out.writeObject(studentInfo);
            out.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public Boolean CommunicateInOrderCreateUser(){
        Boolean isCreateUser=null;
        try {
            ObjectOutputStream out=connection.getOut();
            ObjectInputStream in=connection.getIn();
            UserInfo userInfo = (UserInfo) in.readObject();
            System.out.println(userInfo);
            isCreateUser = ServerServices.CreateNewUser(userInfo);
            out.writeBoolean(isCreateUser);
            out.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isCreateUser;
    }
    @Override
    public void CommunicateInOrderLogout() {
        close();

    }
    @Override
    public void close(){

       //connection.CloseResource();
       // connection.CloseConnection();
    }


}
