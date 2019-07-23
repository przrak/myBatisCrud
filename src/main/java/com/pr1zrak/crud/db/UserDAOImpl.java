package com.pr1zrak.crud.db;

import com.pr1zrak.crud.core.User;
import com.pr1zrak.crud.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO{
    private final SqlSessionFactory sessionFactory;

    public UserDAOImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public User getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).getAllUsers();
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer userId) {

    }
}
