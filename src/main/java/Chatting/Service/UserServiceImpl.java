package Chatting.Service;

import Chatting.Dao.UserDao;
import Chatting.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    public boolean login(User user) {
        User targetUser = userDao.getUser(user.getId());
        user.setRole(targetUser.getRole());
        return targetUser.getPassword().equals(user.getPassword());
    }

    public void register(User user) {
        userDao.addUser(user);
    }
}
