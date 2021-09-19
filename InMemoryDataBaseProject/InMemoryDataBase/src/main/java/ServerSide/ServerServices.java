package ServerSide;
import Info.StudentInfo;
import Info.UserInfo;

public class ServerServices {
    private CachingLayer cachingLayer;
    private DataBaseDAO dataBaseDAO;
    public void init(){
        cachingLayer=CachingLayer.getCachingLayer();
        dataBaseDAO=DataBaseDAO.getDataBaseDAO(10);
    }
    public Boolean InsertStudent(StudentInfo studentInfo){
        cachingLayer.InsertInMemoryDataBase(studentInfo);
        dataBaseDAO.InsertInDataBasePrimary(studentInfo);
        return true;
    }
    public Boolean DeleteStudent(int StudentId){
        cachingLayer.DeleteInMemoryDataBase(StudentId);
        dataBaseDAO.DeleteInDataBasePrimary(StudentId);
        return true;
    }
    public Boolean UpdateStudent(StudentInfo studentInfo, int StudentId){
        cachingLayer.UpdateInMemoryDataBase(studentInfo,StudentId);
        dataBaseDAO.UpdateInDataBasePrimary(studentInfo,StudentId);
        return true;
    }
    public StudentInfo SearchStudent(int StudentId){
        StudentInfo studentInfo=cachingLayer.SearchInMemoryDataBase(StudentId);
        if(studentInfo==null){
            studentInfo=dataBaseDAO.SearchInDataBasePrimary(StudentId);
            if(studentInfo==null)
            {
                return new StudentInfo();
            }
            cachingLayer.InsertInMemoryDataBase(studentInfo);
        }
        return studentInfo;
    }
    public Boolean CreateNewUser(UserInfo userInfo){
        if(ValidationUser(userInfo)){
            System.out.println("User is Found In Data Base");
            return false;
        }
      dataBaseDAO.CreateNewUserInDataBasePrimary(userInfo);
      cachingLayer.CreateNewUserInMemory(userInfo);
      return true;
    }
    public Boolean ValidationUser(UserInfo userInfo){
        Boolean isUser=cachingLayer.ValidationUserInMemoryDataBase(userInfo);
        if(isUser){
            System.out.println("Validation from memory");
            return true;
        }
        else {
            System.out.println("Validation from HDD");
            return dataBaseDAO.ValidationUserInDataBasePrimary(userInfo);
        }
    }
}
