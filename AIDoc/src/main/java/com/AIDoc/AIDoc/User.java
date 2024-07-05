package com.AIDoc.AIDoc;
import lombok.Data;
@Data
public class User {
    private long userID;
    private String userName;
    private String userEmail;
    private String userPhone;
    public void changeUser(User user){
        this.userEmail=user.userEmail;
        this.userName=user.userName;
        this.userPhone=user.userPhone;
    }
}
