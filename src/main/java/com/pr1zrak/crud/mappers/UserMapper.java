package com.pr1zrak.crud.mappers;

import com.pr1zrak.crud.core.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("INSERT INTO USERS(email_id, password, first_name, last_name) VALUES(#{emailId}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn = "user_id")
    void insertUser(User user);

    @Select("SELECT * FROM USERS WHERE user_id=#{userId}")
    @Results({
            @Result(id = true, property = "userId", column = "user_id"),
            @Result(property = "emailId", column = "email_id"),
            @Result(property = "password", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    User getUserById(long userId);

    @Select("SELECT * FROM USERS")
    @Results({
            @Result(id = true, property = "userId", column = "user_id"),
            @Result(property = "emailId", column = "email_id"),
            @Result(property = "password", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    List<User> getAllUsers();

    @Update("UPDATE USERS SET email_id=#{emailId}, password=#{password}, first_name=#{firstName}, last_name=#{lastName} " +
            "WHERE user_id=#{userId}")
    void updateUser(User user);

    @Delete("DELETE FROM USERS WHERE user_id=#{userId}")
    void deleteUser(long userId);
}
