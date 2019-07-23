package com.pr1zrak.crud.mappers;

import com.pr1zrak.crud.core.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    void insertUser(User user);

    User getUserById(Integer userId);

    @Select("select * from users")
    @Results({
            @Result(id = true, property = "userId", column = "user_id"),
            @Result(property = "emailId", column = "email_id"),
            @Result(property = "password", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Integer userId);
}
