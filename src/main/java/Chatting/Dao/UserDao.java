package Chatting.Dao;

import Chatting.VO.User;

public interface UserDao {
    void addUser(User user);
    User getUser(String id);
}
