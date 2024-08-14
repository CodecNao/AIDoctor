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
  @Select("SELECT * FROM \"Users\"")
  List<User> findAll();
 
  @Select("SELECT * FROM \"Users\" WHERE \"userID\" = #{id}")
  User findById(UUID id);

  @Select("SELECT * FROM \"Users\" WHERE \"userEmail\" = #{userEmail}")
  User findByEmail(String email);

  @Insert("INSERT INTO \"Users\" (\"userName\", \"userEmail\", \"userPhone\") VALUES(#{userName}, #{userEmail}, #{userPhone})")
  //@Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(User user);

  @Update("UPDATE \"Users\" SET \"userName\" = #{userName}, \"userEmail\" = #{userEmail}, \"userPhone\" = #{userPhone} WHERE \"userID\" = #{id}")
  void updateById(User user);

  @Update("UPDATE \"Users\" SET \"userName\" = #{userName}, \"userEmail\" = #{userEmail}, \"userPhone\" = #{userPhone} WHERE \"userEmail\" = #{userEmail}")
  void update(User user);

  @Delete("DELETE FROM Users WHERE \"userID\" = #{id}")
  void delete(UUID id);

  @Delete("DELETE FROM \"Users\" WHERE \"userEmail\" = #{userEmail}")
  void deletefromEmail(String Email);
}

