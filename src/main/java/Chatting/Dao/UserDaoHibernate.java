package Chatting.Dao;

import Chatting.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDaoHibernate extends JpaRepository<User, String> {
}
