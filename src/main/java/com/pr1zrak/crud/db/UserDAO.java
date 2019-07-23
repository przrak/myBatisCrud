package com.pr1zrak.crud.db;

import com.pr1zrak.crud.core.User;

import java.util.List;

public interface UserDAO {
    void insertUser(User user);

    User getUserById(Integer userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Integer userId);
}
