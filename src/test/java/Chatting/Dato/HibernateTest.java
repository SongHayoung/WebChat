package Chatting.Dato;

import Chatting.Dao.UserDaoHibernate;
import Chatting.Service.UserService;
import Chatting.Entity.User;
import Config.RootConfig;
import Config.StompWebSocketConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, StompWebSocketConfig.class})
@Transactional
public class HibernateTest {
    private Logger logger = LoggerFactory.getLogger(HibernateTest.class);
    private List<User> user;
    @Autowired
    private UserDaoHibernate userDaoHibernate;

    @Before
    public void setUp() {
        user = new ArrayList<>(Arrays.asList(
                User.builder().id("USER1").password("USER1").role("USER").build(),
                User.builder().id("USER2").password("USER2").role("USER").build(),
                User.builder().id("USER3").password("USER3").role("USER").build()
        ));
    }

    @Test
    public void addUser() {
        userDaoHibernate.save(user.get(0));
        userDaoHibernate.save(user.get(1));
        userDaoHibernate.save(user.get(2));
    }

    @Test
    public void getUser() {
        userDaoHibernate.save(user.get(0));
        userDaoHibernate.save(user.get(1));
        Optional<User> target = userDaoHibernate.findById(user.get(0).getId());
        assertThat(target.get(), is(user.get(0)));
    }

    @Test
    public void getNotExsistUser() {
        Optional<User> target = userDaoHibernate.findById("NOT EXISTS");
        assertThat(target.isPresent(), is(false));
    }

    @Test
    public void sqlInjection() {
        userDaoHibernate.save(user.get(0));
        userDaoHibernate.save(user.get(1));
        Optional<User> injection = userDaoHibernate.findById("; DELETE * FROM USERS;");
        Optional<User> target = userDaoHibernate.findById(user.get(0).getId());
        assertThat(target.get(), is(user.get(0)));
    }

}
