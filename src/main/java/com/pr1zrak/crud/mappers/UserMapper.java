package com.pr1zrak.crud.mappers;

import com.pr1zrak.crud.core.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Insert("INSERT INTO USERS(email_id, password, first_name, last_name) VALUES(#{emailId}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id", flushCache = false)
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

    /*
    We can also execute the stored procedure using @Select annotation. Here we
    need to pass the name of the stored procedure, the parameter list and use an explicit Call to that procedure
     */
    @Select(value = "{CALL getUserByProc(#{userId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public User getUserByProc(Integer userId);

    @SelectProvider(type = MyBatisUtil.class, method = "getUserByName")
    public User getUserByName(String name);

    /*
    @Results – it is a list of result mappings that contain the details of how the database columns are mapped to Java class attributes:
    @Result – it represents a single instance of Result out of the list of results retrieved from @Results. It includes the details like
    mapping from database column to Java bean property, Java type of the property and also the association with other Java objects:
     */
    @Results(value = {
            @Result(property = "personId", column = "personId"),
            @Result(property = "name", column = "name"),
            @Result(property = "addresses", javaType = List.class)
            // ...
    })
    User getUserByIdd(Integer userId);

    /*
    @Many – it specifies a mapping of one object to a collection of the other objects:
     */
//    @Results(value ={
//      @Result(property = "addresses", javaType = List.class, column = "personId", many=@Many(select = "getAddresses"))
//    })
//    @Select("select addressId, streetAddress, personId from address where personId=#{personId}")
//    public Address getAddresses(Integer personId);
    /*
    Similar to @Many annotation, we have @One annotation which specifies the one to one mapping relationship between objects.
     */

    /*
    @MapKey – this is used to convert the list of records to Map of records with the key as defined by value attribute:
     */
    @Select("select * from users")
    @MapKey("userId")
    Map<Integer, User> getAllUserss();
}
