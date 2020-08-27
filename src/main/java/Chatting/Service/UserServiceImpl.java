package Chatting.Service;

import Chatting.Dao.UserDaoHibernate;
import Chatting.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDaoHibernate userDao;

    public boolean login(User user) {
        User targetUser = userDao.findById(user.getId());
        user.setRole(targetUser.getRole());
        return targetUser.getPassword().equals(user.getPassword());
    }

    public void register(User user) {
        userDao.save(user);
    }
}
