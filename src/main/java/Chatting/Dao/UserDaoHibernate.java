package Chatting.Dao;

import Chatting.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoHibernate extends JpaRepository<User, Long> {
    User findById(String id);
}
