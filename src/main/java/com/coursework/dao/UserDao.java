package com.coursework.dao;

import com.coursework.entity.User;
import java.util.ArrayList;

public interface UserDao {
    boolean insertUser(User user);
    ArrayList<User> fetchAllUsers();
    User findUserById(int id);
    User findUserByEmail(String email);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}