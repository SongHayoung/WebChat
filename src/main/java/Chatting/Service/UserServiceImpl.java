package Chatting.Service;

import Chatting.Dao.UserDaoHibernate;
import Chatting.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDaoHibernate userDao;

    public boolean login(User user) {
        userDao.findById(user.getId());
        Optional<User> targetUser = userDao.findById(user.getId());
        if(!targetUser.isPresent())
            return false;
        user.setRole(targetUser.get().getRole());
        return targetUser.get().getPassword().equals(user.getPassword());
    }

    public void register(User user) {
        user.setRole("USER");
        userDao.save(user);
    }
}
