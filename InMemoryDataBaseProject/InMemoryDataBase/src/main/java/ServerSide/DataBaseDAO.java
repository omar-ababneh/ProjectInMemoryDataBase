package ServerSide;

import Info.StudentInfo;
import Info.UserInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseDAO {
    private static DataBaseDAO dataBaseDAO;
    private static ConnectionPool connectionPool;
    private DataBaseDAO(){}
    public synchronized static DataBaseDAO getDataBaseDAO(int NumberOfConnection){
        if(dataBaseDAO==null) {
            dataBaseDAO = new DataBaseDAO();
            connectionPool=new ConnectionPool();
            connectionPool.init(NumberOfConnection);
        }
        return dataBaseDAO;
    }
    public void InsertInDataBasePrimary(StudentInfo studentInfo){
        Connection connection = null;
           try {
               connection =connectionPool.getConnection();
               Statement statement=connection.createStatement();
               String SqlStatement="insert into Student " +
                       "values ("+"'"+studentInfo.getStudentId()+"'"+","+"'"+studentInfo.getStudentFirstName()+"'"+","+
                       "'"+studentInfo.getStudentLastName()+"'"+","+"'"+studentInfo.getGPA()+"'"+","+"'"+studentInfo.getNumberOfCourses()+"'"+")";
               statement.executeUpdate(SqlStatement);

           }catch (Exception e){
               System.out.println(e);
           }finally {
               try {
                   connectionPool.ReturnConnection(connection);
               }catch (NullPointerException e){
                   System.out.println(e);
               }
           }
    }
    public void DeleteInDataBasePrimary(int StudentId){

        Connection connection=null;
        try {
            connection=connectionPool.getConnection();
            Statement statement=connection.createStatement();
            String SqlStatement="delete from Student where StudentId="+StudentId;
            statement.executeUpdate(SqlStatement);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                connectionPool.ReturnConnection(connection);
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }

    }
    public StudentInfo SearchInDataBasePrimary(int StudentId){
        System.out.println("SearchInDataBasePrimary");
        StudentInfo studentInfo=null;
        Connection connection=null;
        try {
            connection=connectionPool.getConnection();
            Statement statement=connection.createStatement();
            String SqlStatement="select * from Student where StudentId="+StudentId;
            ResultSet resultSet=statement.executeQuery(SqlStatement);
            if (resultSet.next()){
                System.out.println("StudentId : "+resultSet.getString(1)+"     StudentFirstName : "+resultSet.getString(2)+"        StudentLastName : "+resultSet.getString(3)+"       GPA : "+resultSet.getString(4)+"     NumberOfCourses : "+resultSet.getString(5));
                studentInfo=new StudentInfo();
                studentInfo.setStudentId(Integer.parseInt(resultSet.getString(1)));
                studentInfo.setStudentFirstName(resultSet.getString(2));
                studentInfo.setStudentLastName(resultSet.getString(3));
                studentInfo.setGPA(Double.parseDouble(resultSet.getString(4)));
                studentInfo.setNumberOfCourses(Integer.parseInt(resultSet.getString(5)));
            }
        }catch (Exception e){
            System.out.println("Exception SearchInDataBasePrimary");
            System.out.println(e);
        }
        finally {
            try {
                connectionPool.ReturnConnection(connection);
            }catch (NullPointerException e){
                System.out.println("NullPointerException SearchInDataBasePrimary");
                System.out.println(e);
            }
        }
        return studentInfo;

    }
    public void UpdateInDataBasePrimary(StudentInfo studentInfo, int StudentId){
        DeleteInDataBasePrimary(StudentId);
        InsertInDataBasePrimary(studentInfo);
    }
    public Boolean ValidationUserInDataBasePrimary(UserInfo userInfo) {
        Connection connection=null;
        try {
            connection=connectionPool.getConnection();
            Statement statement=connection.createStatement();
            String SqlStatement="select * from User where UserId="+userInfo.getUserId();
            ResultSet resultSet=statement.executeQuery(SqlStatement);
            if (resultSet.next()){
                System.out.println("UserId : "+resultSet.getString(1)+"     UserName : "+resultSet.getString(2)+"        Password : "+resultSet.getString(3));
                if(userInfo.getUserName().equals(resultSet.getString(2))){
                    if(userInfo.getPassword().equals(resultSet.getString(3))){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            try {
                connectionPool.ReturnConnection(connection);
            }catch (NullPointerException e){
                System.out.println("NullPointerException ReturnConnection");
                System.out.println(e);
            }
        }
        return false;
    }
    public void CreateNewUserInDataBasePrimary(UserInfo userInfo){
        Connection connection=null;
        try {
            connection =connectionPool.getConnection();
            Statement statement=connection.createStatement();
            String SqlStatement="insert into User " +
                    "values ("+"'"+userInfo.getUserId()+"'"+","+"'"+userInfo.getUserName()+"'"+","+
                    "'"+userInfo.getPassword()+"'"+")";
            statement.executeUpdate(SqlStatement);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            try {
                connectionPool.ReturnConnection(connection);
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
    }
}
