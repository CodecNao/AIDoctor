package com.AIDoc.AIDoc.Users;
import java.util.UUID;
import lombok.Data;
@Data
public class User {
    private UUID userID;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPassword;
}
