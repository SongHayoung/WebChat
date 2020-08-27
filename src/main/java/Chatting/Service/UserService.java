package Chatting.Service;

import Chatting.Entity.User;

public interface UserService {
    boolean login(User user);
    void register(User user);
}
