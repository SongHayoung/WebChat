package Chatting.Dao;

import Chatting.Entity.User;

public interface UserDao {
    void addUser(User user);
    User getUser(String id);
}
