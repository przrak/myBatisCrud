package com.pr1zrak.crud.db;

import com.pr1zrak.crud.core.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void insertUser(User user);

    Optional<User> getUserById(long userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(long userId);
}
