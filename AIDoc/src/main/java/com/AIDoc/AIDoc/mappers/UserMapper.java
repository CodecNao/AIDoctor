package com.AIDoc.AIDoc.mappers;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.AIDoc.AIDoc.Users.*;

@Mapper
public interface UserMapper {
  @Select("SELECT * FROM users")
  List<User> findAll();
 
  @Select("SELECT * FROM users WHERE \"userID\" = #{id}")
  User findById(UUID id);

  @Select("SELECT * FROM users WHERE \"userEmail\" = #{userEmail}")
  User findByEmail(String email);

  @Insert("INSERT INTO users (\"userName\", \"userEmail\", \"userPhone\", \"userPassword\") VALUES(#{userName}, #{userEmail}, #{userPhone}, #{userPassword})")
  //@Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(User user);

  @Update("UPDATE users SET \"userName\" = #{userName}, \"userEmail\" = #{userEmail}, \"userPhone\" = #{userPhone}, \"userPassword\"=#{userPassword} WHERE \"userID\" = #{id}")
  void updateById(User user);

  @Update("UPDATE users SET \"userName\" = #{userName}, \"userEmail\" = #{userEmail}, \"userPhone\" = #{userPhone}, \"userPassword\" = #{userPassword} WHERE \"userEmail\" = #{userEmail}")
  void update(User user);

  @Delete("DELETE FROM users WHERE \"userID\" = #{id}")
  void delete(UUID id);

  @Delete("DELETE FROM users WHERE \"userEmail\" = #{userEmail}")
  void deletefromEmail(String Email);
}

