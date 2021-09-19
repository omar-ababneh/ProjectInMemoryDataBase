package Info;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int UserId;
    private String UserName;
    private String Password;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId= userId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
