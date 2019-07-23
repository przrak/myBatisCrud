package com.pr1zrak.crud.db;

import com.pr1zrak.crud.core.User;
import com.pr1zrak.crud.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO{
    private final SqlSessionFactory sessionFactory;

    public UserDAOImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertUser(User user) {
        try (SqlSession session = sessionFactory.openSession()) {
            session.getMapper(UserMapper.class).insertUser(user);
            session.commit();
        }
    }

    @Override
    public Optional<User> getUserById(long userId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.getMapper(UserMapper.class).getUserById(userId));
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).getAllUsers();
        }
    }

    @Override
    public void updateUser(User user) {
        try (SqlSession session = sessionFactory.openSession()) {
            session.getMapper(UserMapper.class).updateUser(user);
            session.commit();
        }
    }

    @Override
    public void deleteUser(long userId) {
        try (SqlSession session = sessionFactory.openSession()) {
            session.getMapper(UserMapper.class).deleteUser(userId);
            session.commit();
        }
    }
}
