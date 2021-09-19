package ServerSide;
import Info.StudentInfo;
import Info.UserInfo;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import java.util.List;

public class CachingLayer {

    private static CachingLayer Instance;
    private static JedisPoolConfig poolConfig;
    private  static JedisPool jedisPool ;
    private CachingLayer(){}
    public static synchronized CachingLayer getCachingLayer(){
        if(Instance==null){
            Instance = new CachingLayer();
            poolConfig=ConfigurationCache.buildPoolConfig();
            jedisPool= new JedisPool(poolConfig, "localhost");
        }
        return Instance;
    }
    public void InsertInMemoryDataBase(StudentInfo studentInfo){

        try (Jedis jedis = jedisPool.getResource()) {
            Boolean isFound=jedis.exists("STUDENT:"+studentInfo.getStudentId());
            if(isFound){
                System.out.println("Not allow duplicate key");
            }
            jedis.lpush("STUDENT:"+studentInfo.getStudentId(),studentInfo.getStudentFirstName());
            jedis.lpush("STUDENT:"+studentInfo.getStudentId(),studentInfo.getStudentLastName());
            jedis.lpush("STUDENT:"+studentInfo.getStudentId(), String.valueOf(studentInfo.getGPA()));
            jedis.lpush("STUDENT:"+studentInfo.getStudentId(),String.valueOf(studentInfo.getNumberOfCourses()));
        }
    }
    public void DeleteInMemoryDataBase(int StudentId){
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del("STUDENT:"+StudentId);
        }

    }
    public StudentInfo SearchInMemoryDataBase(int StudentId){
        try (Jedis jedis = jedisPool.getResource()) {
            System.out.println("SearchInMemoryDataBase");
            List<String> Info = jedis.lrange("STUDENT:" + StudentId, 0, 4);
            System.out.println(jedis.info("memory"));
            StudentInfo studentInfo = new StudentInfo();
            if (Info.isEmpty()) {
                System.out.println("The record not found in the data base....");
                studentInfo = null;
            } else {
                System.out.println("StudentId  StudentFirstName  StudentLastName  GPA  NumberOfCourses");
                System.out.println(StudentId + "          " + Info.get(3) + "          " + Info.get(2) + "          " + Info.get(1) + "          " + Info.get(0) + "  ");
                studentInfo.setStudentId(StudentId);
                studentInfo.setStudentFirstName(Info.get(3));
                studentInfo.setStudentLastName(Info.get(2));
                studentInfo.setGPA(Double.parseDouble(Info.get(1)));
                studentInfo.setNumberOfCourses(Integer.parseInt(Info.get(0)));
            }
            return studentInfo;
        }
        }
    public void UpdateInMemoryDataBase(StudentInfo studentInfo, int StudentId){
        DeleteInMemoryDataBase(StudentId);
        InsertInMemoryDataBase(studentInfo);

    }
    public Boolean ValidationUserInMemoryDataBase(UserInfo userInfo) {
        try (Jedis jedis = jedisPool.getResource()) {
            List<String> Info = jedis.lrange("USER:" + userInfo.getUserId(), 0, -1);
            System.out.println(Info);
            if (Info.isEmpty()) {
                return false;
            } else if (userInfo.getPassword().equals(Info.get(0))) {
                if (userInfo.getUserName().equals(Info.get(1))) {
                    return true;
                }
            }
            return false;
        }
    }
    public void CreateNewUserInMemory(UserInfo userInfo){
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.lpush("USER:" + userInfo.getUserId(), userInfo.getUserName());
            jedis.lpush("USER:" + userInfo.getUserId(), userInfo.getPassword());
        }
    }

}




