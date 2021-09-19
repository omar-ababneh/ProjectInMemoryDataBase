package ServerSide;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConnectionPool {
    ConcurrentSkipListMap<Integer,Connection>Pool=new ConcurrentSkipListMap<>();
    public void init(int number){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected With Driver");
          for (int i=0;i<number;i++){
              Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/javabook","Omar","1234");
              Pool.put(i,connection);
          }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void close(){

            for(int i=0;i<Pool.size();i++){
                try {
                   Pool.get(i).close();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
    }
    public  Connection getConnection() {

        Connection connection= Pool.remove(Pool.size()-1);
        return connection;
    }
    public  void ReturnConnection(Connection connection){

       Pool.put(Pool.size(),connection);
    }
}
