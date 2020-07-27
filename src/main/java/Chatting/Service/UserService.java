package Chatting.Service;

import Chatting.VO.User;

public interface UserService {
    boolean login(User user);
    void register(User user);
}
