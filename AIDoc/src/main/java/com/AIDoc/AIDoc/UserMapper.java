package com.AIDoc.AIDoc;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
  @Select("SELECT * FROM Users")
  List<User> findAll();

  @Select("SELECT * FROM Users WHERE id = #{id}")
  User findById(UUID id);

  @Insert("INSERT INTO Users(userName, userEmail, userPhone) VALUES(#{userName}, #{userEmail}, #{userPhone})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(User user);

  @Update("UPDATE Users SET userName = #{userName}, userEmail = #{userEmail}, userPhone = #{userPhone} WHERE id = #{id}")
  void update(User user);

  @Delete("DELETE FROM Users WHERE id = #{id}")
  void delete(UUID id);
}

