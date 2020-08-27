package Chatting.Dao;

import Chatting.Entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoJdbc implements UserDao{
    @Autowired
    SqlSession sqlSession;

    public void addUser(User user) {
        user.setRole("USER");
        sqlSession.insert("insertUser", user);
    }

    public User getUser(String id) {
        return sqlSession.selectOne("getUser", id);
    }
}
