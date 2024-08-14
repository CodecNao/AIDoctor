package com.AIDoc.AIDoc;
import java.util.UUID;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Data;
@Data
public class User {
    private UUID userID;
    private String userName;
    private String userEmail;
    private String userPhone;
}
